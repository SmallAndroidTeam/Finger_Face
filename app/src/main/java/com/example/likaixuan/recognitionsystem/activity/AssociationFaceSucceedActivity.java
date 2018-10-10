package com.example.likaixuan.recognitionsystem.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.likaixuan.recognitionsystem.R;


public class AssociationFaceSucceedActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private ImageView iv;
    private AnimationDrawable anim;
    private int duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_face_succeed);
        initViews();
    }

    private void initViews() {
        imageView = findViewById(R.id.add_succeed_icon);
        textView = findViewById(R.id.add_succeed);
        iv = (ImageView) findViewById(R.id.anim);
        iv.setImageResource(R.drawable.finger_true);
        anim = (AnimationDrawable) iv.getDrawable();
        imageView.setAnimation(AnimationUtils.loadAnimation(AssociationFaceSucceedActivity.this, R.anim.scale_bigger));
        textView.setAnimation(AnimationUtils.loadAnimation(AssociationFaceSucceedActivity.this, R.anim.alpha));
        anim.start();
        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            duration += anim.getDuration(i);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                anim.stop();
                imageView.clearAnimation();
                textView.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(AssociationFaceSucceedActivity.this, R.anim.scale_smaller));
                Animation animation1 = (AnimationUtils.loadAnimation(AssociationFaceSucceedActivity.this, R.anim.translate));
                Animation animation2 = (AnimationUtils.loadAnimation(AssociationFaceSucceedActivity.this, R.anim.scale_smaller));
                imageView.startAnimation(animation);
                iv.startAnimation(animation2);
                textView.startAnimation(animation1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                        iv.setVisibility(View.GONE);
                        Intent intent = new Intent(AssociationFaceSucceedActivity.this, UserInfoActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, duration);


    }
}
