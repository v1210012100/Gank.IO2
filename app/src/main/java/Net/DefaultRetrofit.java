package Net;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public class DefaultRetrofit {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GankUrl.GANK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getAllResult(final String type, final int count, final int pageIndex, final ICallBack<AllResult> callBack) {
        Call<AllResult> allData = retrofit.create(GankCall.class).getAllDate(type, count, pageIndex);
        final String key = type + count + pageIndex;
        allData.enqueue(new Callback<AllResult>() {
            @Override
            public void onResponse(Response<AllResult> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    AllResult allResult = response.body();
                    if (!allResult.getError()) {
                        callBack.onSuccess(type, key, allResult);
                    } else {
                        callBack.onFailure(type, key, "数据错误");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callBack.onFailure(type, key, "请求失败");
            }
        });
    }
}
