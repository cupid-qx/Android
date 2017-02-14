package com.cupido.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_CODE =1;
    private Button btn_send ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = getIntent();
//        String extra_data = intent.getStringExtra("extra_data");
//        Toast.makeText(MainActivity.this,extra_data,Toast.LENGTH_SHORT).show();
//        Log.d("Demo",extra_data);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == 2){
                    String returnData = data.getStringExtra("extra_data");
                    Log.d("MainActivity",returnData);
                }
        }
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
