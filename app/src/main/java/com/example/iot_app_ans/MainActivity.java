package com.example.iot_app_ans;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    Button buttonSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        buttonSettings = findViewById(R.id. Settings);

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewActivity();
            }
        });

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

    private void NewActivity() {
        setContentView(R.layout.activity_settings);
    }

}