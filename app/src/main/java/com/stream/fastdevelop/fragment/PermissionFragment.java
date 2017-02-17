package com.stream.fastdevelop.fragment;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.activity.PermissionTipActivity;
import com.stream.fastdevelop.utils.PermissionChecker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/2/13 12:07
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class PermissionFragment extends CommonFragment {

    PermissionChecker checker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new AsyncQueryHandler(getActivity().getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                super.onQueryComplete(token, cookie, cursor);
            }
        };
        checker = new PermissionChecker(getActivity());
//        if (checker.lackStorage()) {
//            PermissionTipActivity.startActivityForResult(getActivity(), 0, "android.permission.WRITE_EXTERNAL_STORAGE");
//        }
//
//        if (checker.lackCallPhone()) {
//            PermissionTipActivity.startActivityForResult(getActivity(), 0, "android.permission.CALL_PHONE");
//        } else {
//            callPhone();
//        }

        if (checker.lackSendSMS()) {
            PermissionTipActivity.startActivityForResult(getActivity(), 0, "android.permission.SEND_SMS");
        } else {
            doSendSMSTo("hh", "content");
//            sendSMS();
        }
//
//        if (checker.lackCamera()) {
//            PermissionTipActivity.startActivityForResult(getActivity(), 0, "android.permission.CAMERA");
//
//        }
//
//        if (checker.lackContacts()) {
//            PermissionTipActivity.startActivityForResult(getActivity(), 0, "android.permission.READ_CONTACTS");
//        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("zcc", "requestCode:"+requestCode + ", resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (!checker.lackCallPhone()) {
            callPhone();
        }
    }

    /**
     * 写操作
     */
    private void writeStorage() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File externalFilesDir = null;
            try {
                externalFilesDir = getActivity().getExternalFilesDir("test");
                File file = new File(externalFilesDir, "test.txt");
                boolean hasFile;
                hasFile = file.exists() || file.createNewFile();
                if (hasFile) {
                    FileOutputStream fis = new FileOutputStream(file);
                    String h = "hello world";
                    byte[] bs = h.getBytes();
                    fis.write(bs);
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打电话
     */
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
//        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
        startActivity(intent);
    }

    /**
     * 调用相机
     */
    private void callCamera() {
        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(imageCaptureIntent);
    }

    /**
     * 发送短息
     */
    private void sendSMS() {
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage("10086");
        for (String text : divideContents) {
            smsManager.sendTextMessage("+86 10086", null, text, null, null);
        }
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public void doSendSMSTo(String phoneNumber,String message){
//        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            startActivity(intent);
//        }
    }

    /**
     * 读取通讯录
     */
    public void readContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; // 联系人Uri；
        // 查询的字段
        String[] projection = { ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY };
        // 按照sort_key升序查詢
        handler.startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
    }

    AsyncQueryHandler handler;





    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getActivity().getPackageName());
        }
        return localIntent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permission, container, false);
        view.findViewById(R.id.enter_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAppDetailSettingIntent());
                startActivity(intent);
            }
        });
        return view;
    }


}
