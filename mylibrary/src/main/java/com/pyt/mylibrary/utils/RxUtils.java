package com.pyt.mylibrary.utils;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * rx utils
 * Created by pengyutao on 2017/6/10.
 */
public class RxUtils {

    public static void click(final View view, int second, final View.OnClickListener listener) {
        if (view == null || listener == null) {
            throw new IllegalArgumentException("view or runnable can not be null");
        }
        RxView.clicks(view)
                .throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        listener.onClick(view);
                    }
                });
    }

    public static void click(View view, View.OnClickListener listener) {
        click(view, 1, listener);
    }


}
