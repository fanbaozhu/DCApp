package com.xunchijn.sichuan.common.module;

import android.text.TextUtils;

import java.io.Serializable;

public class SettingItem implements Serializable {
    private String mId;
    private int index;
    private String title;
    private String subtitle;

    public SettingItem(int index, String title, String subtitle) {
        this.index = index;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return TextUtils.isEmpty(title) ? "" : title;
    }

    public String getSubtitle() {
        return TextUtils.isEmpty(subtitle) ? "" : subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "SettingItem{" +
                "mId='" + mId + '\'' +
                ", index=" + index +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
