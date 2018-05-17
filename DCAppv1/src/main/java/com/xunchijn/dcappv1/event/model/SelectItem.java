package com.xunchijn.dcappv1.event.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectItem implements Serializable {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    private int mIndex;
    private boolean mSelected;

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

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    @Override
    public String toString() {
        return "SelectItem{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mIndex=" + mIndex +
                ", mSelected=" + mSelected +
                '}';
    }
}
