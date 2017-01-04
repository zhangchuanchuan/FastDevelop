package com.stream.fastdevelop.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.adapter.ButtonAdapter;
import com.stream.fastdevelop.fragment.CommonAdapterDemoFragment;
import com.stream.fastdevelop.fragment.CommonFragment;
import com.stream.fastdevelop.fragment.MemoryOptimized;
import com.stream.fastdevelop.fragment.RetrofitFragment;
import com.stream.fastdevelop.fragment.ViewDemoFragment;
import com.stream.fastdevelop.utils.LogUtils;
import com.stream.fastdevelop.vo.ButtonVo;

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
public class MainActivity extends BaseActivity{

    private long lastBackTime = 0;

    private List<ButtonVo> list;

    private RecyclerView mButtonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonListView = (RecyclerView) findViewById(R.id.button_list_view);
        initArgs();
    }

    private void initArgs() {
        list = new ArrayList<>();
        list.add(new ButtonVo("通用", CommonFragment.class.getName()));
        list.add(new ButtonVo("view demo", ViewDemoFragment.class.getName()));
        list.add(new ButtonVo("adapter", CommonAdapterDemoFragment.class.getName()));
        list.add(new ButtonVo("内存优化", MemoryOptimized.class.getName()));
        list.add(new ButtonVo("ob demo", RetrofitFragment.class.getName()));
        ButtonAdapter adapter = new ButtonAdapter(this, list, new ButtonAdapter.ItemCLickListener() {
            @Override
            public void onItemClicked(ButtonVo vo) {
                CommonActivity.jumpToMe(MainActivity.this, vo.getJumpFragmentName());
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mButtonListView.setLayoutManager(manager);
        mButtonListView.setAdapter(adapter);

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

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_BACKGROUND:
                /**
                 *  系统正运行于低内存状态并且你的进程正处于LRU缓存名单最不易被杀死的位置。尽管你的app
                 *  进程并不是处于被杀掉的高危状态，系统可能已经开始杀掉LRU缓存的其他进程了。你应该释放
                 *  掉那些容易恢复的资源，以便于你的进程可以保留下来。
                 */
                LogUtils.d(TAG, "background");
                break;
            case TRIM_MEMORY_COMPLETE:
                /**
                 *  系统正运行于低内存状态并且你的进程正处于LRU名单中最易被杀掉的位置。你应该释放任何
                 *  不影响你的app恢复状态的资源。
                 */
                LogUtils.d(TAG, "complete");
                break;
            case TRIM_MEMORY_MODERATE:
                /**
                 *  系统正运行于低内存状态并且你的进程已经接近LRU名单的中部位置。如果系统开始变得更加内
                 *  存紧张，你的进程是有可能被杀死的。
                 */
                LogUtils.d(TAG, "onTrimMemory level = moderate");
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                /**
                 *  运行临界（危险）状态。你的app仍在运行，但是系统已经把LRU Cache中的大多数进程都已经
                 *  被杀死，因此，应该立即释放所有非必须的资源。如果系统不能回收到足够的ram数量，系统
                 *  将会清楚所有的LRU缓存中的进程，并且开始杀死那些之前被认为不应该杀死的进程。例如：
                 *  包含了一个运行态的Service。
                 */
                LogUtils.d(TAG, "onTrimMemory level = running_critical");
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                /**
                 *  运行低的。你的app正在运行并且没有被列为可杀死的。但是，设备正运行于更低内存的状态下，
                 *  你应该释放不用的资源来提升系统性能（但是这也会直接影响到你的app性能）。
                 */
                LogUtils.d(TAG, "onTrimMemory level = running_low");
                break;
            case TRIM_MEMORY_RUNNING_MODERATE:
                /**
                 *  运行稳健的。你的app正在运行并且不会被列为可杀死的。但是，设备此时正运行于低内存状态下
                 *  ，系统开始出发杀死LRU Cache中的Process机制。
                 */
                LogUtils.d(TAG, "onTrimMemory level = running_moderate");
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                // UI隐藏时候
                LogUtils.d(TAG, "onTrimMemory level = ui_hidden");
                break;
        }
    }
}
