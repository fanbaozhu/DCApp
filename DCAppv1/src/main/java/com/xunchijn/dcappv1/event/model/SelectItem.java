package com.xunchijn.dcappv1.event.model;

public class SelectItem {
    private String mId;
    private String mName;

    public SelectItem(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
