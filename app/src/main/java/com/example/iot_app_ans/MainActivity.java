package com.example.iot_app_ans;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    TextView txtData;
    Button buttonSettings;
    Button Statistic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        txtData = findViewById(R.id.txtData);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        txtData.setText(formattedDate);


        buttonSettings = findViewById(R.id. Settings);
        Statistic = (Button) findViewById(R.id.StatisticButton);
        Statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStatistic();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewActivity();
            }
        });

    }

    private void openStatistic() {
         Intent intent = new Intent(this, StatisticActivity.class);
         startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x2 < x1){
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
//Jakub
    private void NewActivity() {
        setContentView(R.layout.activity_settings);
    }

}