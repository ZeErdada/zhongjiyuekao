package com.example.administrator.wangye2017_9_22;

import android.app.Application;
import android.content.Context;

import com.example.administrator.wangye2017_9_22.utils.ImageLoadUtils;

/**
 * Created by Administrator on 2017/9/22.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        //初始化 ImageLoad工具类
        // 只在Application中初始化一次 全局可使用
        ImageLoadUtils.INSTANCE.init(this);
    }

    public static Context getContext() {
        return context;
    }

}