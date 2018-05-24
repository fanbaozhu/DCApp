package com.xunchijn.sichuan.common.module;

import java.util.List;

public class TitleItem {
    private String mName;
    private int mResourceId;
    private List<SubTitleItem> mSubTitleItems;

    public TitleItem(String name, int resourceId, List<SubTitleItem> subTitleItems) {
        mName = name;
        mResourceId = resourceId;
        mSubTitleItems = subTitleItems;
    }

    public String getName() {
        return mName;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public List<SubTitleItem> getSubTitleItems() {
        return mSubTitleItems;
    }
}
