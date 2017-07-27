package com.pyt.cashflow.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pyt.cashflow.R;
import com.pyt.mylibrary.fragment.BaseMainFragment;


public class MyFragment extends BaseMainFragment {


    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
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
