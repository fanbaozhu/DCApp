package com.xunchijn.dcappv1.common.data;

import com.xunchijn.dcappv1.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CommonService {
    private CommonApi mCommonApi;

    public CommonService() {
        mCommonApi = RetrofitProvider.get().create(CommonApi.class);
    }

    public Observable<Response<String>> login(String userAccount, String userPassword) {
        return mCommonApi.Login(userAccount, userPassword).observeOn(Schedulers.io());
    }
}
