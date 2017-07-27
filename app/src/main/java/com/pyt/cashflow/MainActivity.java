package com.pyt.cashflow;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pyt.cashflow.fragment.main.MainFragment;
import com.pyt.mylibrary.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }



}
