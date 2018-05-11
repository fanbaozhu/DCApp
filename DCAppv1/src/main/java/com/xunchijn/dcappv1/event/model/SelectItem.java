package com.xunchijn.dcappv1.event.model;

import com.google.gson.annotations.SerializedName;

public class SelectItem {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
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
