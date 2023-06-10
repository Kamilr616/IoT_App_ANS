package com.example.iot_app_ans;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {
    private LineChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statystki1);
        chart = findViewById(R.id.chart);
        createTemperatureChart();
    }
    private void createTemperatureChart() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 25f)); // Poniedziałek
        entries.add(new Entry(1, 28f)); // Wtorek
        entries.add(new Entry(2, 26f)); // Środa
        entries.add(new Entry(3, 30f)); // Czwartek
        entries.add(new Entry(4, 29f)); // Piątek
        entries.add(new Entry(5, 27f)); // Sobota
        entries.add(new Entry(6, 24f)); // Niedziela

        LineDataSet dataSet = new LineDataSet(entries, "Temperatura (°C)");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        Description description = new Description();
        description.setText("Temperatura w stosunku do dni tygodnia");
        chart.setDescription(description);

        chart.invalidate();
    }
}
