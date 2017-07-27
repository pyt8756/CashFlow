package com.pyt.mylibrary.activity;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.pyt.mylibrary.activity.BaseActivity.FragmentAnimatorType.HORIZONTAL;

/**
 * activity父类
 * Created by pengyutao on 2017/7/27.
 */
public class BaseActivity extends SupportActivity {

    enum FragmentAnimatorType {

        /**
         * 横向
         */
        HORIZONTAL,

        /**
         * 纵向
         */
        VERTICAL,

        /**
         * 无动画
         */
        NONE,
    }

    /**
     * 设置fragment跳转动画，默认有横向，纵向，无动画如果不满足需求，可以覆写此方法
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator animator;
        switch (getFragmentAnimatorType()) {

            case VERTICAL:
                animator = new DefaultVerticalAnimator();
                break;

            case NONE:
                animator = new DefaultNoAnimator();
                break;

            case HORIZONTAL:
            default:
                animator = new DefaultHorizontalAnimator();
                break;
        }

        return animator;
    }

    protected FragmentAnimatorType getFragmentAnimatorType() {
        return HORIZONTAL;
    }

}
