package rx.hxt.rxdemo.module.token_advanced4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.hxt.rxdemo.BaseFragment;
import rx.hxt.rxdemo.R;
import rx.hxt.rxdemo.model.FakeThing;
import rx.hxt.rxdemo.model.FakeToken;
import rx.hxt.rxdemo.net.NetWork;
import rx.hxt.rxdemo.net.api.FakeApi;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/23.
 */

public class TokenAdcancedFragment extends BaseFragment {

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.tokenTv)
    TextView tokenTv;

    final FakeToken cacheFakeToken = new FakeToken(true);
    boolean tokenUpdated;

    @OnClick(R.id.invalidateTokenBt)
    void inValidateToken() {
        cacheFakeToken.expired = true;
        Toast.makeText(getActivity(), R.string.token_destroyed, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.requestBt)
    void requestToken() {
        tokenUpdated = false;
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        final FakeApi fakeApi = NetWork.getFakeApi();
        Observable.just(null)
                .flatMap(new Func1<Object, Observable<FakeThing>>() {
                    @Override
                    public Observable<FakeThing> call(Object o) {

                        return cacheFakeToken == null ?
                                Observable.<FakeThing>error(new NullPointerException("Token is null!"))
                                :fakeApi.getFakeData(cacheFakeToken);
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if(throwable instanceof  IllegalArgumentException || throwable instanceof NullPointerException){
                                    return fakeApi.getFakeToken("fake_auth_code").doOnNext(new Action1<FakeToken>() {
                                        @Override
                                        public void call(FakeToken fakeToken) {
                                            tokenUpdated = true;
                                            cacheFakeToken.token =fakeToken.token;
                                            cacheFakeToken.expired = fakeToken.expired;
                                        }
                                    });
                                }
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FakeThing>() {
                    @Override
                    public void call(FakeThing fakeData) {
                        swipeRefreshLayout.setRefreshing(false);
                        String token = cacheFakeToken.token;
                        if (tokenUpdated) {
                            token += "(" + getString(R.string.updated) + ")";
                        }
                        tokenTv.setText(getString(R.string.got_token_and_data, token, fakeData.id, fakeData.name));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token_advanced, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.fragment_token_advanced;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_token_advanced;
    }
}
