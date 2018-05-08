package com.xunchijn.dcappv1.event.model;

public class EventEntity {
    private String eventId;
    private String eventDescribe;
    private String eventPosition;
    private String eventPictureUrl;
    private String checkDepartment;
    private String checkType;
    private String checkContent;
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

    public String getEventPictureUrl() {
        return eventPictureUrl;
    }

    public String getCheckDepartment() {
        return checkDepartment;
    }

    public String getCheckType() {
        return checkType;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getEventStatus() {
        return eventStatus;
    }
}
