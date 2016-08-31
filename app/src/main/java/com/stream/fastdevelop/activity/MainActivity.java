package com.stream.fastdevelop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.fragment.CommonAdapterDemoFragment;
import com.stream.fastdevelop.fragment.CommonFragment;
import com.stream.fastdevelop.fragment.ViewDemoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/8/15 11:57
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private long lastBackTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewAndSetOnclick();
    }

    private void findViewAndSetOnclick() {
        List<Integer> list = new ArrayList<>();
        list.add(R.id.common);
        list.add(R.id.view_demo);
        list.add(R.id.adapter_demo);

        for(Integer i : list){
            findViewById(i).setOnClickListener(this);
        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common:
                CommonActivity.jumpToMe(this, CommonFragment.class.getName());
                break;
            case R.id.view_demo:
                CommonActivity.jumpToMe(this, ViewDemoFragment.class.getName());
                break;
            case R.id.adapter_demo:
                CommonActivity.jumpToMe(this, CommonAdapterDemoFragment.class.getName());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastBackTime < 2000){
            super.onBackPressed();
        }else{
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastBackTime = currentTime;
        }
    }
}
