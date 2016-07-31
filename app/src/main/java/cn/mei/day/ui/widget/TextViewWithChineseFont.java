package cn.mei.day.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import cn.mei.day.apputils.TypefaceUtils;

/**
 * Created by Xiao_Bailong on 2016/7/31 0031.
 */

public class TextViewWithChineseFont extends TextView {
    public TextViewWithChineseFont(Context context) {
        super(context);
        initTypeFace();
    }

    public TextViewWithChineseFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace();
    }

    public TextViewWithChineseFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace();
    }

    private void initTypeFace() {
        setTypeface(TypefaceUtils.getChines_lantingheijian());
    }
}
