package com.xunchijn.dcappv1.data;

import com.xunchijn.dcappv1.map.model.MapResult;
import com.xunchijn.dcappv1.map.model.Point;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MapService {
    private MapApi mMapApi;

    public Observable<Response<Result<MapResult>>> getEmpPoint(String empSimid) {
        return mMapApi.GetEmpPoint(empSimid).observeOn(Schedulers.io());
    }
}
