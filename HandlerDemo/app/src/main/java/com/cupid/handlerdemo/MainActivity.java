package com.cupid.handlerdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv_msg;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String str = (String) msg.obj;
                    tv_msg.setText(str);
                    break;
                default:
                    tv_msg.setText("消息传递失败");
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

    }

    public void sendMsg(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    String msg = "当前时间是：" + i;

                    Message message = new Message();
                    // 消息的标记
                    message.what = 1;
                    message.obj = msg;

                    myHandler.sendMessage(message);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //tv_msg.setText(msg);
                }

            }
        }).start();

    }


}
