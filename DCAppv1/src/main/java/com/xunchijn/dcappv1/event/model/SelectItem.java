package com.xunchijn.dcappv1.event.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectItem implements Serializable {
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
