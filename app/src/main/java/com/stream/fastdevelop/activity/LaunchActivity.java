package com.stream.fastdevelop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.stream.fastdevelop.R;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/8/15 11:58
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessageDelayed(msg, 2000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jumpToMainActivity();
                    break;
            }
        }
    };

    private void jumpToMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

}
