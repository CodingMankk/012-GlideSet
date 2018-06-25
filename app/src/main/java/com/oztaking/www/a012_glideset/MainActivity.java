package com.oztaking.www.a012_glideset;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TLayout mTLayout;

    private Button mBtnLoadImg;
    private ImageView mImageView;

    private String url1 = "http://cn.bing" +
            ".com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    private String url2 = "http://img5.imgtn.bdimg.com/it/u=394265007,236680862&fm=200&gp=0.jpg";
    private String url3 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1677785443,2608802960&fm=27&gp=0.jpg";
    private String url4 = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3561673955,3107573814&fm=85&app=2&f=JPEG?w=121&h=75&s=2D62CB020D922FCE66C5641C0100A092";
    private String url5 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3346824393,2161129631&fm=27&gp=0.jpg";
    private String url6 = "https://www.baidu.com/img/bd_logo1.png";
    private String urlGif = "http://p1.pstatp.com/large/166200019850062839d3";
    private String urlError = "https://ss1.bdstatic" +
            ".com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2385237309,131922640&fm=27&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_tlayout_main);

        Logger.init("MainActivity");

        mTLayout = (TLayout) findViewById(R.id.tlayout);

        mBtnLoadImg = (Button) findViewById(R.id.Btn_LoadImageView);
        mImageView = (ImageView) findViewById(R.id.imagView);

        //提前预加载图片
//        cacheLoadPic();
        mBtnLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSimpleTarget();
//                TDownLoadOnly(url3);
//                loadImageView(urlGif);
//                TDownloadOnlyY(url4);
//                loadImageView(url4);
                TListener(url5);
            }
        });

        //loadImageView();
//        loadTLayout(url3);
    }


    /**
     * [1]普通加载图片
     */
    private void loadImageView(String url) {
        Glide.with(getApplicationContext())
                .load(url)
                //                .load(urlBaiduYun)
                //                .override(50, 50)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                //                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }


    /**
     * [2]
     * into()方法还可以传入别的参数:
     * [2-1]简单的simpleTarget；
     */
    private void loadSimpleTarget() {
        SimpleTarget<GlideDrawable> simpleTarget1 = new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                    GlideDrawable> glideAnimation) {
                mImageView.setImageDrawable(resource);
            }
        };


        SimpleTarget<Bitmap> simpleTarget2 = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap>
                    glideAnimation) {
                mImageView.setImageBitmap(resource);
            }
        };

        Glide.with(getApplicationContext())
                .load(urlGif)
                //                .override(50, 50)
                //                .placeholder(R.drawable.loading)
                //                .error(R.drawable.error)
                //                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //                .into(simpleTarget2);
                .into(simpleTarget1);
    }

    /**
     * into()方法还可以传入别的参数:
     * [2-2]为Layout加载图片
     */
    private void loadTLayout(String url) {
        Glide.with(this)
                .load(url)
                .into(mTLayout.getViewTarget());
    }

    /**
     * [3]提前预加载图片-preload
     * 使用的时候配合：loadImageView一起使用，url地址需要一致，否则预加载无效；
     */
    private void cacheLoadPic() {
        Glide.with(this)
                .load(urlGif)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();

    }


    /**
     * [4]downloadOnly
     * downloadOnly()方法表示只会下载图片，而不会对图片进行加载。
     * 当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作
     * downloadOnly(int width, int height)--->子线程中下载图片的，
     * downloadOnly(Y target)-->主线程中下载图片
     */

    private void TDownLoadOnly(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //预加载图片
                    final FutureTarget<File> target = Glide.with(getApplicationContext())
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    //获取下载的文件；
                    final File file = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //打印获得的文件的名称；
                            Toast.makeText(getApplicationContext(), file.getPath(),
                                    Toast.LENGTH_LONG).show();
                            Logger.i(file.getPath());
//                            Glide.with(getApplicationContext())
//                                    .load(url)
//                                    .into(mImageView);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    /**
     * [4]downloadOnly
     * downloadOnly()方法表示只会下载图片，而不会对图片进行加载
     * downloadOnly(Y target)-->主线程中下载图片
     * @param url
     *
     * 需要配合类：DownloadImageTarget
     */
    private void TDownloadOnlyY(String url){
        Glide.with(getApplicationContext())
                .load(url)
                .downloadOnly(new DownloadImageTarget());
    }

    private class DownloadImageTarget implements Target<File>{

        @Override
        public void onLoadStarted(Drawable placeholder) {

        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {

        }

        //必须实现
        @Override
        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
            Toast.makeText(getApplicationContext(), resource.getPath(),
                    Toast.LENGTH_LONG).show();
            Logger.i(resource.getPath());

        }

        @Override
        public void onLoadCleared(Drawable placeholder) {

        }

        //必须实现
        @Override
        public void getSize(SizeReadyCallback cb) {
            cb.onSizeReady(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL);

        }

        @Override
        public void setRequest(Request request) {

        }

        @Override
        public Request getRequest() {
            return null;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onDestroy() {

        }
    }


    /**
     * [5]Listener监听方法
     */
    private void TListener(String url){
        Glide.with(getApplicationContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable>
                            target, boolean isFirstResource) {
                        Logger.i("TListener.onException");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Logger.i(resource.toString());
//                        return true;
                        return false;
                    }
                })
                .into(mImageView);
    }




}
