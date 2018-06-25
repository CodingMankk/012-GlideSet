package com.oztaking.www.a012_glideset;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity {

    private TLayout mTLayout;

    private Button mBtnLoadImg;
    private ImageView mImageView;

    private String url1 = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    private String url2 = "http://img5.imgtn.bdimg.com/it/u=394265007,236680862&fm=200&gp=0.jpg";
    private String urlGif = "http://p1.pstatp.com/large/166200019850062839d3";

    private String urlError = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2385237309,131922640&fm=27&gp=0.jpg";


    private String urlBaiduYun = "https://pan.baidu.com/s/14Sz-DZM6jVXJdFfbrjeaPw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_tlayout_main);

        mTLayout = (TLayout) findViewById(R.id.tlayout);

        mBtnLoadImg = (Button) findViewById(R.id.Btn_LoadImageView);
        mImageView = (ImageView) findViewById(R.id.imagView);

        mBtnLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });


        loadImageView();
        loadTLayout();

    }


    private void loadImageView() {
        Glide.with(getApplicationContext())
                .load(urlGif)
                .override(50, 50)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
    }


    /**
     * into()方法还可以传入别的参数:
     * [1]简单的simpleTarget；
     */
    private void loadSimpleTarget() {



        SimpleTarget<GlideDrawable> simpleTarget1 = new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                mImageView.setImageDrawable(resource);
            }
        };


        SimpleTarget<Bitmap> simpleTarget2 = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mImageView.setImageBitmap(resource);
            }
        };

        Glide.with(getApplicationContext())
                .load(urlGif)
                .override(50, 50)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//             .into(simpleTarget2);
                .into(simpleTarget1);
    }

    /**
     * into()方法还可以传入别的参数:
     * [1]简单的simpleTarget；
     */


    //
    private void loadTLayout() {
        Glide.with(this)
                .load(url1)
                .into(mTLayout.getViewTarget());
    }


}
