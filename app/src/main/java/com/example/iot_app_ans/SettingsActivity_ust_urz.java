package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity_ust_urz extends AppCompatActivity {
    Button Home;
    Button Statistic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_ust_urz);
        Statistic = (Button)findViewById(R.id.statisticButton_urz);

        Statistic.setOnClickListener(view -> openStatistic());

        Home = (Button)findViewById(R.id.Home_urz);

        Home.setOnClickListener(view -> openHome());


    }

    private void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openStatistic() {
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }
}