package com.stream.fastdevelop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.fragment.CommonAdapterDemoFragment;
import com.stream.fastdevelop.fragment.CommonFragment;
import com.stream.fastdevelop.fragment.MemoryOptimized;
import com.stream.fastdevelop.fragment.ViewDemoFragment;

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

    private SparseArray<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArgs();
    }

    private void initArgs() {
        list = new SparseArray<>();
        list.put(R.id.common, CommonFragment.class.getName());
        list.put(R.id.view_demo, ViewDemoFragment.class.getName());
        list.put(R.id.adapter_demo, CommonAdapterDemoFragment.class.getName());
        list.put(R.id.memory_optimized, MemoryOptimized.class.getName());
        findViewAndSetOnclick();
    }

    private void findViewAndSetOnclick() {

        for(int i = 0; i < list.size(); i++){
            findViewById(list.keyAt(i)).setOnClickListener(this);
        }
    }




    @Override
    public void onClick(View v) {
        for(int i = 0; i < list.size(); i++){
            if(list.keyAt(i) == v.getId()){
                CommonActivity.jumpToMe(this, list.get(list.keyAt(i)));
            }
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
