package com.example.iot_app_ans;
import com.google.gson.annotations.SerializedName;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ESP32ApiClient {
    private static final String ESP32_BASE_URL = "https://ha.salonar.pl/"; // Adres domeny ESP32
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjYWY3YTA3NDY4OTI0ODM0ODUzNDZjMzc5ZTZmZGJmNCIsImlhdCI6MTY4NjQ5MDkzNiwiZXhwIjoyMDAxODUwOTM2fQ.u9PO6vQDtTs2xncIZD7jWlLG7MbTk1m2EihsoYJDeRY"; // Twój token uwierzytelniający

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(AUTH_TOKEN))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ESP32_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
