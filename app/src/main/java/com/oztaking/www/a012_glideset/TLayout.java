package com.oztaking.www.a012_glideset;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * @function
 */

public class TLayout extends LinearLayout{

    private ViewTarget<TLayout,GlideDrawable> viewTarget;

    public TLayout(Context context) {
        super(context);
    }

    public TLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        viewTarget = new ViewTarget<TLayout,GlideDrawable>(this){
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                TLayout tLayout = getView();
                tLayout.setBackground(resource);
            }
        };

    }

    public ViewTarget<TLayout, GlideDrawable> getViewTarget() {
        return viewTarget;
    }
}
