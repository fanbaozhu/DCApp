package com.xunchijn.tongshan.event.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.tongshan.util.TimeUtils;

public class EventItem {
    private String eventId;
    //描述
    private String eventDescribe;
    //位置
    private String eventPosition;
    //照片
    private String eventPictureName;
    @SerializedName("checkDepartment")
    private String eventDepartment;
    //子部门
    private String eventSubDepartment;
    //考核类型
    private String checkType;
    //考核详情
    private String checkContent;
    //状态
    private String eventStatus;
    //上报时间
    private String reportTime;

    public String getEventId() {
        return eventId;
    }

    public String getEventDescribe() {
        return eventDescribe;
    }

    public String getEventPosition() {
        return eventPosition;
    }

    public String getEventPictureName() {
        return eventPictureName;
    }

    public String getReportTime() {
        if (TextUtils.isEmpty(reportTime)) {
            return "";
        }
        return TimeUtils.getStrTime(reportTime);
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public String getEventDepartment() {
        return eventDepartment;
    }

    public String getEventSubDepartment() {
        return eventSubDepartment;
    }

    public String getCheckType() {
        return checkType;
    }

    public String getCheckContent() {
        return checkContent;
    }
}
