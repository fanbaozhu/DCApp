package com.xunchijn.tongshan.statistic.model;

import java.util.List;

public class StatisticResult {
	private List<DomainItem> carList;
	private List<DomainItem> carDetails;
	private List<DomainItem> userList;
	private List<DomainItem> userDetails;
	private List<DomainItem> userAttendanceList;
	private List<DomainItem> attendanceDetails;

	public List<DomainItem> getAttendanceDetails() {
		return attendanceDetails;
	}

	public List<DomainItem> getUserAttendanceList() {
		return userAttendanceList;
	}

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
