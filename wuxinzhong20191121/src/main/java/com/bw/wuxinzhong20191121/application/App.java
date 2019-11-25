package com.bw.wuxinzhong20191121.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
