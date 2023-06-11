package com.example.iot_app_ans;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;


public interface ESP32Service {
    @GET("/api/states/sensor.iot_esp_ans_temperatura_1")
    Call<JsonObject> getTemperatureData();

    @GET("/api/states/sensor.iot_esp_ans_wilgotnosc_1")
    Call<JsonObject> getHumidityData();

    @GET("/api/states/sensor.iot_esp_ans_wifi_signal_1")
    Call<JsonObject> getWIFIData();

    @GET("/api/states/sensor.iot_esp_ans_wilgotnosc_bezwzgledna_1")
    Call<JsonObject> getHumidityAbsolutData();

    @GET("/api/states/switch.iot_esp_ans_relay_1")
    Call<JsonObject> getSwitchStatus();

    @POST("/api/services/switch/turn_on")
    Call<JsonObject> turnOnSwitch(@Body JsonObject body);
    @POST("/api/services/switch/turn_off")
    Call<JsonObject> turnOffSwitch(@Body JsonObject body);

}



