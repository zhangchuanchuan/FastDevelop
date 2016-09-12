package com.stream.fastdevelop.fragment;

import com.stream.fastdevelop.utils.LogUtils;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/9/12 10:53
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class MemoryOptimized extends CommonFragment {
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
