package com.example.administrator.wangye2017_9_22;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.administrator.wangye2017_9_22.adapter.MyBaseAdapter;
import com.example.administrator.wangye2017_9_22.fragment.Fragment1;
import com.example.administrator.wangye2017_9_22.fragment.Fragment2;
import com.example.administrator.wangye2017_9_22.fragment.Fragment3;
import com.example.administrator.wangye2017_9_22.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.wangye2017_9_22.R.id.tab_layout;
import static com.example.administrator.wangye2017_9_22.R.id.view_pager;

/**
 * 王野 主界面
 */
public class MainActivity extends AppCompatActivity {

    @Bind(tab_layout)
    TabLayout tablayout;
    @Bind(view_pager)
    ViewPager viewpager;
    private List<Fragment> list;
    MyBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
    }
    public  void initViewPager() {
        list = new ArrayList<>();
        //传入fragment
        list.add(new Fragment1());
        list.add(new Fragment4());
        list.add(new Fragment3());
        list.add(new Fragment2());
        adapter = new MyBaseAdapter(getSupportFragmentManager());
        adapter.setFragment(list);
        viewpager.setAdapter(adapter);
        //创建指示器
        for (int i = 0; i < list.size() ; i++) {
            tablayout.addTab(tablayout.newTab());
        }
        //关联
        tablayout.setupWithViewPager(viewpager);
        //添加tittle
        tablayout.getTabAt(0).setText("最新日报");
        tablayout.getTabAt(1).setText("专栏");
        tablayout.getTabAt(2).setText("热门");
        tablayout.getTabAt(3).setText("主题日报");
        //设置间隔线
        LinearLayout linearLayout = (LinearLayout) tablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
    }
}
