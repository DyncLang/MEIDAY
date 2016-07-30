package com.dync.lib.http.Imageloader.bender;

import android.widget.ImageView;

import com.dync.lib.R;
import com.dync.lib.http.Imageloader.ImageLoaderUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Xiao_Bailong on 2016/5/26 0026.
 * Class Note:
 * encapsulation of ImageView,Build Pattern used
 */
public class ImageLoader {
    public static enum LOAD_TYPE {
        LARGE,//大图
        MEDIUM,//中等图
        BLUR,//高斯
        CIRCLE,//圆图
        SMALL//小图

    }

    private int type;  //类型 (大图，中图，小图)
    private String url; //需要解析的url
    private int placeHolder; //当没有成功加载的时候显示的图片
    private ImageView imgView; //ImageView的实例
    private int strategy;//加载策略，是否在wifi下才加载


    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imgView = builder.imgView;
        this.strategy = builder.strategy;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public int getStrategy() {
        return strategy;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private ImageView imgView;
        private int strategy;

        /**
         * 默认加载的是缩略图图
         */
        public Builder() {
            this.type = LOAD_TYPE.MEDIUM.ordinal();
            this.url = "";
            this.imgView = null;
            this.strategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
            this.placeHolder = R.drawable.icon_photo;
        }

        /**
         * 设置加载图片的模式
         * LARGE,MEDIUM,BLUR,CIRCLE,SMALL
         *
         * @param type
         * @return
         */
        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder smallUrl(String sUrl) {
            if (sUrl != null) {
                try {
                    JSONObject jsonObject = new JSONObject(sUrl);
                    String thumbnailurl = jsonObject.getString("thumbnailurl");
                    this.url = thumbnailurl;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return this;
        }

        public Builder bigUrl(String sUrl) {
            if (sUrl != null) {
                try {
                    JSONObject jsonObject = new JSONObject(sUrl);
                    String thumbnailurl = jsonObject.getString("imageurl");
                    this.url = thumbnailurl;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return this;
        }


        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.strategy = strategy;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }
}
