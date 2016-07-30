package com.dync.lib.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.dync.lib.R;
import com.dync.lib.util.SharePrefUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Skyline on 2016/5/24.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String TAG = null;// Log tag
    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    public boolean isNight;
    public Context mContext;
    protected Activity mActivity;
    private ImageView ivShadow;
    private Unbinder unbindr;
    private String[] permissionGroup;
    protected AppManager appManager;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       // isNight = SharePrefUtil.isNight();
        //setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);
        this.setContentView(this.getLayoutId());

        mContext = this;
        mActivity = this;


        unbindr = ButterKnife.bind(this);
        TAG = this.getClass().getSimpleName();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        this.initView(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        unbindr.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNight != SharePrefUtil.isNight()) {
            reload();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    /**
     * replaceFragment (resLayId)
     *
     * @param resLayId
     * @param fragment
     * @param isAddBackStack
     */
    protected void replaceFragment(int resLayId, Fragment fragment, boolean isAddBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,
                R.anim.slide_out_right);
        fragmentTransaction.replace(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    /**
     * replaceFragment
     *
     * @param resLayId
     * @param fragment
     * @param isAddBackStack
     * @param isAnimation
     */
    protected void replaceFragment(int resLayId, Fragment fragment, boolean isAddBackStack, boolean isAnimation) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        fragmentTransaction.replace(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    protected void addFragment(int resLayId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right, R.anim.slide_in_left,
                R.anim.slide_out_right);
        fragmentTransaction.add(resLayId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * addFragment(int resLayId,Fragment
     * fragment,boolean isAddBackStack,Fragment ...hideFragments){
     *
     * @param resLayId
     * @param fragment
     * @param isAddBackStack
     */
    protected void addFragment(int resLayId, Fragment fragment, Fragment hideFragment, boolean isAddBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right, R.anim.slide_in_left,
                R.anim.slide_out_right);
        if (hideFragment != null) {
            fragmentTransaction.hide(hideFragment);
        }
        fragmentTransaction.add(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 添加Fragment
     *
     * @param resLayId
     * @param showFragment
     * @param isAddBackStack
     * @param hideFragments  要隐藏的Fragment数组
     */
    protected void addFragment(int resLayId, Fragment showFragment, boolean isAnimation, boolean isAddBackStack, Fragment... hideFragments) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        if (hideFragments != null) {
            for (Fragment hideFragment : hideFragments) {
                if (hideFragment != null) {
                    fragmentTransaction.hide(hideFragment);
                }
            }
        }
        if (showFragment.isAdded()) {
            fragmentTransaction.show(showFragment);
        } else {
            fragmentTransaction.add(resLayId, showFragment);
            fragmentTransaction.show(showFragment);
        }
//		fragmentTransaction.replace(resLayId,showFragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
//		fragmentTransaction.commitAllowingStateLoss();
        fragmentTransaction.commit();
    }

    /**
     * @param resLayId
     * @param showFragment
     * @param isAnimation
     * @param isAddBackStack
     * @param tag
     */
    protected void addFragment(int resLayId, Fragment showFragment, boolean isAnimation, boolean isAddBackStack, int tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("" + tag);
        if (findFragmentByTag == null) {
            fragmentTransaction.add(resLayId, showFragment, tag + "");
        }

        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * @param resLayId
     * @param showFragment
     * @param isAnimation
     * @param isAddBackStack
     * @param tag
     * @param hideFragments
     */
    protected void addFragment(int resLayId, Fragment showFragment, boolean isAnimation, boolean isAddBackStack, int tag,
                               Fragment... hideFragments) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("" + tag);
        if (findFragmentByTag == null) {
            fragmentTransaction.add(resLayId, showFragment, tag + "");
        } else {
            for (int i = 0; i < hideFragments.length; i++) {
                fragmentTransaction.hide(hideFragments[i]);
            }
            fragmentTransaction.show(findFragmentByTag);
        }

        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * showHideFragment
     *
     * @param showFragment
     * @param hideFragments
     * @param isAddBackStack
     */
    public void showHideFragment(Fragment showFragment, boolean isAddBackStack, Fragment... hideFragments) {
        if (showFragment == null)
            return;
        if (showFragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
            if (hideFragments != null) {
                for (Fragment hideFragment : hideFragments) {
                    if (hideFragment != null) {
                        fragmentTransaction.hide(hideFragment);
                    }
                }
            }
            fragmentTransaction.show(showFragment);
            if (isAddBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }

    /**
     * remove fragment
     *
     * @param fragment
     */
    protected void removeFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    /**
     * 带有动画的启动Activity
     *
     * @param cls
     */
    public void startAnimActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void finishAnimActivity() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void startAnimActivity(Class<?> cls, boolean isFinish) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (isFinish)
            finish();
    }

    protected void startAnimActivity(Class<?> cls, String key, String value) {

        Intent intent = new Intent(this, cls);
        intent.putExtra(key, value);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    /**
     * Eventbus callback message
     *
     * @Tode JavaBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {

    }

//    ProgressDialog pd;
//
//    private void ProgressDialog(Context ctx) {
//        pd = new ProgressDialog(ctx);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd.setCancelable(true);
//        pd.setCanceledOnTouchOutside(false);
//        pd.show();
//    }
//
//    public void showProgressDialog() {
//        if (pd == null) {
//            ProgressDialog(this);
//        }
//        pd.setMessage("加载中...");
//        pd.show();
//    }
//
//    public void showProgressDialog(String message) {
//        if (pd == null) {
//            ProgressDialog(this);
//        }
//        pd.setMessage(message);
//        pd.show();
//    }
//
//    public void hiddenProgressDialog() {
//        if (pd.isShowing()) {
//            pd.cancel();
//        }
//    }
}
