package com.dync.lib.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dync.lib.R;
import com.dync.lib.ui.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xiaobailong on 2016/5/24.
 */
public abstract class BaseFragment extends Fragment {
    protected static String TAG = null;
    protected Context mContext;
    protected Activity mActivity;
    protected View mContentView;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
        TAG = this.getClass().getSimpleName();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {

            mContentView = inflater.inflate(getContentViewID(), null);
            unbinder = ButterKnife.bind(this, mContentView);
            initViewsAndEvents(mContentView, savedInstanceState);

        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * replaceFragment
     *
     * @param resLayId
     * @param fragment
     * @param isAddBackStack
     */
    protected void replaceFragment(int resLayId, Fragment fragment, boolean isAddBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * addFragment
     *
     * @param resLayId
     * @param fragment
     * @param isAddBackStack
     */
    protected void addFragment(int resLayId, Fragment fragment, boolean isAddBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * addFragment
     *
     * @param resLayId
     * @param showFragment
     * @param isAddBackStack
     * @param hideFragments
     */
    protected void addFragment(int resLayId, Fragment showFragment, boolean isAnimation, boolean isAddBackStack, Fragment... hideFragments) {

    }

    /**
     * showHideFragment
     *
     * @param showFragment
     * @param hideFragments
     * @param isAddBackStack
     */
    protected void showHideFragment(Fragment showFragment, boolean isAddBackStack, Fragment... hideFragments) {
        ((BaseActivity) getActivity()).showHideFragment(showFragment, isAddBackStack, hideFragments);
    }

    /**
     * Eventbus callback message
     */
    @Subscribe
    public void onEventMainThread(Message msg) {

    }

    protected void startAnimActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void startAnimActivity(Class<?> cls, String key, String value) {

        Intent intent = new Intent(mContext, cls);
        intent.putExtra(key, value);
        mContext.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void startAnimActivity(Class<?> cls, boolean isFinish) {
        startAnimActivity(cls);
        if (isFinish) {
            getActivity().finish();
        }
    }

    /**
     * override this method to return content view id of the fragment
     */
    protected abstract int getContentViewID();

    /**
     * override this method to do operation in the fragment
     */
    protected abstract void initViewsAndEvents(View rootView, Bundle savedInstanceState);

}
