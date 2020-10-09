package com.qlabs.wordbook.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.databinding.ActSplashBinding;
import com.qlabs.wordbook.word.view.WordListActivity;

public class SplashScreen extends Activity {
    private ActSplashBinding splashBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.act_splash);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, WordListActivity.class));
            finish();
        },1500);
    }
}
