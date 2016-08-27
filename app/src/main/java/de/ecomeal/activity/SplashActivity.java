package de.ecomeal.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import de.ecomeal.R;

/**
 * Created by LS on 20.06.2016.
 */
public class SplashActivity extends BaseToolbarActivity {

    private static final long SPLASH_DURATION = 3000;
    private View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        imageView = (ImageView) findViewById(R.id.ImageView01);
        imageView.setBackgroundResource(R.drawable.movie);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DURATION);
    }
}
