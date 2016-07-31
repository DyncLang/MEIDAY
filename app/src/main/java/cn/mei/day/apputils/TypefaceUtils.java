package cn.mei.day.apputils;

import android.graphics.Typeface;

import cn.mei.day.AppApplacation;

/**
 * Created by Xiao_Bailong on 2016/7/31 0031.
 *
 *  过去字体
 */
public class TypefaceUtils {
    private static Typeface HelveticaLight;
    private static Typeface Chines_lantingheijian;
    private static Typeface COUTURE_Bold;

    public static Typeface getHelveticaLight()
    {
        if (HelveticaLight == null) {
            HelveticaLight = Typeface.createFromAsset(AppApplacation.app().getAssets(), "fonts/HelveticaLight.ttf");
        }
        return HelveticaLight;
    }

    public static Typeface getChines_lantingheijian()
    {
        if (Chines_lantingheijian == null) {
            Chines_lantingheijian = Typeface.createFromAsset(AppApplacation.app().getAssets(), "fonts/Chines_lantingheijian.ttf");
        }
        return Chines_lantingheijian;
    }

    public static Typeface getCOUTURE_Bold()
    {
        if (COUTURE_Bold == null) {
            COUTURE_Bold = Typeface.createFromAsset(AppApplacation.app().getAssets(), "fonts/COUTURE-Bold.ttf");
        }
        return COUTURE_Bold;
    }

}
