package com.example.administrator.wangye2017_9_22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.administrator.wangye2017_9_22.adapter.MyAdapter;
import com.example.administrator.wangye2017_9_22.bean.Bean;
import com.example.administrator.wangye2017_9_22.utils.OkHttpManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 详情页
 */
public class Xiangqing extends AppCompatActivity {
    @Bind(R.id.Xiangqing_recy)
    RecyclerView XiangqingRecy;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    private MyAdapter mAdapter;
    private String url = "http://news-at.zhihu.com/api/4/themes";
    private List<String> lists;
    private List<Bean.OthersBean> otherlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        otherlist = new ArrayList<>();
        lists = new ArrayList<>();
        //初始化数据
        initData();
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
                mAdapter = new MyAdapter(Xiangqing.this, otherlist);
                //布局管理器
                XiangqingRecy.setLayoutManager(new LinearLayoutManager(Xiangqing.this));
                //加载适配器
                XiangqingRecy.setAdapter(mAdapter);
            }
        });
    }
}
