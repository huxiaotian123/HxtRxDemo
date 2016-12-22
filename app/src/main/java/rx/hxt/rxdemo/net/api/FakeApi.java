package rx.hxt.rxdemo.net.api;


import android.support.annotation.NonNull;

import java.util.Random;

import rx.Observable;
import rx.functions.Func1;
import rx.hxt.rxdemo.model.FakeThing;
import rx.hxt.rxdemo.model.FakeToken;

/**
 * Created by Administrator on 2016/12/22.
 */

public class FakeApi {
    Random random = new Random();

    public Observable<FakeToken> getFakeToken(@NonNull String fakeAuth) {
        return Observable.just(fakeAuth)
                .map(new Func1<String, FakeToken>() {
                    @Override
                    public FakeToken call(String s) {
                        int fakeNetWorkTimeOut = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetWorkTimeOut);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FakeToken fakeToken = new FakeToken();
                        fakeToken.token = createToken();
                        return fakeToken;
                    }
                });
    }

    private static String createToken() {
        return "fake_token_" + System.currentTimeMillis() % 10000;
    }


    public Observable<FakeThing> getFakeData(FakeToken fakeToken) {
        return Observable.just(fakeToken)
                .map(new Func1<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing call(FakeToken fakeToken) {
                        int fakeNetworkTimeCost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (fakeToken.expired) {
                            throw new IllegalArgumentException("Token expired!");
                        }
                        FakeThing fakeData = new FakeThing();
                        fakeData.id = (int) (System.currentTimeMillis() % 1000);
                        fakeData.name = "FAKE_USER_" + fakeData.id;
                        return fakeData;
                    }
                });
    }
}
