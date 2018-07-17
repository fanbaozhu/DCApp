package com.xunchijn.tongshan.statistic.model;

public class DomainItem {
	private String carId;
	private String carName;
	private String carDept;
	private String carType;
	//频率
	private String frequency;
	//时间差
	private String timeDifference;
	//量
	private String quantity;
	private String startTime;
	private String endTime;
	private String ton;
	private String number;
	//标识
	private String flag;
	private String userId;
	private String userName;
	private String userDept;
	private String userStatus;
	//里程
	private String mileage;

	public String getMileage() {
		return mileage;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserDept() {
		return userDept;
	}

	public String getFlag() {
		return flag;
	}

	public String getNumber() {
		return number;
	}

	public String getTon() {
		return ton;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getCarId() {
		return carId;
	}

	public String getCarName() {
		return carName;
	}

	public String getCarDept() {
		return carDept;
	}

	public String getCarType() {
		return carType;
	}

	public String getFrequency() {
		return frequency;
	}

	public String getTimeDifference() {
		return timeDifference;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}
