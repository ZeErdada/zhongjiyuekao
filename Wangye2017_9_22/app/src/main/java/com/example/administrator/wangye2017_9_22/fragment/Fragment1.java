package com.example.administrator.wangye2017_9_22.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.administrator.wangye2017_9_22.R;
import com.example.administrator.wangye2017_9_22.adapter.MyAdapter;
import com.example.administrator.wangye2017_9_22.bean.Bean;
import com.example.administrator.wangye2017_9_22.utils.ImageLoadUtils;
import com.example.administrator.wangye2017_9_22.utils.OkHttpManager;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/22.
 * 王野 最新日报fragment
 */

public class Fragment1 extends Fragment {
    @Bind(R.id.fragment1_recy)
    XRecyclerView fragment1Recy;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.group)
    RadioGroup group;
    @Bind(R.id.header)
    RecyclerViewHeader header;

    private List<Bean.OthersBean> otherlist;
    //当前索引位置以及上一个索引位置
    private int index = 0,preIndex = 0;
    //是否需要轮播标志
    private boolean isContinue = true;
    //定时器，用于实现轮播
    private Timer timer;
    Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    index++;
                    viewpager.setCurrentItem(index);
            }
        }
    };
    private MyAdapter mAdapter;
    private String url = "http://news-at.zhihu.com/api/4/themes";
    private List<String> lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        ButterKnife.bind(this, view);
        otherlist = new ArrayList<>();
        lists = new ArrayList<>();
        //初始化数据
        initData();

        timer = new Timer();//创建Timer对象
        //执行定时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    mHandler.sendEmptyMessage(1);
            }
        },2000,2000);//延迟2秒，每隔2秒发一次消息


        mAdapter = new MyAdapter(getActivity(),otherlist);
        fragment1Recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragment1Recy.setAdapter(mAdapter);
        // 将顶部视图与RecyclerView关联即可
        header.attachTo(fragment1Recy);

        fragment1Recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "已加载", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    //获取数据
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
                //根据图片个数初始化按钮
                initRadioButton(lists.size());
                System.out.println("wouiad"+lists.size());
                //设置适配器
                viewpager.setAdapter(new pagerAdapter());
                //切换监听
                viewpager.addOnPageChangeListener(onPageChangeListener);
                //触摸监听
                viewpager.setOnTouchListener(onTouchListener);
            }
        });
    }

    /**
     * 根据图片个数初始化按钮
     * @param length
     */
    private void initRadioButton(int length) {
        for(int i = 0;i<length;i++){
            ImageView imageview = new ImageView(getActivity());
            imageview.setImageResource(R.drawable.rg_selector);//设置背景选择器
            imageview.setPadding(20,0,0,0);//设置每个按钮之间的间距
            //将按钮依次添加到RadioGroup中
            group.addView(imageview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //默认选中第一个按钮，因为默认显示第一张图片
            group.getChildAt(0).setEnabled(false);
        }
    }
    /**
     * 根据当前触摸事件判断是否要轮播
     */
    View.OnTouchListener onTouchListener  = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                //手指按下和划动的时候停止图片的轮播
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    isContinue = false;
                    break;
                default:
                    isContinue = true;
            }
            return false;
        }
    };
    /**
     *根据当前选中的页面设置按钮的选中
     */
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            index = position;//当前位置赋值给索引
            setCurrentDot(index%lists.size());
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    /**
     * 设置对应位置按钮的状态
     * @param i 当前位置
     */
    private void setCurrentDot(int i) {
        if(group.getChildAt(i)!=null){
            group.getChildAt(i).setEnabled(false);//当前按钮选中
        }
        if(group.getChildAt(preIndex)!=null){
            group.getChildAt(preIndex).setEnabled(true);//上一个取消选中
            preIndex = i;//当前位置变为上一个，继续下次轮播
        }
    }
    class  pagerAdapter extends android.support.v4.view.PagerAdapter{
        //存放图片的数组
        private List<ImageView> mList = new ArrayList<>();
        @Override
        public int getCount() {
            //返回一个比较大的值，目的是为了实现无限轮播
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position%lists.size();
            ImageView imageView = new ImageView(getActivity());
            //圆角加载效果
            ImageLoadUtils.INSTANCE.loadRoundedImageView(imageView,lists.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            mList.add(imageView);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除上一个
            container.removeView(mList.get(position));
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        //fragment不可见时停止轮播
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        //销毁时停止
        timer.cancel();
        super.onPause();
    }
}
