package com.example.likaixuan.recognitionsystem.activity;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.likaixuan.recognitionsystem.R;
import com.example.likaixuan.recognitionsystem.utils.FingerImageView;
import com.example.likaixuan.recognitionsystem.utils.FingerUtil;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bamboy on 2017/3/13.
 */
public class AssociationFingerActivity extends AppCompatActivity {
    /**
     * 指纹识别
     */
    private FingerUtil mFingerUtil;
    /**
     * 指纹监听
     */
    private FingerprintManagerCompat.AuthenticationCallback mFingerListen;

    private TextView tv_log;
    private TextView tv_finger;
    /**
     * 显示提示性动画的ImageView
     */
    private FingerImageView iv_finger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.association_finger);
        initViews();
        try {
                    // 初始化指纹按钮点击事件
                    initFingerBtnClick();
                } catch (Exception e) {
                    tv_log.setText("当前设备没有指纹识别模块\n或未遵循 Android M 指纹API规范");
                    // 指纹开启失败，显示失败图标
                    iv_finger.end(false);
                }
                // 显示提示文字
                ObjectAnimator.ofFloat(tv_log, "alpha", 0, 0.7f).setDuration(150).start();
    }
    private void initViews() {
        tv_log = (TextView) findViewById(R.id.tv_log);
        iv_finger = (FingerImageView) findViewById(R.id.iv_finger);
        tv_finger=(TextView)findViewById(R.id.tv_finger);
        iv_finger.setAnimation(AnimationUtils.loadAnimation(AssociationFingerActivity.this, R.anim.scale_bigger));
        tv_finger.setAnimation(AnimationUtils.loadAnimation(AssociationFingerActivity.this, R.anim.alpha));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                iv_finger.clearAnimation();
                tv_finger.clearAnimation();
                Animation animation=(AnimationUtils.loadAnimation(AssociationFingerActivity.this, R.anim.scale_smaller));
                Animation animation1=(AnimationUtils.loadAnimation(AssociationFingerActivity.this, R.anim.translate));
                iv_finger.startAnimation(animation);
                tv_finger.startAnimation(animation1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        iv_finger.setVisibility(View.GONE);
                        tv_finger.setVisibility(View.GONE);
                        Intent intent = new Intent(AssociationFingerActivity.this,FingerPrintAddSucceedActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 2300);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFingerUtil != null && mFingerListen != null) {
            mFingerUtil.startFingerListen(mFingerListen);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFingerUtil != null) {
            mFingerUtil.stopsFingerListen();
        }
    }

    /**
     * 初始化指纹按钮点击事件
     */
    private void initFingerBtnClick(){
        //检测设备是否有指纹识别模块
        if (false == checkFingerModule()) {
            tv_log.setText("当前设备没有指纹识别模块\n或未遵循 Android M 指纹API规范");
            // 不支持指纹，显示失败图标
            iv_finger.end(false);
            return;
        }

        // 检测是否需要初始化
        if (mFingerListen == null || mFingerUtil == null) {
            //初始化指纹识别
            // 指纹开启成功，显示指纹图标
            iv_finger.startGif();
        } else {
            // 启动指纹识别
            mFingerUtil.startFingerListen(mFingerListen);
            // 指纹开启成功，显示指纹图标
            iv_finger.startGif();
        }
    }

    /**
     * 检测是否有指纹模块
     *
     * @return 是否有指纹模块
     */
    private boolean checkFingerModule() {
        try {
            return ((FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE)).isHardwareDetected();
        } catch (Exception e) {
            return false;
        }
    }


}
