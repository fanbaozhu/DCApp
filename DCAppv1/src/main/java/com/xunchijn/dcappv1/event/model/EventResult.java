package com.xunchijn.dcappv1.event.model;

import java.util.List;

public class EventResult {
    private List<SelectItem> checkDepartment;
    private List<SelectItem> checkSubDepartment;
    private List<SelectItem> checkType;
    private List<SelectItem> checkContent;
    private String fileName;//上传文件返回的文件名
    private Integer reportStatus;

    public String getFileName() {
        return fileName;
    }

    private EventEntity eventInformation;

    public EventEntity getEventInformation() {
        return eventInformation;
    }

    public List<SelectItem> getCheckDepartment() {
        return checkDepartment;
    }

    public List<SelectItem> getCheckSubDepartment() {
        return checkSubDepartment;
    }

    public List<SelectItem> getCheckType() {
        return checkType;
    }

    public List<SelectItem> getCheckContent() {
        return checkContent;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }
}
