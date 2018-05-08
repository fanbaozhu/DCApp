package com.xunchijn.dcappv1.event.model;

import java.util.List;

public class DepartmentEntity {
    private String mId;
    private String mName;
    private String mSubtitle;

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    private List<DepartmentEntity> mSubDepartment;
    private boolean mSelected;

    public DepartmentEntity(String id, String name) {
        mId = id;
        mName = name;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public List<DepartmentEntity> getSubDepartment() {
        return mSubDepartment;
    }

    public void setSubDepartment(List<DepartmentEntity> subDepartment) {
        mSubDepartment = subDepartment;
    }
}
