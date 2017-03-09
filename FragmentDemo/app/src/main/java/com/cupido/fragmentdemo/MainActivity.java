package com.cupido.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * 碎片是一种可以嵌入在活动当中的UI片段，能够让程序更加合理和充分的利用大屏幕的空火箭
 * 创建碎片的过程
 *      ①：创建 XML 样式文件
 *      ②：创建对应类对象继承android.support.v4.app.Fragment类，
 *      ③：通过 inflater 的 inflate 方法绑定样式文件和类文件
 *      ④：在主样式文件中引用 fragment 标签，通过 name 指定全类名
 *
 * 动态添加碎片
 *      ①：获取FragmentManager对象
 *      ②：开启事务
 *      ③：replace实例
 *      ④：提交事务
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_left);
        btn.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_left:
                replaceFragment(new RightFragment_2());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentLayout_right,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
