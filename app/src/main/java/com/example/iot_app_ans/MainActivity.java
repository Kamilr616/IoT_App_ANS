package com.example.iot_app_ans;
import com.google.gson.annotations.SerializedName;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    TextView txtData;
    Button buttonSettings;
    Button Statistic;
    ESP32Service esp32Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        txtData = findViewById(R.id.txtData);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        txtData.setText(formattedDate);


        buttonSettings = findViewById(R.id.Settings);
        Statistic = (Button) findViewById(R.id.StatisticButton);
        Statistic.setOnClickListener(view -> openStatistic());
        buttonSettings.setOnClickListener(view -> NewActivity());

        // Inicjalizacja Retrofit
        esp32Service = ESP32ApiClient.getClient().create(ESP32Service.class);

        // Wywołanie żądania GET
        Call<JsonObject> call = esp32Service.getData();


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null) {
                        String stateValue = jsonObject.get("state").getAsString();
                        String unitOfMeasurement = jsonObject.get("attributes").getAsJsonObject().get("unit_of_measurement").getAsString();
                        String message = stateValue + " " + unitOfMeasurement;

                        // Aktualizacja TextView
                        TextView textView = findViewById(R.id.txt23C);
                        textView.setText(message);
                    } else {
                        String errorMessage = "Błąd odpowiedzi HTTP: " + response.code();
                        Log.d("TAG", errorMessage);
                        switch (response.code()) {
                            case 400:
                                // Obsługa błędu 400 Bad Request
                                break;
                            case 401:
                                // Obsługa błędu 401 Unauthorized
                                break;
                            case 404:
                                // Obsługa błędu 404 Not Found
                                break;
                            default:
                                // Obsługa innych błędów
                                break;
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Obsługa błędu komunikacji
            }
            });
    }

    private void openStatistic() {
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x2 < x1) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    private void NewActivity() {
        setContentView(R.layout.activity_settings);
    }
}




