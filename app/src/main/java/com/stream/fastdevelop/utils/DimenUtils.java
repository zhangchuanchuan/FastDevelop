package com.stream.fastdevelop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * description：尺寸工具类
 * ===============================
 * creator：zcc
 * create time：2016/9/7 10:37
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class DimenUtils {


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取status bar 高度
     */
    public static int getStatusBarHeight(Context ctx) {
        int statusBarHeight = 0;
        try {
            /**
             * 通过反射机制获取StatusBar高度
             */
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int height = Integer.parseInt(field.get(object).toString());
            /**
             * 设置StatusBar高度
             */
            statusBarHeight = ctx.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusBarHeight;
    }

    /**
     * 获取navigation bar 高度
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId);
    }


    /**
     * 获取屏幕宽度（像素）
     *
     * @param ctx context
     *
     * @return 屏幕宽度像素值
     */
    public static int getDisplayWidth(Context ctx) {
        DisplayMetrics metric = new DisplayMetrics();
        if (ctx != null) {
            WindowManager winManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
            winManager.getDefaultDisplay().getMetrics(metric);
        }
        return metric.widthPixels;
    }

    /**
     * 获取屏幕高度（像素）
     *
     * @param ctx context
     *
     * @return 屏幕高度像素值
     */
    public static int getDisplayHeight(Context ctx) {
        DisplayMetrics metric = new DisplayMetrics();
        if (ctx != null) {
            WindowManager winManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
            winManager.getDefaultDisplay().getMetrics(metric);
        }
        return metric.heightPixels;
    }
}
