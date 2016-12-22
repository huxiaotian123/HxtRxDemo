package rx.hxt.rxdemo.net.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.hxt.rxdemo.model.GankBeautyResult;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeautys(@Path("number")int number,@Path("page")int page);
}
