package com.example.myserve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class CalenderActivity extends AppCompatActivity {


    ArrayList barArrayList;
    DatabaseHelper myDb;


    Button firstCalB,secondCalB,deuceCalB,adCalB,topCalB,sliceCalB,flatCalB;
    BarChart barChart;
    TextView title,sideT,firstT,typeT;

    ImageView homeB;
    private String side = "";
    private int numServe = 0;
    private String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calender);

        firstCalB =(Button) findViewById(R.id.firstCal);
        secondCalB =(Button) findViewById(R.id.secondCal);
        deuceCalB =(Button) findViewById(R.id.deuceCal);
        adCalB = (Button)findViewById(R.id.adCal);
        topCalB = (Button)findViewById(R.id.topspinCal);
        sliceCalB = (Button)findViewById(R.id.sliceCal);
        flatCalB = (Button)findViewById(R.id.flatCal);
        title = findViewById(R.id.title);
        sideT = findViewById(R.id.sideT);
        firstT = findViewById(R.id.firstT);
        typeT = findViewById(R.id.typeT);
        homeB = findViewById(R.id.homeB);

        firstCalB.setEnabled(false);

        barChart = findViewById(R.id.barchart);
        myDb = DatabaseHelper.getInstance(this);


        firstOnClick();
        secondOnClick();
        deuceOnClick();
        adOnClick();
        topOnClick();
        sliceOnClick();
        flatOnClick();
        homeBOnClick();
    }

    private void homeBOnClick() {
        homeB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainSwitch = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainSwitch);
                    }
                }
        );
    }

    private void deuceOnClick() {
        deuceCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deuceCalB.setEnabled(false);
                        adCalB.setEnabled(false);
                        firstCalB.setEnabled(true);
                        secondCalB.setEnabled(true);
                        side = "deuce";
                    }
                }
        );
    }

    private void adOnClick(){
        adCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deuceCalB.setEnabled(false);
                        adCalB.setEnabled(false);
                        firstCalB.setEnabled(true);
                        secondCalB.setEnabled(true);
                        side = "ad";
                    }
                }
        );
    }

    private void firstOnClick() {
        firstCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firstCalB.setEnabled(false);
                        secondCalB.setEnabled(false);
                        topCalB.setEnabled(true);
                        sliceCalB.setEnabled(true);
                        flatCalB.setEnabled(true);
                        numServe = 1;

                    }
                }
        );
    }

    private void secondOnClick() {
        secondCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firstCalB.setEnabled(false);
                        secondCalB.setEnabled(false);
                        topCalB.setEnabled(true);
                        sliceCalB.setEnabled(true);
                        flatCalB.setEnabled(true);
                        numServe = 2;
                    }
                }
        );
    }


    private void flatOnClick(){
        flatCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        topCalB.setEnabled(false);
                        sliceCalB.setEnabled(false);
                        flatCalB.setEnabled(false);
                        type = "flat";
                        getData();
                    }
                }
        );
    }
    private void topOnClick(){
        topCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        topCalB.setEnabled(false);
                        sliceCalB.setEnabled(false);
                        flatCalB.setEnabled(false);
                        type = "topspin";
                        getData();
                    }
                }
        );
    }
    private void sliceOnClick(){
        sliceCalB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        topCalB.setEnabled(false);
                        sliceCalB.setEnabled(false);
                        flatCalB.setEnabled(false);
                        type = "slice";
                        getData();
                    }
                }
        );
    }

    private void getData(){
        topCalB.setVisibility(View.GONE);
        sliceCalB.setVisibility(View.GONE);
        flatCalB.setVisibility(View.GONE);
        deuceCalB.setVisibility(View.GONE);
        adCalB.setVisibility(View.GONE);
        firstCalB.setVisibility(View.GONE);
        secondCalB.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        sideT.setVisibility(View.GONE);
        firstT.setVisibility(View.GONE);
        typeT.setVisibility(View.GONE);
        barChart.setVisibility(View.VISIBLE);
        barArrayList = new ArrayList();
        Cursor res = myDb.getALlData();
        while(res.moveToNext()){
            float percentage;
           Toast.makeText(CalenderActivity.this,res.getInt(0)+"",Toast.LENGTH_SHORT).show();
            if(res.getString(5).equals(side)&&res.getInt(3)==numServe&&res.getString(4).equals(type)) {
                float made = res.getInt(2);
                float total = res.getInt(1);
                percentage = (made/total)*100;
                percentage =  Math.round(percentage * 100) / 100;
                barArrayList.add(new BarEntry(res.getInt(0),percentage));

            }
        }
        BarDataSet barDataSet = new BarDataSet(barArrayList,"Serve Stats");
        BarData barData = new BarData(barDataSet);
        barDataSet.setColor(Color.LTGRAY);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);
        barChart.setData(barData);


    }
}