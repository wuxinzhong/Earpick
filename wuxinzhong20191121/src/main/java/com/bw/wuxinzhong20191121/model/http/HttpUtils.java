package com.bw.wuxinzhong20191121.model.http;

import com.bw.wuxinzhong20191121.constant.Constant;

import java.security.PublicKey;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/21/021<p>
 * <p>更改时间：2019/11/21/021<p>
 */
public class HttpUtils {
    private static final HttpUtils ourInstance = new HttpUtils();
    private Constant mConstant;

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
    }

    //获取数据
    public void getData(){
        //拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit build1 = new Retrofit.Builder()
                .client(build)
                .baseUrl("http://172.17.8.100/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mConstant = build1.create(Constant.class);
    }

    public Constant getConstant(){
        getData();
        return mConstant;
    }
}
