package com.cupid.externalstorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Button btn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        btn = (Button) findViewById(R.id.btn);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iv.setImageBitmap(IOUtil.readImg("animal.jpg"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void save(View v) throws Exception {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic2);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();


        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
        bitmap.recycle();
        boolean b = IOUtil.saveImg("animal.jpg", byteArray.toByteArray());
        if (b)
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        byteArray.close();

    }
}
