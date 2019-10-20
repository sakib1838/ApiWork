package com.example.apiwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent mySuperIntent = new Intent(Loading.this, UserDataActivity.class);
                startActivity(mySuperIntent);
                Loading.this.finish();
            }
        }, 10000);

    }
}
