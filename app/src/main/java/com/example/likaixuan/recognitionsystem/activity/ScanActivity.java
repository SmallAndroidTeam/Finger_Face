package com.example.likaixuan.recognitionsystem.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.likaixuan.recognitionsystem.R;
import com.example.likaixuan.recognitionsystem.particles.particle_ring.ExplosionField;
import com.example.likaixuan.recognitionsystem.utils.CustomViews;

import java.util.Timer;
import java.util.TimerTask;

public class ScanActivity extends AppCompatActivity {
    private CustomViews mCustomView;
    private ImageView finger;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        init();
    }

    public void init() {
        mCustomView = (CustomViews) findViewById(R.id.cv);
        finger = (ImageView) findViewById(R.id.finger);
        final View ring = findViewById(R.id.ring);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        new ExplosionField(ScanActivity.this).explode(ring, null);
                    }
                });

            }
        }, 100);
    }
}
