package com.stream.fastdevelop.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.stream.fastdevelop.fragment.CommonFragment;

/**
 * Created by 张川川 on 2016/8/21.
 * 通用的Activity
 */
public class CommonActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String fragmentName = getIntent().getStringExtra("fragment");
            Class<?> aClass = Class.forName(fragmentName);
            CommonFragment commonFragment = ((CommonFragment) aClass.newInstance());
            getFragmentManager().beginTransaction().add(android.R.id.content, commonFragment).commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param context 启动用的context
     * @param fragment 内嵌的fragment名字
     */
    public static void jumpToMe(Context context, String fragment){
        Intent intent = new Intent();
        intent.setClass(context, CommonActivity.class);
        intent.putExtra("fragment", fragment);
        context.startActivity(intent);
    }
}
