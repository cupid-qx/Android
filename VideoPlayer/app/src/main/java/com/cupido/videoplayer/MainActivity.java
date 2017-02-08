package com.cupido.videoplayer;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.vv_main) ;

        /**
         * 本地视频播放
         */

//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.mp4";
//        videoView.setVideoPath(path);

        /**
         * 网络视频播放
         */
        videoView.setVideoURI(Uri.parse("http://192.168.1.106:8080/Video/hello.mp4"));

        /**
         * 视频的控制 MediaController
         */

        MediaController controller = new MediaController(this) ;
        // 设置VideoView与MediaCOntroller 建立关联
        videoView.setMediaController(controller);
        // 设置MediaController 与 VideoView 建立关联
        controller.setMediaPlayer(videoView);

    }
}
