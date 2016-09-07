package com.stream.fastdevelop.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/9/7 17:46
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class BaseActivity extends Activity {
    String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }
}
