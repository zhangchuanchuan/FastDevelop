package com.stream.fastdevelop.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.activity.CommonActivity;
import com.stream.fastdevelop.vo.ButtonVo;

import java.util.List;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/3 15:15
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    List<ButtonVo> list;

    Context context;

    ItemCLickListener cLickListener;

    public ButtonAdapter(Context context, List<ButtonVo> list, ItemCLickListener listener){
        this.context = context;
        this.list = list;
        this.cLickListener = listener;
    }

    @Override
    public ButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ButtonViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_button, parent, false));
    }

    @Override
    public void onBindViewHolder(ButtonViewHolder holder, int position) {
        if (list != null && list.size() > position) {
            final ButtonVo temp = list.get(position);
            holder.buttonText.setText(temp.getButtonText());
            holder.buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cLickListener.onItemClicked(temp);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder{

        TextView buttonText;

        public ButtonViewHolder(View itemView) {
            super(itemView);
            buttonText = (TextView) itemView.findViewById(R.id.button);
        }
    }

    public interface ItemCLickListener{
        void onItemClicked (ButtonVo vo);
    }
}
