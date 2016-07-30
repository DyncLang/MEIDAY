package com.dync.lib.http.Imageloader.bender;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by Xiao_Bailong on 2016/5/26 0026.
 * Class Note:
 * abstract class defined to load image
 */
public abstract class BaseImageLoaderProvider<T extends View> {
    public abstract void loadImage(Context ctx, ImageLoader img);
    public abstract void loadImage(Context ctx, ImageLoader img, ViewTarget<T, GlideDrawable> target);
}
