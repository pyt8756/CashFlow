package com.pyt.cashflow.fragment.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pyt.cashflow.R;
import com.pyt.mylibrary.fragment.BaseMainFragment;


public class SecondFragment extends BaseMainFragment {


    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
