package com.example.administrator.wangye2017_9_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wangye2017_9_22.R;
import com.example.administrator.wangye2017_9_22.Xiangqing;
import com.example.administrator.wangye2017_9_22.adapter.MyAdapterGride;
import com.example.administrator.wangye2017_9_22.bean.Bean;
import com.example.administrator.wangye2017_9_22.utils.OkHttpManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/22.
 * 王野  fragemnt
 */

public class Fragment2 extends Fragment {

    @Bind(R.id.fragment2_recy)
    RecyclerView fragment2Recy;
    private MyAdapterGride mAdapter;
    private String url = "http://news-at.zhihu.com/api/4/themes";
    private List<String> lists;
    private List<Bean.OthersBean> otherlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ButterKnife.bind(this, view);
        otherlist = new ArrayList<>();
        lists = new ArrayList<>();

        //初始化数据并加载适配器
        initData();

        return view;
    }

    private void initData() {
        OkHttpManager.getInstance().asyncJsonStringByURL(url, new OkHttpManager.Func1() {
            @Override
            public void onResponse(String result) {
                //bean类解析
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                List<Bean.OthersBean> others = bean.others;
                otherlist.addAll(others);
                //获取图片集合
                for (int i = 0; i < otherlist.size(); i++) {
                    Bean.OthersBean othersBean = otherlist.get(i);
                    lists.add(othersBean.thumbnail);
                }
                //适配器对象
                mAdapter = new MyAdapterGride(getActivity(), lists);
                //接口回调点击事件
                mAdapter.setOnItemClickListener(new MyAdapterGride.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startActivity(new Intent(getActivity(), Xiangqing.class));
                    }
                });

                fragment2Recy.setLayoutManager(new GridLayoutManager(getActivity(),2));
                //加载适配器
                fragment2Recy.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
