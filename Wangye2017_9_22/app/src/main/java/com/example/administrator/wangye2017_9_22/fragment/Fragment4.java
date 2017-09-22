package com.example.administrator.wangye2017_9_22.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wangye2017_9_22.R;

/**
 * Created by Administrator on 2017/9/22.
 * 王野  fragemnt
 */

public class Fragment4 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1,container,false);
        return view;
    }
}
