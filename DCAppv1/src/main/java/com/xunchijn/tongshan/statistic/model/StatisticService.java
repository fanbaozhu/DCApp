package com.xunchijn.tongshan.statistic.model;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StatisticService {
	private StatisticApi mStatisticApi;

	public StatisticService() {
		mStatisticApi = RetrofitProvider.get().create(StatisticApi.class);
	}

	//车辆进出区域报表
	public Observable<Response<Result<StatisticResult>>> GetCarDomains(String startTime, String Name) {
		return mStatisticApi.GetCarDomains(startTime, Name).subscribeOn(Schedulers.io());
	}

	//人员工作报表
	public Observable<Response<Result<StatisticResult>>> GetUserDomains(String startTime, String Name) {
		return mStatisticApi.GetUserDomains(startTime, Name).subscribeOn(Schedulers.io());
	}

	//车辆出入详细表
	public Observable<Response<Result<StatisticResult>>> GetCarDomainsDetails(String startTime, String simId) {
		return mStatisticApi.GetCarDomainsDetails(startTime, simId).subscribeOn(Schedulers.io());
	}

	//人员工作详细表
	public Observable<Response<Result<StatisticResult>>> GetUserWorkDetails(String startTime, String simId) {
		return mStatisticApi.GetUserWorkDetails(startTime, simId).subscribeOn(Schedulers.io());
	}

	//车辆加水/垃圾倾倒表
	public Observable<Response<Result<StatisticResult>>> GetCarOtherDomains(String startTime, String Name, String type) {
		return mStatisticApi.GetCarOtherDomains(startTime, Name, type).subscribeOn(Schedulers.io());
	}

	//车辆加水/垃圾倾倒详情表
	public Observable<Response<Result<StatisticResult>>> GetCarOtherDomainsDetails(String startTime, String simId) {
		return mStatisticApi.GetCarOtherDomainsDetails(startTime, simId).subscribeOn(Schedulers.io());
	}

	//人员考勤报表
	public Observable<Response<Result<StatisticResult>>> GetUserAttendance(String startTime, String Name, String endTime) {
		return mStatisticApi.GetUserAttendance(startTime, Name, endTime).subscribeOn(Schedulers.io());
	}
}
