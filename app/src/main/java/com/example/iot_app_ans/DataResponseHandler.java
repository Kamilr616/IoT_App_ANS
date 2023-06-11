// DataResponseHandler.java
package com.example.iot_app_ans;

import android.widget.TextView;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataResponseHandler implements Callback<JsonObject> {

    private int textViewId;

    public DataResponseHandler(int textViewId) {
        this.textViewId = textViewId;
    }

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if (response.isSuccessful()) {
            JsonObject jsonObject = response.body();
            if (jsonObject != null) {
                String stateValue = jsonObject.get("state").getAsString();
                String unitOfMeasurement = jsonObject.get("attributes").getAsJsonObject().get("unit_of_measurement").getAsString();
                String message = stateValue + "" + unitOfMeasurement;

                // Aktualizacja TextView
                TextView textView = ((MainActivity) MainActivity.getAppContext()).findViewById(textViewId);
                textView.setText(message);
            } else {
                String errorMessage = "Błąd odpowiedzi HTTP: " + response.code();
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
}
