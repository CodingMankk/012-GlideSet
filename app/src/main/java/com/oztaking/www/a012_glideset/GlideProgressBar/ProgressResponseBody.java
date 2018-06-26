package com.oztaking.www.a012_glideset.GlideProgressBar;

import android.support.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @function
 */

public class ProgressResponseBody extends ResponseBody{

    private static final String TAG = "ProgressResponseBody";

    private BufferedSource bufferedSource;
    private ResponseBody responseBody;

    //持有该接口的对象；
    private ProgressListener listener;

    public ProgressResponseBody(String url,ResponseBody responseBody) {
        this.responseBody = responseBody;
        //获取到拦截中的listener；
        listener = ProgressInterceptor.LISTENER_MAP.get(url);
        Logger.init("ProgressResponseBody");

    }


    /**
     * 在contentType()和contentLength()方法中直接就调
     * 用传入的原始ResponseBody的contentType()和contentLength()方法即可，这相当于一种委托模式
     * @return
     */
    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
        }
        return bufferedSource;
    }


    /**
     * url参数就是图片的url地址了，而ResponseBody参数则是OkHttp拦截到的原始的ResponseBody对象。
     * 然后在构造方法中，调用ProgressInterceptor中的LISTENER_MAP来去获取该url对应的监听器回调对象，
     * 有了这个对象，待会就可以回调计算出来的下载进度了
     * =========================
     * 继承自ForwardingSource的实现类。ForwardingSource也是一个使用委托模式的工具，它不处理
     * 任何具体的逻辑，只是负责将传入的原始Source对象进行中转。但是，我们使用ProgressSource继承自ForwardingSource，那么就可以在中转的过程中加入
     * 自己的逻辑了。
     * 可以看到，在ProgressSource中我们重写了read()方法，然后在read()方法中获取该次读取到的字节数
     * 以及下载文件的总字节数，并进行一些简单的数学计算
     * 就能算出当前的下载进度了。
     * 这里我先使用Log工具将算出的结果打印了一下，再通过前面获取到的回调监听器对象将结果进行回调
     *
     */
    private class ProgressSource extends ForwardingSource{

        long totalBytesRead = 0;
        int currentProgress;

        public ProgressSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            //读取到字符数；
            long bytesRead = super.read(sink, byteCount);
            //总长度；
            long fullLength = responseBody.contentLength();
            if (bytesRead == -1) {
                totalBytesRead = fullLength;
            } else {
                totalBytesRead += bytesRead;
            }

            //进度的计算
            int progress = (int) (100f * totalBytesRead / fullLength);
            Logger.d("progress:"+progress);
            Logger.d("+++++++++++++++++++++++");
            Log.d("ProgressResponseBody","progress:"+progress);

            if (listener != null && progress != currentProgress){
                listener.onProgress(progress); //更新进度；
            }

            //传输结束之后，listener置为null
            if (listener != null && totalBytesRead == fullLength){
                listener = null;
            }
            //记录当前的数据的长度；
            currentProgress = progress;
            return bytesRead;
        }
    }


}
