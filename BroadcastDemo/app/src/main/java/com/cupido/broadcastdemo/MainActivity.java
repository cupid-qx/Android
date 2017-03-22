package com.cupido.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 通过动态的方式注册一个监听网络变化的广播接收器
 */
public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private Button btn_send ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();

        /**
         * 当系统的网络状态发生变化时，发出的就是这一条 android.net.conn.CONNECTIVITY_CHANGE 的 action
         */
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        networkChangeReceiver = new NetworkChangeReceiver();

        // 进行注册，返回的是 Intent 对象
        Intent intent = registerReceiver(networkChangeReceiver, intentFilter);


        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.cupid.broadcastdemo.MY_BROADCAST");
                // sendBroadcast(intent);

                /**
                 * 发送有序广播，第二个参数为权限相关的字符串
                 * 可以在 AndroidManifest.xml文件中定义优先级来制定接受顺序
                 */
                sendOrderedBroadcast(intent,null);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();

            // 通过 getSystemService 方法得到 ConnectivityManager 的系统服务类实例
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            // 通过 getActiveNetworkInfo() 方法得到 NetworkInfo 对象，在进行判断当前是否有网络
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            //♥♥♥♥  对于网络的访问需要在 AndroidManifest.xml 中声明权限  ♥♥♥♥

            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
