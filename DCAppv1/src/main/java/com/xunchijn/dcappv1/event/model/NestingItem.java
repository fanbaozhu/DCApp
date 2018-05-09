package com.xunchijn.dcappv1.event.model;

import java.util.List;

public class DepartmentEntity {
    private String mId;
    private String mName;
    private String mSubtitle;
    private List<SelectItem> mItems;

    public DepartmentEntity(String id, String name, String subtitle) {
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

    public List<SelectItem> getItems() {
        return mItems;
    }

    public void setItems(List<SelectItem> items) {
        mItems = items;
    }
}
