package com.xunchijn.dcappv1.event.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResult {
    private String fileName;//上传文件返回的文件名
    private Integer reportStatus;//上报事件状态标志
    @SerializedName("eventInfomation")
    private EventItem mEventItem;
    private List<EventItem> eventHistory;
    private List<SelectItem> checkDepartment;
    private List<SelectItem> checkSubDepartment;
    private List<SelectItem> checkType;
    private List<SelectItem> checkContent;

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

    public String getFileName() {
        return fileName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public List<EventItem> getEventHistory() {
        return eventHistory;
    }

    public EventItem getEventItem() {
        return mEventItem;
    }
}
