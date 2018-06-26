package com.oztaking.www.a012_glideset.okhttpmodule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.oztaking.www.a012_glideset.GlideProgressBar.ProgressInterceptor;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * @function
 *
 * [1] Glide内置ExternalCacheDiskCacheFactory，将加载的图片缓存到SD卡
 * [2] InternalCacheDiskCacheFactory和ExternalCacheDiskCacheFactory的默认硬盘缓存大小都是250M
 */


public class TGlideModule implements GlideModule{

    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,DISK_CACHE_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

    }

    //注册替换的module
    @Override
    public void registerComponents(Context context, Glide glide) {

        /**
         * 创建了一个OkHttpClient.Builder，然后调用addInterceptor()方法将刚才创建的ProgressInterceptor添加进去，最后将构建出来的新OkHttpClient对象
           传入到OkHttpGlideUrlLoader.Factory中即可
         */
        //添加启用拦截器代码
        //启用这个拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ProgressInterceptor());//添加拦截器；
        OkHttpClient okHttpClient = builder.build();
        glide.register(GlideUrl.class, InputStream.class,new OkHttpGlideUrlLoader.ClientFactory());
    }
}
