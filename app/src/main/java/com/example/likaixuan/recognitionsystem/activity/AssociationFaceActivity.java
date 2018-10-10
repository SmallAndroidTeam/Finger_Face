package com.example.likaixuan.recognitionsystem.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.likaixuan.recognitionsystem.R;

public class AssociationFaceActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView iv;
    private int duration = 0;
    private AnimationDrawable anim_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.association_face);
        initViews();

    }

    private void initViews() {
        textView1 = findViewById(R.id.whether_associate_face);
        textView2 = findViewById(R.id.sure_association);
        //   mSquareProgress = (SquareProgress) findViewById(R.id.sp);
        textView3 = findViewById(R.id.quxiao);
        iv = (ImageView) findViewById(R.id.anim);
        iv.setImageResource(R.drawable.associate);
        anim_start = (AnimationDrawable) iv.getDrawable();
        textView1.setAnimation(AnimationUtils.loadAnimation(AssociationFaceActivity.this, R.anim.scale_bigger));
        textView2.setAnimation(AnimationUtils.loadAnimation(AssociationFaceActivity.this, R.anim.alpha));
        textView3.setAnimation(AnimationUtils.loadAnimation(AssociationFaceActivity.this, R.anim.alpha));
        anim_start.start();
        for (int i = 0; i < anim_start.getNumberOfFrames(); i++) {
            duration += anim_start.getDuration(i);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                anim_start.stop();
                Intent intent = new Intent(AssociationFaceActivity.this, AssociationFaceSucceedActivity.class);
                startActivity(intent);   //此处调用第二个动画播放方法
            }
        }, duration + 100);

    }
}
