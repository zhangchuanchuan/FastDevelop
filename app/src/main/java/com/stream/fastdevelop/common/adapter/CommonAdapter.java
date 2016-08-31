package com.stream.fastdevelop.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * description：通用的adapter
 * ===============================
 * creator：zcc
 * create time：2016/8/31 14:57
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;

    protected int mResoureceId;

    protected List<T> mDatas;


    /**
     * CommonAdapter的构造器
     * @param context context
     * @param resourceId item的资源Id
     * @param datas 数据
     */
    public CommonAdapter(Context context, int resourceId, List<T> datas){
        mContext = context;
        mResoureceId = resourceId;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getCommonViewHolder(mContext, convertView, parent, mResoureceId, position);
        T t = mDatas.get(position);
        initData(t, viewHolder);
        return viewHolder.getConvertView();
    }

    public abstract void initData(T t, CommonViewHolder viewHolder);
}
