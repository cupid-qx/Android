package com.cupid.handlerdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_msg ;

    private Handler myHandler = new Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

    }

    public void sendMsg(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<20;i++){
                    String msg = "当前时间是："+i ;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tv_msg.setText(msg);
                }

            }
        }).start();

    }


}
