package com.xunchijn.dcappv1.map.model;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class EmpItem {
    private String empDept;
    private String empName;
    private String empZoon;
    private String empAddress;
    private String empStatus;
    private String empScanTime;
    private String empPoint;

    public String getEmpPoint() {
        return empPoint;
    }

    public void setEmpPoint(String empPoint) {
        this.empPoint = empPoint;
    }

    public EmpItem(String empDept, String empName, String empZoon, String empAddress, String empStatus, String empScanTime, String empPoint) {
        this.empDept = empDept;
        this.empName = empName;
        this.empZoon = empZoon;
        this.empAddress = empAddress;
        this.empStatus = empStatus;
        this.empScanTime = empScanTime;
        this.empPoint = empPoint;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpZoon() {
        return empZoon;
    }

    public void setEmpZoon(String empZoon) {
        this.empZoon = empZoon;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpScanTime() {
        return empScanTime;
    }

    public void setEmpScanTime(String empScanTime) {
        this.empScanTime = empScanTime;
    }
}
