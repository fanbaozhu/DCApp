package com.xunchijn.tongshan.statistic.model;

import java.util.List;

public class StatisticResult {
	private List<DomainItem> carList;
	private List<DomainItem> carDetails;
	private List<DomainItem> userList;
	private List<DomainItem> userDetails;

	public List<DomainItem> getUserList() {
		return userList;
	}

	public List<DomainItem> getUserDetails() {
		return userDetails;
	}

	public List<DomainItem> getCarList() {
		return carList;
	}

	public List<DomainItem> getCarDetails() {
		return carDetails;
	}
}
