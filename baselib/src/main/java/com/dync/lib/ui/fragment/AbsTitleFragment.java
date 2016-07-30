package com.dync.lib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dync.lib.R;

import butterknife.ButterKnife;


/**
 * Created by xiaobailong on 2016/5/11.
 * Class Note:
 * 1 Same operation like AbaBaseFragment,(title+content)
 */
public abstract class AbsTitleFragment extends BaseFragment {
    protected View mTopBar;
    protected View mContent;
    private FrameLayout topLayout;
    private FrameLayout centerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewID() != 0) {
            View mRootView = inflater.inflate(getContentViewID(), null);

            topLayout = (FrameLayout) mRootView.findViewById(R.id.fragment_title);
            centerLayout = (FrameLayout) mRootView.findViewById(R.id.fragment_content);
            if (getTopBarViewID() != 0) {
                mTopBar = LayoutInflater.from(getActivity()).inflate(getTopBarViewID(), null);
                if (topLayout != null && mTopBar != null) {
                    topLayout.addView(mTopBar, 0);
                }
            } else {
                topLayout.setVisibility(View.GONE); //no title to show
            }


            if (getCenterViewID() != 0) {

                mContent = LayoutInflater.from(getActivity()).inflate(getCenterViewID(), null);
                if (centerLayout != null && mContent != null) {
                    centerLayout.addView(mContent, 0);
                }
            }

            unbinder = ButterKnife.bind(this, mRootView);
            initViewsAndEvents(mRootView, savedInstanceState);
            return mRootView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    protected abstract int getCenterViewID();

    protected abstract int getTopBarViewID();

    @Override
    protected int getContentViewID() {
        return R.layout.abs_fragment_title;
    }


}


