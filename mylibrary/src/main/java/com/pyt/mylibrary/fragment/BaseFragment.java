package com.pyt.mylibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 所有fragment父类
 * Created by pengyutao on 2017/7/27.
 */
public abstract class BaseFragment extends SupportFragment {

    protected String TAG = this.getClass().getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);



}
