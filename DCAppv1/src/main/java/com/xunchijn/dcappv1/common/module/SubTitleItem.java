package com.xunchijn.dcappv1.common.module;

public class SubTitleItem {
    private int mId;
    private String mName;
    private int mResourceId;

    public SubTitleItem(int id, String name, int resourceId) {
        mId = id;
        mName = name;
        mResourceId = resourceId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getResourceId() {
        return mResourceId;
    }
}
