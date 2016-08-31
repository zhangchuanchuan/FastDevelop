package com.stream.fastdevelop.common.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * description：通用的ViewHolder
 * ===============================
 * creator：张川川
 * create time：2016/8/31 14:56
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class CommonViewHolder {

    private Context mContext;

    private int mResourceId;

    private int position;

    private View mConvertView;

    // 存储int, object类型比HashMap更高效
    private SparseArray<View> mHolderViews;

    public CommonViewHolder(Context context, int resourceId, ViewGroup parent, int position){
        mContext = context;
        mResourceId = resourceId;
        this.position = position;
        mConvertView = LayoutInflater.from(context).inflate(resourceId, parent, false);
        mConvertView.setTag(this);
    }

    public static CommonViewHolder getCommonViewHolder(Context context, View convertView, ViewGroup parent, int resourceId, int position){
        if(convertView == null){
            return new CommonViewHolder(context, resourceId, parent, position);
        }
        CommonViewHolder commonViewHolder = (CommonViewHolder) convertView.getTag();
        commonViewHolder.mConvertView = convertView;
        commonViewHolder.position = position;
        return commonViewHolder;
    }

    public View getViewById(int viewId){
        if(mHolderViews == null){
            mHolderViews = new SparseArray<>();
        }
        if(mHolderViews.get(viewId) == null){
            View view = mConvertView.findViewById(viewId);
            mHolderViews.put(viewId, view);
            return view;
        }else{
            return mHolderViews.get(viewId);
        }
    }

    public View getConvertView(){
        return mConvertView;
    }

}
