package com.example.myserve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DataPassListener {
    //test
    DatabaseHelper myDb;
    EditText editText_name,editText_surname,editText_marks,editText_id;
    Button deuce,ad,deuceYour,adYour;
    int total,made,number = 0;
    float allTimeTotal,allTimeMade = 0;
    float allTimeTotalSecond,allTimeMadeSecond = 0;

    String type,side;
    private float topSpinMade = 0;
    private float topSpinTotal = 0;

    private float sliceMade = 0;
    private float sliceTotal = 0;

    private float flatMade = 0;
    private float flatTotal = 0;

    private GestureDetectorCompat mGestureDetector;

    ImageView calender;
    boolean calenderChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
//        mGestureDetector = new GestureDetectorCompat(this,new GestureListener());

//        editText_marks = (EditText) findViewById(R.id.editText_marks);
//        editText_name = (EditText)findViewById(R.id.editText_name);
//        editText_surname = (EditText)findViewById(R.id.editText_surname);
//        editText_id = (EditText)findViewById(R.id.editText_id);
        deuce = (Button)findViewById(R.id.deuce);
        ad = (Button)findViewById(R.id.ad);
        deuceYour = findViewById(R.id.deuceYour);
        adYour = findViewById(R.id.adYour);
        calender = findViewById(R.id.calender);
        mGestureDetector = new GestureDetectorCompat(this,new GestureListener());
//        button_update = (Button)findViewById(R.id.button_update);
//        button_delete = (Button)findViewById(R.id.button_delete);
//        AddData();
//        viewAll();
//        UpdateData();
//        DeleteData();
        calenderClick();
        deuceClick();
        adClick();
        displayData();
    }

    private void calenderClick() {
        calender.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calenderChecked = true;
                        Cursor res = myDb.getALlData();
                        res.moveToLast();
//                        displayDataCal(res);
                        Intent calenderSwitch = new Intent(getApplicationContext(), CalenderActivity.class);
                        startActivity(calenderSwitch);


                    }
                }
        );
    }

