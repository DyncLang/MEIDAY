package com.dync.lib.http.Imageloader.bender;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ViewTarget;
import com.dync.lib.AbsApplication;
import com.dync.lib.http.Imageloader.GlideCircleTransform;
import com.dync.lib.http.Imageloader.ImageLoaderUtil;
import com.dync.lib.util.AppUtils;
import com.dync.lib.util.SettingUtils;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Xiao_Bailong on 2016/5/26 0026.
 * Class Note:
 * provide way to load image
 */
public class GlideImageLoaderProvider extends BaseImageLoaderProvider {
    @Override
    public void loadImage(Context ctx, ImageLoader img) {

        boolean flag = SettingUtils.getOnlyWifiLoadImg(ctx);
        //如果不是在wifi下加载图片，直接加载
        if (!flag) {
            loadNormal(ctx, img);
            return;
        }

        int strategy = img.getStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = AppUtils.getNetWorkType(AbsApplication.app());

            if (netType == AppUtils.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img);
            } else {
                loadCache(ctx, img);
            }
        } else {
            loadNormal(ctx, img);
        }

    }

    @Override
    public void loadImage(Context ctx, ImageLoader img, ViewTarget target) {
        loadCache(ctx,img,target);
    }


    /**
     * load image with Glide
     */
    private void loadNormal(Context ctx, ImageLoader img) {

        switch (ImageLoader.LOAD_TYPE.values()[img.getType()]) {
            case CIRCLE:
                Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).transform(new GlideCircleTransform(ctx)).into(img.getImgView());
                //圆图
                break;
            case BLUR:
                //高斯模糊
                Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).bitmapTransform(new BlurTransformation(ctx, 20)).into(img.getImgView());
                break;
            case LARGE:
                Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).into(img.getImgView());
            default:
                Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).into(img.getImgView());
                break;
        }

    }


    /**
     * load cache image with Glide
     */
    private void loadCache(Context ctx, ImageLoader img) {
        switch (ImageLoader.LOAD_TYPE.values()[img.getType()]) {
            case CIRCLE:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideCircleTransform(ctx)).into(img.getImgView());
                //圆图
                break;
            case BLUR:
                //高斯模糊
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).bitmapTransform(new BlurTransformation(ctx, 20)).into(img.getImgView());
                break;
            case LARGE:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(img.getImgView());
            default:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(img.getImgView());
                break;
        }

    }

    /**
     * load cache image with Glide
     */
    private void loadCache(Context ctx, ImageLoader img, ViewTarget target) {
        switch (ImageLoader.LOAD_TYPE.values()[img.getType()]) {
            case CIRCLE:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideCircleTransform(ctx)).into(target);
                //圆图
                break;
            case BLUR:
                //高斯模糊
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).bitmapTransform(new BlurTransformation(ctx, 20)).into(target);
                break;
            case LARGE:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(target);
            default:
                Glide.with(ctx).load(getFuckUrl(img.getUrl())).placeholder(img.getPlaceHolder()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(target);
                break;
        }

    }


    public static String getFuckUrl(String url) {
        if (url != null && url.startsWith("http://ear.duomi.com/wp-content/themes/headlines/thumb.php?src=")) {
            url = url.substring(url.indexOf("=") + 1, url.indexOf("jpg") > 0 ? url.indexOf("jpg") + 3 : url.indexOf("png") > 0 ? url.indexOf("png") + 3 : url.length());
            url = url.replace("kxt.fm", "ear.duomi.com");
        }
        return url;
    }
}
