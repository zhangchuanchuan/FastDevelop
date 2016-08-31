package com.stream.fastdevelop.common.adapter;

import android.content.Context;
import android.widget.TextView;

import com.stream.fastdevelop.R;

import java.util.List;

/**
 * description：
 * ===============================
 * creator：zcc
 * create time：2016/8/31 17:03
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class DemoAdapter extends CommonAdapter<DemoVo> {

    public DemoAdapter(Context context, int resourceId, List<DemoVo> datas){
        super(context, resourceId, datas);
    }

    @Override
    public void initData(DemoVo demoVo, CommonViewHolder viewHolder) {
        if(demoVo == null || viewHolder == null){
            return;
        }
        ((TextView)viewHolder.getViewById(R.id.text)).setText(demoVo.getText());

    }
}
