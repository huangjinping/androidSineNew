package com.sineverything.news;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.activity.GuideActivity;
import com.sineverything.news.ui.main.activity.MainActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_name)
    TextView tvName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(4000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    RxPermissions rxPermissions = new RxPermissions(SplashActivity.this); // where this is an Activity instance
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {
                                toAction();
                                finish();
                                return;
                            } else {
                                showAlert();
                                showLongToast("请打开权限");
                                return;
                            }
                        }
                    });
                } else {
                    toAction();
                    finish();
                    return;
                }


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    /**
     * 草拟吗
     */
    private void toAction() {
        boolean first = UserManager.isFirst(this);
        if (first) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            return;
        }
        MainActivity.startAction(SplashActivity.this);

    }


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("由于您开启的权限过少，从而影响到使用，请设置权限");
        builder.setPositiveButton("去设置", new android.content.DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myAppSettings);
                SplashActivity.this.finish();
            }
        });
        builder.show();


    }


}
