package com.xunchijn.dcappv1.event.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.util.TimeUtils;

public class EventItem {
    private String eventId;
    private String eventDescribe;
    @SerializedName("eventPositon")
    private String eventPosition;
    private String eventPictureName;
    private String reportTime;
    private String eventStatus;

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
}
