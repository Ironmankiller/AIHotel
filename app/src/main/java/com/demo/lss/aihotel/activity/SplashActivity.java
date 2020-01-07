package com.demo.lss.aihotel.activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.lss.aihotel.R;

import zuo.biao.library.base.BaseActivity;

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(MainTabActivity.createIntent(SplashActivity.this));
                finish();
            }
        }, 2000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

}