package com.example.myserve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class settings extends AppCompatActivity {


    ImageView homeB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        homeB = findViewById(R.id.homeB);
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
}