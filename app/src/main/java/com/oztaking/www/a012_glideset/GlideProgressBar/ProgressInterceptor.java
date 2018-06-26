package com.oztaking.www.a012_glideset.GlideProgressBar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @function
 */

public class ProgressInterceptor implements Interceptor{

    /**
     * 使用了一个Map来保存注册的监听器，Map的键是一个URL地址。
     * 可能会使用Glide同时加载很多张图片必须要能区分出来每个下载进度的回调到底是对应哪个图片URL地址的
     */
    public  static final Map<String,ProgressListener> LISTENER_MAP = new HashMap<>();

    /**
     * [1]创建一个没有任何逻辑的空拦截器，新建ProgressInterceptor类并实现Interceptor接口
     * 拦截到了OkHttp的请求，然后调用proceed()方法去处理这个请求，最终将服务器响应的Response返回
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //获得添加进度之后的response；
        Response response = chain.proceed(request);
        //获取url
        String url = request.url().toString();
        //获取现在的body;
        ResponseBody body = response.body();
        //使用添加进度之后的body替换原来的body；
        Response newResponse = response.newBuilder().body(new ProgressResponseBody(url, body)).build();
        return newResponse;
    }


    /**
     * 在集合中添加和删除拦截记录；
     */
    public static void addListener(String url,ProgressListener listener){
        LISTENER_MAP.put(url,listener);
    }

    public static void removeListener(String url){
        LISTENER_MAP.remove(url);
    }

}
