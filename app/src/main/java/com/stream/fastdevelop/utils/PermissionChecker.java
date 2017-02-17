package com.stream.fastdevelop.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/2/16 13:26
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class PermissionChecker {
    /**
     * 检查权限的工具类
     * <p/>
     * Created by wangchenlong on 16/1/26.
     */
        private final Context mContext;

        public PermissionChecker(Context context) {
            mContext = context.getApplicationContext();
        }

        // 判断权限集合
        public boolean lacksPermissions(String... permissions) {
            for (String permission : permissions) {
                if (lacksPermission(permission)) {
                    return true;
                }
            }
            return false;
        }

        // 判断是否缺少权限
        private boolean lacksPermission(String permission) {
            return ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED;
        }

        public boolean lackStorage() {
            return lacksPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        public boolean lackContacts() {
            return lacksPermission("android.permission.READ_CONTACTS");
        }

        public boolean lackCamera() {
            return lacksPermission("android.permission.CAMERA");
        }

        public boolean lackCallPhone() {
            return lacksPermission("android.permission.CALL_PHONE");
        }

        public boolean lackSendSMS() {
            return lacksPermission("android.permission.SEND_SMS");
        }
}
