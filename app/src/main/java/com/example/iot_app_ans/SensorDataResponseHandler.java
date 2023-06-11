package com.example.iot_app_ans;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import android.widget.TextView;

public class SensorDataResponseHandler implements Callback<JsonObject> {


    private MainActivity mainActivity;
    private int textViewId;

    public SensorDataResponseHandler(MainActivity mainActivity, int textViewId) {
        this.mainActivity = mainActivity;
        this.textViewId = textViewId;
    }

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if (response.isSuccessful()) {
            JsonObject jsonObject = response.body();
            if (jsonObject != null) {
                String stateValue = jsonObject.get("state").getAsString();
                String unitOfMeasurement = jsonObject.get("attributes").getAsJsonObject().get("unit_of_measurement").getAsString();
                String message = stateValue + " " + unitOfMeasurement;

                // Aktualizacja TextView
                TextView textView = mainActivity.findViewById(textViewId);
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
        Log.e("TAG", "Błąd komunikacji: " + t.getMessage());
    }
}
