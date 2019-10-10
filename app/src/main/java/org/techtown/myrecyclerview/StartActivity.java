package org.techtown.myrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    Handler mHandler = new Handler();
    ImageSwitcher mSwitcher;
    boolean mRunning;
    Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView im = (ImageView) findViewById(R.id.imageView13);
        Animation anim = AnimationUtils
                .loadAnimation
                        (getApplicationContext(),
                                R.anim.set_anim);
        im.startAnimation(anim);




        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                startActivity(intent);
            }
        });

    }}