package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

public class SettingsActivity_inf_urz extends AppCompatActivity {
    Button Home,Statistic;
    float x1, x2, y1, y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_inf_urz);

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
    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x2 > x1){
                    Intent i = new Intent(this, SettingsActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}