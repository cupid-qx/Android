package com.cupid.viewpagedemo;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager vp_img;
    private ArrayList<ImageView> mList;
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vp_img = (ViewPager) findViewById(R.id.vp_img);

//        int[] imgs = getImages();
//        mList = new ArrayList<ImageView>();
//        for (int i = 0; i < imgs.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setBackgroundResource(imgs[i]);
//            mList.add(imageView);
//        }

        list = new ArrayList<Fragment>() ;
        for (int i = 0; i < 3; i++) {
            list.add( PagerFragment.newInstance("今天是个好日子"+i));
        }

        // 必须得到一个 v4 包中的fragment
        MyViewPager viewPager = new MyViewPager(getSupportFragmentManager());

        vp_img.setAdapter(viewPager);

    }

    private int[] getImages() {

        return new int[]{
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5,
                R.drawable.pic6,
        };
    }

    /**
     * 1、getCount() 用来表示到底可以显示多少子视图
     * 2、isViewFromObject() 用来判断是否产生新的子视图
     * 3、instantiateItem() 产生一个新的视图
     * 4、destroyItem() 移除一个视图
     */
//    class MyViewPager extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mList.get(position));
//            return mList.get(position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mList.get(position));
//        }
//    }

    class MyViewPager extends FragmentPagerAdapter{

        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