//    public void displayDataCal(Cursor res){
//        if(res.getString(5).equals("deuce")&&res.getInt(3)==1) {
//            allTimeTotal += res.getInt(1);
//            allTimeMade += res.getInt(2);
//            percentage = (allTimeMade/allTimeTotal)*100;
//            deuce.setText("First Serve: \n"+percentage+"%");
//            ad.setText("");
//            adYour.setText("");
//            displayDataCalType("deuce",res);
//        } else if(res.getString(5).equals("deuce")&&res.getInt(3)==2) {
//            allTimeTotal += res.getInt(1);
//            allTimeMade += res.getInt(2);
//            percentage = (allTimeMade/allTimeTotal)*100;
//            deuce.setText("Second Serve: \n"+percentage+"%");
//            ad.setText("");
//            adYour.setText("");
//            displayDataCalType("deuce",res);
//        }
//
//        if(res.getString(5).equals("ad")&&res.getInt(3)==1) {
//            allTimeTotal += res.getInt(1);
//            allTimeMade += res.getInt(2);
//            percentage = (allTimeMade/allTimeTotal)*100;
//            ad.setText("First Serve: \n"+percentage+"%");
//            deuce.setText("");
//            deuceYour.setText("");
//            displayDataCalType("ad",res);
//        } else if(res.getString(5).equals("ad")&&res.getInt(3)==2) {
//            allTimeTotal += res.getInt(1);
//            allTimeMade += res.getInt(2);
//            percentage = (allTimeMade/allTimeTotal)*100;
//            ad.setText("Second Serve: \n"+percentage+"%");
//            deuce.setText("");
//            deuceYour.setText("");
//            displayDataCalType("ad",res);
//        }
//    }
//
//    private void displayDataCalType(String side,Cursor res) {
//        if(res.getString(4).equals("topspin")){
//            topSpinTotal += res.getInt(1);
//            topSpinMade += res.getInt(2);
//            percentageTopSpin = (topSpinMade/topSpinTotal)*100;
//        } else if (res.getString(4).equals("slice")) {
//            sliceTotal += res.getInt(1);
//            sliceMade += res.getInt(2);
//            percentageSlice  = (sliceMade/sliceTotal)*100;
//        }else{
//            flatTotal += res.getInt(1);
//            flatMade += res.getInt(2);
//            percentageFlat  = (flatMade/flatTotal)*100;
//        }
//    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        //CONTROL + 0
        // when you first create the class gives you options for all the functions you wanna make
        //for gesture listeners


        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            if(calenderChecked) {
                Toast.makeText(MainActivity.this, "Long Press", Toast.LENGTH_SHORT).show();
            }
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
            if(calenderChecked) {
                Toast.makeText(MainActivity.this, "Double Tap", Toast.LENGTH_SHORT).show();
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            if(calenderChecked) {
                Toast.makeText(MainActivity.this, "Single Tap Confirm", Toast.LENGTH_SHORT).show();
            }
            return super.onSingleTapConfirmed(e);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void adClick() {
        ad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        side = "ad";
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView, amount_of_serve.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack(null)
                                .commit();
                    }
                }
        );
    }

    private int fragmentSwitch =0;

    @Override
    public void onDataPass(String data) {
//        Toast.makeText(MainActivity.this,String.valueOf(fragmentSwitch),Toast.LENGTH_LONG).show();
        fragmentSwitch++;
//        Toast.makeText(MainActivity.this,String.valueOf(fragmentSwitch),Toast.LENGTH_LONG).show();
        if(fragmentSwitch==1){
            total = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, amount_of_serve_in.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch==2) {
            made = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, first_or_second.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch==3) {
            number = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, type_of_serve.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch==4) {
            type = data;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, BlankFragment.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
            fragmentSwitch = 0;


            myDb.insertData(total,made,number,type,side);
            displayData();


        }

    }
    float percentage = 0,percentage2 = 0,percentageTopSpin = 0,percentageSlice = 0,percentageFlat = 0;

    private void displayData() {
        Cursor res = myDb.getALlData();
        while(res.moveToNext()) {
            if(res.getString(5).equals("deuce")&&res.getInt(3)==1) {
                allTimeTotal += res.getInt(1);
                allTimeMade += res.getInt(2);
                percentage = (allTimeMade/allTimeTotal)*100;
                percentage =  Math.round(percentage * 100) / 100;
                displayTypePercentages(res);

            }
        }
        res = myDb.getALlData();
        while(res.moveToNext()){
            if(res.getString(5).equals("deuce")&&res.getInt(3)==2) {
                allTimeTotalSecond += res.getInt(1);
                allTimeMadeSecond += res.getInt(2);
                percentage2 = (allTimeMadeSecond/allTimeTotalSecond)*100;
                displayTypePercentages(res);
            }
        }
        percentage =  Math.round(percentage * 100) / 100;
        percentage2 =  Math.round(percentage2 * 100) / 100;
        percentageTopSpin =  Math.round(percentageTopSpin * 100) / 100;
        percentageSlice =  Math.round(percentageSlice * 100) / 100;
        percentageFlat =  Math.round(percentageFlat * 100) / 100;
//        Toast.makeText(MainActivity.this,topSpinMade+"",Toast.LENGTH_SHORT).show();

        deuce.setText("First Serve: \n"+percentage+"%\nSecond Serve: \n"+percentage2+"%");
        deuceYour.setText("Top Spin: \n"+percentageTopSpin+"%\nSlice: \n"+percentageSlice+"%\nFlat: \n"+percentageFlat+"%");
        allTimeMade = 0;
        allTimeTotal = 0;
        allTimeTotalSecond = 0;
        allTimeMadeSecond = 0;
        res = myDb.getALlData();
        percentage = 0;
        percentage2 = 0;
        percentageSlice = 0;
        percentageTopSpin = 0;
        percentageFlat = 0;
        topSpinTotal = 0;
        topSpinMade = 0;
        sliceTotal = 0;
        sliceMade = 0;
        flatTotal = 0;
        flatMade = 0;
        while(res.moveToNext()) {
            if(res.getString(5).equals("ad")&&res.getInt(3)==1) {
                allTimeTotal += res.getInt(1);
                allTimeMade += res.getInt(2);
                percentage = (allTimeMade/allTimeTotal)*100;
                displayTypePercentages(res);
            }
        }
        res = myDb.getALlData();
        while(res.moveToNext()) {
            if(res.getString(5).equals("ad")&&res.getInt(3)==2) {
                allTimeTotalSecond += res.getInt(1);
                allTimeMadeSecond += res.getInt(2);
                percentage2 = (allTimeMadeSecond/allTimeTotalSecond)*100;
                displayTypePercentages(res);
            }
        }
        percentage =  Math.round(percentage * 100) / 100;
        percentage2 =  Math.round(percentage2 * 100) / 100;
        percentageTopSpin =  Math.round(percentageTopSpin * 100) / 100;
        percentageSlice =  Math.round(percentageSlice * 100) / 100;
        percentageFlat =  Math.round(percentageFlat * 100) / 100;
        ad.setText("First Serve: \n"+percentage+"%\nSecond Serve: \n"+percentage2+"%");
        adYour.setText("Top Spin: \n"+percentageTopSpin+"%\nSlice: \n"+percentageSlice+"%\nFlat: \n"+percentageFlat+"%");
        allTimeMade = 0;
        allTimeTotal = 0;
        allTimeTotalSecond = 0;
        allTimeMadeSecond = 0;
        res = myDb.getALlData();
        percentage = 0;
        percentage2 = 0;
        percentageSlice = 0;
        percentageTopSpin = 0;
        percentageFlat = 0;
        topSpinTotal = 0;
        topSpinMade = 0;
        sliceTotal = 0;
        sliceMade = 0;
        flatTotal = 0;
        flatMade = 0;

    }

    private void displayTypePercentages(Cursor res){
        if(res.getString(4).equals("topspin")){
            topSpinTotal += res.getInt(1);
            topSpinMade += res.getInt(2);
            percentageTopSpin = (topSpinMade/topSpinTotal)*100;
        } else if (res.getString(4).equals("slice")) {
            sliceTotal += res.getInt(1);
            sliceMade += res.getInt(2);
            percentageSlice  = (sliceMade/sliceTotal)*100;
        }else{
            flatTotal += res.getInt(1);
            flatMade += res.getInt(2);
            percentageFlat  = (flatMade/flatTotal)*100;
        }
    }


    public void deuceClick(){
        deuce.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        side = "deuce";
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView, amount_of_serve.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack(null)
                                .commit();
                    }
                }
        );
    }

