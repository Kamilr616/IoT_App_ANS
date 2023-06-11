package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    float x1, x2, y1, y2;
    Button Statistic;
    TextView txtData;
    Button Settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        txtData = findViewById(R.id.txtData);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        txtData.setText(formattedDate);

        Statistic = (Button) findViewById(R.id. statisticButton2);

        Statistic.setOnClickListener(view -> openStatistic());

        Settings = (Button) findViewById(R.id. settings2);

        Settings.setOnClickListener(view -> openSettings());
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
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
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}