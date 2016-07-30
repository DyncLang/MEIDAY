package com.dync.lib.http.Imageloader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ViewTarget;
import com.dync.lib.http.Imageloader.bender.BaseImageLoaderProvider;
import com.dync.lib.http.Imageloader.bender.GlideImageLoaderProvider;
import com.dync.lib.http.Imageloader.bender.ImageLoader;

/**
 * Created by Xiao_Bailong on 2016/5/26 0026.
 */
public class ImageLoaderUtil<T extends View> {

    public static final int LOAD_STRATEGY_NORMAL = 0;
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;

    private static ImageLoaderUtil mInstance;
    private BaseImageLoaderProvider mProvider;

    private ImageLoaderUtil() {
        mProvider = new GlideImageLoaderProvider();
    }

    //single instance
    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * ImageLoader imageLoader = new ImageLoader.Builder().url(url).imgView(mUserImg).build();
     * ImageLoaderUtil.getInstance().loadImage(this, imageLoader);
     *
     * @param context
     * @param img
     */

    public void loadImage(Context context, ImageLoader img) {
        mProvider.loadImage(context, img);
    }

    /**
     * ImageLoader imageLoader = new ImageLoader.Builder().url(url).imgView(mUserImg).build();
     * ImageLoaderUtil.getInstance().loadImage(this, imageLoader);
     *
     * @param context
     * @param img
     * @param target
     */
    public void loadImage(Context context, ImageLoader img, ViewTarget<T, GlideDrawable> target) {
        mProvider.loadImage(context, img,target);
    }

}
