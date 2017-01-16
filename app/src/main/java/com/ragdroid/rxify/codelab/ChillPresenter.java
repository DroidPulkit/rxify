package com.ragdroid.rxify.codelab;

import com.ragdroid.rxify.codelab.presenter.BaseCLPresenter;
import com.ragdroid.rxify.core.BaseSchedulerProvider;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * Created by garimajain on 15/01/17.
 */

public class ChillPresenter extends BaseCLPresenter<Integer> implements CodeLabContract.Presenter {

    //Input
    Observable<Integer> inputValues = Observable.fromIterable(Arrays.asList(1, 1, 2, 3, 2));

    //TODO Print all distinct numbers

    @Inject
    public ChillPresenter(BaseSchedulerProvider provider) {
        super(provider);
    }

    @Override
    protected Disposable getDisposable() {
        return inputValues
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .compose(lazyTransformer)
                .subscribe(next, error, complete);
    }
}
