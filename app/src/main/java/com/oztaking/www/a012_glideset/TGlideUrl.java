package com.oztaking.www.a012_glideset;

import com.bumptech.glide.load.model.GlideUrl;

/**
 * @function 针对加载图片的url后缀增加Token的解决方式；
 *
 * 使用方法：
 * Glide.with().load(new TGlideUrl(url)).into(imageView);
 */

public class TGlideUrl extends GlideUrl{

    private String mUrl;


    public TGlideUrl(String url) {
        super(url);
        mUrl = url;
    }

    @Override
    public String getCacheKey() {
//        return super.getCacheKey();
        //将token替换为空;
        return mUrl.replace(findTokenParam(),"");
    }

    /**
     *将图片url地址中token参数的这一部分移除掉
     * @return
     */
    private String findTokenParam() {
        String tokenParam ="";
        int tokenKeyIndex = mUrl.indexOf("?token=") >0? mUrl.indexOf("?token="):mUrl
                .indexOf("&token=");
        if (tokenKeyIndex != -1){
            int nextSubIndex = mUrl.indexOf("&",tokenKeyIndex+1);
            if (nextSubIndex != -1){
                tokenParam = mUrl.substring(tokenKeyIndex+1,nextSubIndex+1);
            }else{
                tokenParam = mUrl.substring(tokenKeyIndex);
            }
        }

        return tokenParam;
    }


}
