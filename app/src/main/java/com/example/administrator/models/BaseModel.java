package com.example.administrator.models;

/**
 * Created by Administrator on 2016/1/6.
 */
public class BaseModel {
    // 删除标示
    private String deleteFlag;

    public void setDeleteFlag(String deleteFlagp)
    {
        this.deleteFlag=deleteFlagp;
    }

    public String getDeleteFlag()
    {
        return deleteFlag;
    }

    // 创建时间
    private String createDate;

    public void setCreateDate(String createDatep)
    {
        this.createDate=createDatep;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    // 创建人
    private String createUserId;

    public void setCreateUserId(String createUserIdp)
    {
        this.createUserId=createUserIdp;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }

    // 备注
    private String remark;

    public void setRemark(String remarkp)
    {
        this.remark=remarkp;
    }

    public String getRemark()
    {
        return remark;
    }

    // 时间
    private String updateDate;

    public void setUpdateDate(String updateDatep)
    {
        this.updateDate=updateDatep;
    }

    public String getUpdateDate()
    {
        return updateDate;
    }

    // 更新人
    private String updateUserId;

    public void setUpdateUserId(String updateUserIdp)
    {
        this.updateUserId=updateUserIdp;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
}
