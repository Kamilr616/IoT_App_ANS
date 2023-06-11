package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    Button Ustawienia_urzadzenia,Home,Statistic,informacje_urzadzenia,informacje_aplikacji,authors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Statistic = (Button)findViewById(R.id.statisticButton_urz);
        Statistic.setOnClickListener(view -> openStatistic());

        Home = (Button)findViewById(R.id.Home_urz);
        Home.setOnClickListener(view -> openHome());

        Ustawienia_urzadzenia = (Button)findViewById(R.id. ust_urz);
        Ustawienia_urzadzenia.setOnClickListener(view -> openUst_urz());

        informacje_urzadzenia =(Button)findViewById(R.id. inf_urz);
        informacje_urzadzenia.setOnClickListener(view -> open_inf_urz());

        informacje_aplikacji =(Button)findViewById(R.id. inf_apl);
        informacje_aplikacji.setOnClickListener(view -> open_inf_app());

    }

    private void openUst_urz() {
        Intent intent = new Intent(this, SettingsActivity_ust_urz.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openStatistic() {
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }

    private void open_inf_urz() {
        Intent intent = new Intent(this, SettingsActivity_inf_urz.class);
        startActivity(intent);
    }
    private void open_inf_app() {
        Intent intent = new Intent(this, SettingsActivity_inf_app.class);
        startActivity(intent);
    }
}
