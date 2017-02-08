package com.cupido.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Demo","MainActivity onCreate...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo","MainActivity onStart...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Demo","MainActivity onStop...");
    }
}
