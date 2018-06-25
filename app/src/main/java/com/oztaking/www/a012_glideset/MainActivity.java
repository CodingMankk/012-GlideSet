package com.oztaking.www.a012_glideset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        mBtnLoadImg = (Button) findViewById(R.id.Btn_LoadImageView);
        mImageView = (ImageView) findViewById(R.id.imagView);

        mBtnLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext())
                        .load(urlGif)
//                        .asBitmap()
                        .asGif()
                        .override(50,50)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(mImageView);
            }
        });

    }
}
