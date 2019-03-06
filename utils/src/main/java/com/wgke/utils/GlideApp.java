package com.wgke.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wgke.utils.callback.FileCallback;
import com.wgke.utils.callback.GlideListener;

import java.io.File;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide工具类
 */
public class GlideApp {
    private Context context;
    private RequestBuilder<Drawable> builder;
    RequestOptions options;
    RequestListener<Drawable> listener;

    public GlideApp(Context context) {
        this.context = context;
    }

    /**
     * 初始化
     */
    public static GlideApp with(Context context) {
        GlideApp app = new GlideApp(context);
        return app;
    }

    /**
     * 网络图片
     */
    public GlideApp load(String url) {
        builder = Glide.with(context).load(url);
        return this;
    }

    /**
     * 网络图片
     */
    public GlideApp load(int res) {
        builder = Glide.with(context).load(res);
        return this;
    }

    /**
     * 本地图片
     */
    public GlideApp load(File res) {
        builder = Glide.with(context).load(res);
        return this;
    }

    /**
     * bitmap图片
     */
    public GlideApp load(Bitmap res) {
        builder = Glide.with(context).load(res);
        return this;
    }

    /**
     * Drawable
     */
    public GlideApp load(Drawable res) {
        builder = Glide.with(context).load(res);
        return this;
    }


    /**
     * 加载占位图
     */
    public GlideApp placeholder(int res) {
        if (options == null)
            options = new RequestOptions().centerCrop();
        options.placeholder(res);
        return this;
    }

    /**
     * 加载失败
     */
    public GlideApp error(int res) {
        if (options == null)
            options = new RequestOptions().centerCrop();
        options.error(res);
        return this;
    }

    /**
     * 圆处理
     */
    public GlideApp circleCrop() {
        if (options == null)
            options = new RequestOptions().centerCrop();
        options.circleCrop();
        return this;
    }

    /**
     * 圆角处理
     */
    public GlideApp rounded(int dip) {
        if (options == null)
            options = new RequestOptions().centerCrop();
        options.bitmapTransform(new RoundedCornersTransformation(dip2px(context, dip), 0, RoundedCornersTransformation.CornerType.ALL));
        return this;
    }


    /**
     * 加载图片
     */
    public void into(ImageView imageView) {
        if (builder != null) {
            if (options != null)
                builder.apply(options.diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
            else builder.listener(listener).into(imageView);
        }
    }

    /**
     * 中止加载
     */
    public void close() {
        if (context != null) {
            Glide.with(context).onStop();
        }
    }

    /**
     * 下载bitmap
     */
    public void downBitmap(int w, int h, final GlideListener listener) {
        if (builder != null) {
            builder.into(new SimpleTarget<Drawable>(w, h) {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    if (listener != null)
                        listener.callback(resource);
                }
            });
        }
    }

    /**
     * 动画加载
     */
    public GlideApp dontAnimate() {
        if (options == null)
            options = new RequestOptions().centerCrop();
        options.dontAnimate();
        return this;
    }

    //dp和px的转换关系比例
    private int dip2px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    /**
     * 用来监听加载是否完成，然后完成后续逻辑
     */
    public GlideApp listener(final FileCallback callback) {
        listener = new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (callback != null)
                    callback.result("");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (callback != null)
                    callback.result("");
                return false;
            }
        };
        return this;
    }
}
