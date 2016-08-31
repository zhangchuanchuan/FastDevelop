package com.stream.fastdevelop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.common.adapter.DemoAdapter;
import com.stream.fastdevelop.common.adapter.DemoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/8/31 17:14
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class CommonAdapterDemoFragment extends CommonFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_adapter_demo, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        List<DemoVo> datas = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            DemoVo vo = new DemoVo();
            vo.setText("第"+i+"测试数据");
            datas.add(vo);
        }
        DemoAdapter adapter = new DemoAdapter(getActivity(), R.layout.adapter_demo_item, datas);
        listView.setAdapter(adapter);
    }
}
