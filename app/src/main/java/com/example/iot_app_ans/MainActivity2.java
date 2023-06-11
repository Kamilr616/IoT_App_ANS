package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    float x1, x2, y1, y2;
    Button Statistic;
    TextView txtData;
    Button Settings;
    ESP32Service esp32Service;
    private static Context appContext;
    private Button relayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        txtData = findViewById(R.id.txtData);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        txtData.setText(formattedDate);

        Statistic = findViewById(R.id.statisticButton2);
        Statistic.setOnClickListener(view -> openStatistic());

        Settings = findViewById(R.id.settings2);
        Settings.setOnClickListener(view -> openSettings());

        // Inicjalizacja Retrofit
        esp32Service = ESP32ApiClient.getClient().create(ESP32Service.class);

        // Przypisanie kontekstu aplikacji do zmiennej appContext
        appContext = getApplicationContext();

        relayButton = findViewById(R.id.relaybutton);
        relayButton.setOnClickListener(view -> {
            // Obsługa zdarzenia kliknięcia przycisku
            String switchStatus = relayButton.getTag().toString();

            if (switchStatus.equals("on")) {
                // Jeśli dioda jest włączona, wyślij żądanie POST, aby wyłączyć
                sendSwitchStatus("off");
            } else if (switchStatus.equals("off")) {
                // Jeśli dioda jest wyłączona, wyślij żądanie POST, aby włączyć
                sendSwitchStatus("on");
            }
        });

        getSwitchStatus();
    }

    private void getSwitchStatus() {
        Call<JsonObject> switchStatusCall = esp32Service.getSwitchStatus();
        switchStatusCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null) {
                        String switchStatus = jsonObject.get("state").getAsString();

                        // Tutaj możesz zrobić coś z wartością switchStatus
                        if (switchStatus.equals("on")) {
                            // Dioda włączona
                            // Możesz zaktualizować interfejs użytkownika, np. zmienić kolor diody
                            relayButton.setBackgroundColor(getResources().getColor(R.color.colorEnabled));
                            relayButton.setTag("on");
                        } else if (switchStatus.equals("off")) {
                            // Dioda wyłączona
                            // Możesz zaktualizować interfejs użytkownika, np. zmienić kolor diody
                            relayButton.setBackgroundColor(getResources().getColor(R.color.colorDisabled));
                            relayButton.setTag("off");
                        } else {
                            // Inny stan diody
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    private void sendSwitchStatus(String status) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("entity_id", "switch.iot_esp_ans_relay_1");

        Call<JsonObject> switchStatusCall;
        if (status.equals("on")) {
            switchStatusCall = esp32Service.turnOnSwitch(requestBody);
            relayButton.setBackgroundColor(getResources().getColor(R.color.colorEnabled));
            relayButton.setTag("on");
        } else {
            switchStatusCall = esp32Service.turnOffSwitch(requestBody);
            relayButton.setBackgroundColor(getResources().getColor(R.color.colorDisabled));
            relayButton.setTag("off");
        }

        switchStatusCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    // Zapytanie POST wykonane pomyślnie
                    // Możesz zaktualizować interfejs użytkownika lub wykonać inne czynności

                    // Sprawdzanie stanu przekaźnika po wysłaniu żądania POST
                    getSwitchStatus();
                } else {
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

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Wystąpił błąd podczas wykonania żądania POST
                // Możesz obsłużyć ten przypadek, np. wyświetlając komunikat o błędzie
            }
        });
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
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
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
