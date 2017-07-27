package com.pyt.mylibrary.fragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

/**
 * 支持rxjava
 * Created by pengyutao on 2017/7/27.
 */
public class MyObservable<T> {

    private ConnectableObservable<T> publish;
    private ObservableEmitter<T> emitter;

    public MyObservable() {
        initObservable();
    }

    public ConnectableObservable<T> getObservable() {
        if (publish == null) {
            initObservable();
        }
        return publish;
    }

    public ObservableEmitter<T> getEmitter() {
        if (emitter == null) {
            initObservable();
        }
        return emitter;
    }

    private void initObservable() {
        publish = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<T> e) throws Exception {
                emitter = e;
            }
        }).observeOn(AndroidSchedulers.mainThread()).publish();
        publish.connect();
    }

    public Disposable subscribe() {
        return getObservable().subscribe();
    }

    public Disposable subscribe(Consumer<? super T> onNext) {
        return getObservable().subscribe(onNext);
    }

    public Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        return getObservable().subscribe(onNext, onError);
    }

    public Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        return getObservable().subscribe(onNext, onError, onComplete);
    }

    public Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                                Action onComplete, Consumer<? super Disposable> onSubscribe) {
        return getObservable().subscribe(onNext, onError, onComplete, onSubscribe);
    }

    public void subscribe(Observer<? super T> observer) {
        getObservable().subscribe(observer);
    }

    public void onNext(T value) {
        getEmitter().onNext(value);
    }

    public void onError(Throwable error) {
        getEmitter().onError(error);
    }

    public void onComplete() {
        getEmitter().onComplete();
    }

}
