package com.example.iot_app_ans;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ESP32Service {
    @GET("/api/states/sensor.iot_esp_ans_temperatura_1")
    Call<JsonObject> getTemperatureData();

    @GET("/api/states/sensor.iot_esp_ans_wilgotnosc_1")
    Call<JsonObject> getHumidityData();

    @GET("/api/states/sensor.iot_esp_ans_wifi_signal_1")
    Call<JsonObject> getWIFIData();

    @GET("/api/states/sensor.iot_esp_ans_wilgotnosc_bezwzgledna_1")
    Call<JsonObject> getHumidityAbsolutData();

}



