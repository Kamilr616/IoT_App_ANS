package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    Button buttonStatistic ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonStatistic = findViewById(R.id. Statistic1);

        buttonStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewActivity();
            }
        });


    }

    private void NewActivity() {
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }
}