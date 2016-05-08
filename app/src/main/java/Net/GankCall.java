package Net;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public interface GankCall {
    @GET("data/{type}/{count}/{pageIndex}")
    Call<AllResult> getAllDate(@Path("type") String type,
                                   @Path("count") int count,
                                   @Path("pageIndex") int pageIndex
    );
}
