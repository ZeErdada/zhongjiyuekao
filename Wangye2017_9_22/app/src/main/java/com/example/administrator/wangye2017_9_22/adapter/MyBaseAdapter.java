package com.example.administrator.wangye2017_9_22.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
/**
 * 王野
 * 适配器
 */
public class MyBaseAdapter extends FragmentPagerAdapter {
    public MyBaseAdapter(FragmentManager fm) {
        super(fm);
    }
    private List<Fragment>mlist;
    public void setFragment(List<Fragment> list){
        mlist=list;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mlist.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}