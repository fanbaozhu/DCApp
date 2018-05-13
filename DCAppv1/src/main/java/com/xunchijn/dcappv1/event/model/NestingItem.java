package com.xunchijn.dcappv1.event.model;

import java.util.List;

/*
 * 嵌套外部类
 * */
public class NestingItem {
    private String mId;
    private String mName;
    private String mSubtitle;
    //嵌套内部列表数据
    private List<? extends SelectItem> mItems;

    public NestingItem(String id, String name, String subtitle) {
        mId = id;
        mName = name;
        mSubtitle = subtitle;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public List<? extends SelectItem> getItems() {
        return mItems;
    }

    public void setItems(List<? extends SelectItem> items) {
        mItems = items;
    }
}
