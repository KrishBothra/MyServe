package com.example.myserve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DataPassListener {
    //test
    DatabaseHelper myDb;
    EditText editText_name,editText_surname,editText_marks,editText_id;
    Button deuce,ad;
    int total,made,number;
    String type,side;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

//        editText_marks = (EditText) findViewById(R.id.editText_marks);
//        editText_name = (EditText)findViewById(R.id.editText_name);
//        editText_surname = (EditText)findViewById(R.id.editText_surname);
//        editText_id = (EditText)findViewById(R.id.editText_id);
        deuce = (Button)findViewById(R.id.deuce);
        ad = (Button)findViewById(R.id.ad);
//        button_update = (Button)findViewById(R.id.button_update);
//        button_delete = (Button)findViewById(R.id.button_delete);
//        AddData();
//        viewAll();
//        UpdateData();
//        DeleteData();
        deuceClick();
    }

    private int fragmentSwitch =0;

    @Override
    public void onDataPass(String data) {
//        Toast.makeText(MainActivity.this,String.valueOf(fragmentSwitch),Toast.LENGTH_LONG).show();
        fragmentSwitch++;
        Toast.makeText(MainActivity.this,String.valueOf(fragmentSwitch),Toast.LENGTH_LONG).show();
        if(fragmentSwitch>0){
            total = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, amount_of_serve_in.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch>1) {
            made = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, first_or_second.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch>2) {
            number = Integer.parseInt(data);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, type_of_serve.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
        if (fragmentSwitch>3) {
            type = data;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, BlankFragment.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
            fragmentSwitch = 0;


//            myDb.insertData(total, made,number,type,side);


        }
    }

    public void deuceClick(){
        deuce.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
//                             showMessage("Error","Nothing found");
//                             return;
//                         }
//
//                         StringBuffer buffer = new StringBuffer();
//                         while (res.moveToNext()){
//                             buffer.append("Id :"+ res.getString(0)+"\n");
//                             buffer.append("Name :"+ res.getString(1)+"\n");
//                             buffer.append("Surname :"+ res.getString(2)+"\n");
//                             buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                         }
//
//                         //show all data
//                        showMessage("Data", buffer.toString());
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