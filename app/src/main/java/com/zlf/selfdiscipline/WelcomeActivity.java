package com.zlf.selfdiscipline;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends AppCompatActivity {
    private Intent intent;
    //初始化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //适配状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //动画效果
        Transition transition = new Fade();
        intent = new Intent(WelcomeActivity.this, MainActivity.class);
        transition.setDuration(800);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        getWindow().setReturnTransition(transition);
        getWindow().setReenterTransition(transition);
        setContentView(R.layout.activity_welcome);
        welcome();
    }

    private void welcome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this).toBundle());
                    finish();
            }
        }, 2000);//等待时间
    }
}
