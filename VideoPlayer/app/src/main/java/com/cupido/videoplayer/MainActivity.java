package com.cupido.videoplayer;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    private LinearLayout controllerLayout;
    private ImageView imageView_pause, imageView_fullScreen;
    private TextView textView_timeNow, textView_timeTotal;
    private SeekBar seekBar_volume, seekBar_player;

    private static final int updateUI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 初始化 UI 控件
         */
        initUI();

        /**
         * 设置播放器的相关事件
         */
        setPlayerEvent();


        /**
         * 本地视频播放
         */

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello.mp4";
        videoView.setVideoPath(path);
        UIHandler.sendEmptyMessage(updateUI);
        /**
         * 网络视频播放
         */
//        videoView.setVideoURI(Uri.parse("http://192.168.1.106:8080/Video/hello.mp4"));

        /**
         * 视频的控制 MediaController
         */
//
//        MediaController controller = new MediaController(this);
//        // 设置VideoView与MediaCOntroller 建立关联
//        videoView.setMediaController(controller);
//        // 设置MediaController 与 VideoView 建立关联
//        controller.setMediaPlayer(videoView);
    }

    /**
     * 格式化事件的方法
     */
    private void formatTime(TextView tv, int millisecond) {
        int second = millisecond / 1000;
        int hour = second / 3600;
        int minute = second % 3600 / 60;
        int second1 = second % 60;

        String str = null;
        if (hour != 0) {
            str = String.format("%02d:%02d:%02d", hour, minute, second1);
        } else {
            str = String.format("%02d:%02d", minute, second1);
        }
        tv.setText(str);
    }

    /**
     * 更新 UI 的进度
     */
    private Handler UIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // 获取当前播放时间
            int position = videoView.getCurrentPosition();
            // 获取视频的总时间
            int totalTime = videoView.getDuration();

            if (msg.what == updateUI) {
                formatTime(textView_timeNow, position);
                formatTime(textView_timeTotal, totalTime);

                seekBar_player.setMax(totalTime);
                seekBar_player.setProgress(position);
                UIHandler.sendEmptyMessageDelayed(1, 500);
            }
        }
    };

    private void setPlayerEvent() {
        // 开始暂停的点击事件
        imageView_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imageView_pause.setImageResource(R.drawable.video_start_style);
                    // 暂停播放
                    videoView.pause();
                    UIHandler.removeMessages(updateUI);
                } else {
                    imageView_pause.setImageResource(R.drawable.video_stop_style);
                    // 开始播放
                    videoView.start();
                    UIHandler.sendEmptyMessage(updateUI);
                }
            }
        });

        seekBar_player.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= seekBar.getMax() - 300) {
                    imageView_pause.setImageResource(R.drawable.video_start_style);
                    // 暂停播放
                    videoView.pause();
                    UIHandler.removeMessages(updateUI);
                }
                formatTime(textView_timeNow, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                UIHandler.removeMessages(updateUI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int progress = seekBar.getProgress();
                // 直接跳转到拖拽的位置
                videoView.seekTo(progress);
                UIHandler.sendEmptyMessage(updateUI);
            }
        });
    }

    /**
     * 初始化 UI 控件函数
     */
    private void initUI() {
        videoView = (VideoView) findViewById(R.id.vv_main);

        controllerLayout = (LinearLayout) findViewById(R.id.layout_controllerBar);

        imageView_pause = (ImageView) findViewById(R.id.iv_pause);
        imageView_fullScreen = (ImageView) findViewById(R.id.iv_screen);

        textView_timeNow = (TextView) findViewById(R.id.tv_now);
        textView_timeTotal = (TextView) findViewById(R.id.tv_totalTime);

        seekBar_player = (SeekBar) findViewById(R.id.seekBar_player);
        seekBar_volume = (SeekBar) findViewById(R.id.seekBar_volume);
    }


    @Override
    protected void onPause() {
        super.onPause();
        UIHandler.removeMessages(updateUI);
    }
}