//    public void AddData(){
//        button_add.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isInserted = myDb.insertData(editText_name.getText().toString(),
//                                editText_surname.getText().toString(),
//                                editText_marks.getText().toString());
//                        if(isInserted){
//                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//        );
//    }
//
//    public void viewAll(){
//        button_view.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getALlData();
//                         if(res.getCount()==0){
//                             //show messages
//                             return;
//                         }
//
//                         StringBuffer buffer = new StringBuffer();
//                         while (res.moveToNext()){
//                             buffer.append("Id :"+ res.getString(0)+"\n");
//                             buffer.append("Name :"+ res.getString(1)+"\n");
//                             buffer.append("Surname :"+ res.getString(2)+"\n");
//                             buffer.append("Marks :"+ res.getString(3)+"\n\n");
//
//
//                         }
//
//                         //show all data
//
//                    }
//                }
//        );
//    }
//
//    public void UpdateData(){
//        button_update.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editText_id.getText().toString()
//                                ,editText_name.getText().toString(),
//                                editText_surname.getText().toString(),
//                                editText_marks.getText().toString());
//                        if(isUpdate== true){
//                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(MainActivity.this,"Data not Updataed",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//        );
//    }
//
//    public void DeleteData(){
//        button_delete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editText_id.getText().toString());
//                        if(deletedRows>0){
//                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//        );
//    }
//
//
//    public void showMessage(String title, String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }
}