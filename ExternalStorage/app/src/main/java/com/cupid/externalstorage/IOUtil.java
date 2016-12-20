package com.cupid.externalstorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by QcQ on 2016/11/20.
 * 外部存储工具类
 */

public class IOUtil {

    public static final String PATH = Environment.getExternalStorageDirectory()+
            File.separator+"cupid"+File.separator+"images" ;

    //判断 SD 卡是否挂载
    public static boolean isMouted()
    {
        String state = Environment.getExternalStorageState() ;
        return state.equals(Environment.MEDIA_MOUNTED) ;
    }

    //保存图片
    public static boolean saveImg(String name,byte[] data){
        if(!isMouted())
            return false ;
        File dir = new File(PATH) ;
        if(!dir.exists())
            dir.mkdirs() ;//如果文件夹不存在，则创建一个文件夹
        try {
            FileOutputStream fos = new FileOutputStream(new File(dir,name)) ;
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
        return true ;
    }

    //读取图片
    @Nullable
    public static Bitmap readImg(String fileName) throws Exception
    {
        if(!isMouted())
            return null ;
        File imgFile = new File(PATH,fileName) ;
        if(imgFile.exists())
        {
           return BitmapFactory.decodeFile(imgFile.getAbsolutePath()) ;
        }
        return null ;
    }
}
