package com.pyt.cashflow.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pyt.cashflow.R;
import com.pyt.cashflow.fragment.home.HomeFragment;
import com.pyt.cashflow.fragment.my.MyFragment;
import com.pyt.cashflow.fragment.second.SecondFragment;
import com.pyt.mylibrary.fragment.BaseFragment;
import com.pyt.mylibrary.fragment.MyObservable;
import com.pyt.mylibrary.view.bottomBar.BottomBar;
import com.pyt.mylibrary.view.bottomBar.BottomBarTab;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportFragment;

import static com.pyt.cashflow.fragment.main.MainFragment.MainFragmentEvent.event_count;
import static com.pyt.cashflow.fragment.main.MainFragment.MainFragmentEvent.event_select;
import static com.pyt.cashflow.fragment.main.MainFragment.MainFragmentEvent.event_start;

public class MainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomBar mBottomBar;

    private static MyObservable<MainFragmentEvent> mObservable;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = MyFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(SecondFragment.class);
            mFragments[THIRD] = findChildFragment(MyFragment.class);
        }
    }

    @Override
    protected void initView(View view) {
        mObservable = new MyObservable<>();
        mObservable.subscribe(new Consumer<MainFragmentEvent>() {
            @Override
            public void accept(@NonNull MainFragmentEvent event) throws Exception {
                switch (event.event) {
                    case event_start:
                        start(event.targetFragment, event.launchMode);
                        break;
                    case event_select:
                        mBottomBar.setCurrentItem(event.select);
                        break;
                    case event_count:
                        mBottomBar.getItem(event.select).setUnreadCount(event.num);
                        break;
                }
            }
        });

        mBottomBar = view.findViewById(R.id.bottomBar);
        mBottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_white_24dp, getString(R.string.home)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_discover_white_24dp, getString(R.string.discover)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_account_circle_white_24dp, getString(R.string.my)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {
                    tab.setUnreadCount(0);
                } else {
                    tab.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                EventBus.getDefault().post(new TabSelectedEvent(position));
            }
        });
    }


    public static MyObservable<MainFragmentEvent> getmObservable() {
        return mObservable;
    }

    public static class MainFragmentEvent {

        static final int event_start = 0;
        static final int event_select = 1;
        static final int event_count = 2;

        int event;
        BaseFragment targetFragment;
        int launchMode;
        int select;
        int num;

        public MainFragmentEvent(BaseFragment targetFragment, int launchMode) {
            event = event_start;
            this.targetFragment = targetFragment;
            this.launchMode = launchMode;
        }

        public MainFragmentEvent(int select) {
            this.select = select;
        }

        public MainFragmentEvent(int select, int num) {
            this.select = select;
            this.num = num;
        }
    }

}
