package com.xunchijn.sichuan.event.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.sichuan.util.TimeUtils;

public class EventItem {
    private String eventId;
    private String eventDescribe;
    private String eventPosition;
    private String eventPictureName;
    @SerializedName("checkDepartment")
    private String eventDepartment;
    private String eventSubDepartment;
    private String checkType;
    private String checkContent;
    private String eventStatus;
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
