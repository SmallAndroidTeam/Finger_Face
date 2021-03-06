package com.example.likaixuan.recognitionsystem.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.likaixuan.recognitionsystem.R;

import java.util.Timer;
import java.util.TimerTask;

public class UserInfoActivity extends AppCompatActivity {
    private ImageView icon1;
    private ImageView back1;
    private TextView text1;
    private ImageView arrow;
    private ImageView back2;
    private ImageView icon2;
    private TextView text2;
    private TextView name;
    private TextView face;
    private TextView correlation;
    private TextView fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        initViewsClick();
        initAnims();
        nextPage();
    }

    private void initViewsClick() {
        // 为单个View添加点击事件
        icon1 = findViewById(R.id.icon_add1);
        back1 = findViewById(R.id.back1);
        text1 = findViewById(R.id.text1);
        arrow = findViewById(R.id.icon_arrow);
        back2 = findViewById(R.id.back2);
        icon2 = findViewById(R.id.icon_add2);
        text2 = findViewById(R.id.text2);
        name = findViewById(R.id.name);
        face = findViewById(R.id.face);
        correlation = findViewById(R.id.correlation);
        fingerprint = findViewById(R.id.fingerprint);
    }

    private void initAnims() {
        //初始化底部注册、登录的按钮动画
        //以控件自身所在的位置为原点，从下方距离原点200像素的位置移动到原点
        ObjectAnimator tranName = ObjectAnimator.ofFloat(name, "translationY", 500, 0);
        ObjectAnimator tranFingerprint = ObjectAnimator.ofFloat(fingerprint, "translationY", 500, 0);
        ObjectAnimator tranFace = ObjectAnimator.ofFloat(face, "translationY", 500, 0);
        ObjectAnimator tranCorrelation = ObjectAnimator.ofFloat(correlation, "translationY", 500, 0);
        ObjectAnimator tranBack1_X = ObjectAnimator.ofFloat(back1, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack1_Y = ObjectAnimator.ofFloat(back1, "scaleY", 0.5f, 1f);
        ObjectAnimator tranBack2_X = ObjectAnimator.ofFloat(back2, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack2_Y = ObjectAnimator.ofFloat(back2, "scaleY", 0.5f, 1f);
        ObjectAnimator icon1_X = ObjectAnimator.ofFloat(icon1, "scaleX", 0.5f, 1f);
        ObjectAnimator icon1_Y = ObjectAnimator.ofFloat(icon1, "scaleY", 0.5f, 1f);
        ObjectAnimator icon2_X = ObjectAnimator.ofFloat(icon2, "scaleX", 0.5f, 1f);
        ObjectAnimator icon2_Y = ObjectAnimator.ofFloat(icon2, "scaleY", 0.5f, 1f);
        ObjectAnimator arrow_X = ObjectAnimator.ofFloat(arrow, "scaleX", 0.1f, 1f);
        ObjectAnimator arrow_Y = ObjectAnimator.ofFloat(arrow, "scaleY", 0.1f, 1f);
        //将注册、登录的控件alpha属性从0变到1
        ObjectAnimator alphaLoName = ObjectAnimator.ofFloat(name, "alpha", 0, 1);
        ObjectAnimator alphaFingerprint = ObjectAnimator.ofFloat(fingerprint, "alpha", 0, 1);
        ObjectAnimator alphaFace = ObjectAnimator.ofFloat(face, "alpha", 0, 1);
        ObjectAnimator alphaCorrelation = ObjectAnimator.ofFloat(correlation, "alpha", 0, 1);
        ObjectAnimator alphaBack1 = ObjectAnimator.ofFloat(back1, "alpha", 0, 1);
        ObjectAnimator alphaBack2 = ObjectAnimator.ofFloat(back2, "alpha", 0, 1);
        final AnimatorSet bottomAnim = new AnimatorSet();
        bottomAnim.setDuration(1500);
        //同时执行控件平移和alpha渐变动画
        bottomAnim.play(tranName).with(tranFingerprint).with(tranFace).with(tranCorrelation).with(tranBack1_X)
                .with(tranBack1_Y).with(tranBack2_X).with(tranBack2_Y).with(icon1_X)
                .with(icon1_Y).with(icon2_X).with(icon2_Y).with(arrow_X).with(arrow_Y)
                .with(alphaLoName).with(alphaFingerprint).with(alphaFace)
                .with(alphaCorrelation).with(alphaBack1).with(alphaBack2);
        bottomAnim.start();


    }

    private void nextPage() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(UserInfoActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }


}
