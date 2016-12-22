package rx.hxt.rxdemo.net.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.hxt.rxdemo.model.ZhuangbiImage;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface ZhuangbiApi {
    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
