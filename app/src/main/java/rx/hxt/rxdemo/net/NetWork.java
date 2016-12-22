package rx.hxt.rxdemo.net;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.hxt.rxdemo.net.api.FakeApi;
import rx.hxt.rxdemo.net.api.GankApi;
import rx.hxt.rxdemo.net.api.ZhuangbiApi;

/**
 * Created by Administrator on 2016/12/22.
 */

public class NetWork {
    private static ZhuangbiApi zhuangbiApi;
    private static FakeApi fakeApi;
    private static GankApi gankApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhuangbiApi getZhuangbiApi() {
        if (zhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static GankApi getGankApi() {
        if (null == gankApi) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    public static FakeApi getFakeApi() {
        if (null == fakeApi) {
            fakeApi = new FakeApi();
        }
        return fakeApi;
    }
}
