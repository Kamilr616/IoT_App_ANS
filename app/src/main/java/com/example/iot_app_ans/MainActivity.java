package com.example.iot_app_ans;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.Call;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import com.google.gson.JsonObject;
import android.widget.ProgressBar;
import android.os.Handler;
import android.view.View;




public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    TextView txtData;
    Button buttonSettings;
    Button Statistic;
    ESP32Service esp32Service;
    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        txtData = findViewById(R.id.txtData);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        txtData.setText(formattedDate);

        buttonSettings = findViewById(R.id.Settings);
        Statistic = findViewById(R.id.StatisticButton);
        Statistic.setOnClickListener(view -> openStatistic());
        buttonSettings.setOnClickListener(view -> openSettings());

        // Inicjalizacja Retrofit
        esp32Service = ESP32ApiClient.getClient().create(ESP32Service.class);

        // Przypisanie kontekstu aplikacji do zmiennej appContext
        appContext = getApplicationContext();

        // Wywołanie żądania GET dla czujnika temperatury
        Call<JsonObject> temperatureCall = esp32Service.getTemperatureData();
        temperatureCall.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt23C));

        // Wywołanie żądania GET dla czujnika wilgotności
        Call<JsonObject> humidityCall = esp32Service.getHumidityData();
        humidityCall.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txtThirtySix2));

        // Wywołanie żądania GET dla czujnika WIFI
        Call<JsonObject> wifi = esp32Service.getWIFIData();
        wifi.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt40dbm2));

        // Wywołanie żądania GET dla czujnika WIFI
        Call<JsonObject> HumidityAbsolut = esp32Service.getHumidityAbsolutData();
        HumidityAbsolut.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt75C2));
    }

    public static Context getAppContext() {
        return appContext;
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
                float deltaY = y2 - y1;
                if (deltaY > 0 && Math.abs(deltaY) > 100) {
                    // Swipe z góry na dół - odświeżanie danych
                    showProgressBar(); // Pokazuje ProgressBar
                    refreshData(); // Odświeża dane
                } else if (x1 > x2) {
                    // Swipe w lewo
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }

    private void showProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Ukryj ProgressBar po 5 sekundach
        Handler handler = new Handler();
        handler.postDelayed(() -> hideProgressBar(), 4000); // 4000 milisekund = 5 sekund
    }

    private void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private void refreshData() {
        // Wywołanie żądania GET dla czujnika temperatury
        Call<JsonObject> temperatureCall = esp32Service.getTemperatureData();
        temperatureCall.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt23C));

        // Wywołanie żądania GET dla czujnika wilgotności
        Call<JsonObject> humidityCall = esp32Service.getHumidityData();
        humidityCall.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txtThirtySix2));

        // Wywołanie żądania GET dla czujnika WIFI
        Call<JsonObject> wifi = esp32Service.getWIFIData();
        wifi.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt40dbm2));

        // Wywołanie żądania GET dla czujnika WIFI
        Call<JsonObject> HumidityAbsolut = esp32Service.getHumidityAbsolutData();
        HumidityAbsolut.enqueue(new SensorDataResponseHandler(MainActivity.this, R.id.txt75C2));
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
