package com.oztaking.www.a012_glideset;

import android.graphics.Bitmap;
import android.graphics.Color;
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

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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

    private String url_baidulogo = "https://www.baidu.com/img/bd_logo1.png";
    private String url7 = "http://cn.bing.com/az/hprichbg/rb/AvalancheCreek_ROW11173354624_1920x1080.jpg";
    private String url8 = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=fd635a6d9122720e7bcee5fc43f06d7b/bba1cd11728b47104d8602c3cacec3fdfc032310.jpg";


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
//                TListener(url5);
//                TImageTransform(url_baidulogo);
//                TImageTransform(url7);
//                CircleCrop(url7);

//                TTransformBlurTransformation(url7);
//                TTransformGrayscaleTransformation(url1);
//                TTransformGBTransformation(urlGif);
                TTransformMultiTransformation(url8);
            }});

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
                        Logger.i("TListener.onResourceReady()");
                        Logger.i(resource.toString());
//                        return true;
                        return false;
                    }
                })
                .into(mImageView);
    }

    /**
     * [6]添加和取消图片的变换
     * 只需要借助override()方法强制将图片尺寸指定成原始大小
     */
    private void TImageTransform(String url){
//        RequestManager with = Glide.with(this);
//        DrawableTypeRequest<String> load = with.load(url);
//        DrawableRequestBuilder<String> stringDrawableRequestBuilder = load.dontTransform();
//        Target<GlideDrawable> into = load.into(mImageView);

//        Glide.with(this)
//                .load(url)
//                .dontTransform()
//                .into(mImageView);

//        Glide.with(this)
//                .load(url)
//                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
//                .into(mImageView);

//        Glide.with(this)
//                .load(url)
//                .centerCrop()
//                .into(mImageView);

        Glide.with(this)
                .load(url)
                .fitCenter()
                .into(mImageView);
    }


    /**
     * [7]对图片进行圆形处理--增加了对圆形图片描边的效果；
     * 新建类：CircleCrop
     */

    private void CircleCrop(String url){
//        Glide.with(this)
//                .load(url)
//                .transform(new CircleCrop(this))
//                .into(mImageView);


        //带白色边框的圆形图片加载
        Glide.with(this).
                load(url)
                .centerCrop()
                .transform(new CircleCrop(getApplicationContext(),5,Color.WHITE))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }


    /**
     * [8]图形变换的使用:使用第三方库：
     * glide-transformations的项目主页地址是 https://github.com/wasabeef/glide-transformations
     *
     * 参考文章：https://www.cnblogs.com/qianyukun/p/6867436.html
     * BlurTransformation ---0 模糊效果
     * GrayscaleTransformation ---1 黑白效果
     * BlurTransformation+GrayscaleTransformation：黑白+模糊；
     */

    private void TTransformBlurTransformation(String url){
        Glide.with(this)
                .load(url)
                .bitmapTransform(new BlurTransformation(this))
                .into(mImageView);
    }

    private void TTransformGrayscaleTransformation(String url){
        Glide.with(this)
                .load(url)
                .bitmapTransform(new GrayscaleTransformation(this))
                .into(mImageView);

    }

    private void TTransformGBTransformation(String url){
        Glide.with(this)
                .load(url)
                .bitmapTransform(new BlurTransformation(this),new GrayscaleTransformation(this))
                .into(mImageView);
    }

    /**
     *
     *  BlurTransformation--模糊
     *  ColorFilterTransformation --颜色遮盖
     *  CropCircleTransformation --圆形
     *  CropSquareTransformation --正方形剪裁
     *  CropTransformation--自定义尺寸的尺寸的剪裁；
     *  GrayscaleTransformation --黑白
     *  MaskTransformation--遮盖效果
     *  RoundedCornersTransformation--圆角剪裁效果；
     *
     * @param url
     */


    private void TTransformMultiTransformation(String url){

//        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(getApplicationContext(),25),
//                new RoundedCornersTransformation(getApplicationContext(),128, 0, RoundedCornersTransformation.CornerType.ALL),
//        new CropSquareTransformation(getApplicationContext())
//        );

        BlurTransformation blurTransformation = new BlurTransformation(getApplicationContext(), 25);
        ColorFilterTransformation colorFilterTransformation = new ColorFilterTransformation(getApplicationContext(), Color.WHITE);
        CropCircleTransformation cropCircleTransformation = new CropCircleTransformation(getApplicationContext());
        CropSquareTransformation cropSquareTransformation = new CropSquareTransformation(getApplicationContext());
        CropTransformation crpTransformation = new CropTransformation(getApplicationContext(), 500, 500);
        GrayscaleTransformation grayscaleTransformation = new GrayscaleTransformation(getApplicationContext());
        MaskTransformation maskTransformation = new MaskTransformation(getApplicationContext(), R.mipmap.ic_launcher);

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(getApplicationContext(), 50, 20);


        Glide.with(this)
                .load(url)
                .bitmapTransform(roundedCornersTransformation)
                .into(mImageView);
    }







}
