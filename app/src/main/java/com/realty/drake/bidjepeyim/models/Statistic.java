package com.example.fab.chart;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.realty.drake.bidjepeyim.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS;

public class Statistic extends AppCompatActivity {

    public String uid;
    public String author;
    public String title1;
    public String body;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database
            .getReference()
            .child("0");



    BarChart barChart;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = (BarChart)findViewById(R.id.chart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(1000);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.animateXY(3100, 3100);

        //myRef.setValue("Hello, World!");
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        // barEntries.add(new BarEntry(0,350f));
        barEntries.add(new BarEntry(1,100f));
        barEntries.add(new BarEntry(2,300f));
        barEntries.add(new BarEntry(3,200f));
        barEntries.add(new BarEntry(4,100f));
        barEntries.add(new BarEntry(5,100f));
        barEntries.add(new BarEntry(6,300f));
        barEntries.add(new BarEntry(7,240f));
        barEntries.add(new BarEntry(8,250f));
        barEntries.add(new BarEntry(9,190f));
        barEntries.add(new BarEntry(10,160f));

        barEntries.add(new BarEntry(11,150f));
        barEntries.add(new BarEntry(12,100f));
        //barEntries.add(new BarEntry(5,900f));
        //  barEntries.add(new BarEntry(6,100f));
        // barEntries.add(new BarEntry(7,250f));
        //  barEntries.add(new BarEntry(8,300f));
        // barEntries.add(new BarEntry(9,200f));
        //   barEntries.add(new BarEntry(10,120f));
        // barEntries.add(new BarEntry(11,600f));
        //   barEntries.add(new BarEntry(12,10f));
        // barEntries.add(new BarEntry(13,0));
        //TextView textView = (TextView)findViewById(R.id.tvMinis);
        final BarDataSet barDataSet = new BarDataSet(barEntries,"MONTH");


        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(barDataSet);


        data.setBarWidth(0.35f);
        barChart.setData(data);



        final String[] months = new String[]{"","Jan","Fev","Mar","Av","Mai","Juin","Jun","Aout","Sept","Oct","Nov","Dec"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxis.setAxisLineWidth(0.5f);
        //xAxis.setGranularity(1);
        //  xAxis.setAxisMaximum(14);
        //  xAxis.setCenterAxisLabels(true);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{

        private String[] mValues;
        public MyXAxisValueFormatter(String[] values) {

            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }


}
