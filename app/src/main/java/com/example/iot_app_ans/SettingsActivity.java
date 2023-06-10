package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    Button Statistic;
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Statistic = (Button)findViewById(R.id. statisticButton3);

        Statistic.setOnClickListener(view -> openStatistic());

        Home = (Button)findViewById(R.id. Home);

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
