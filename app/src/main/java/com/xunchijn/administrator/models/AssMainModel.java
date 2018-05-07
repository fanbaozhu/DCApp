package com.xunchijn.administrator.models;

/**
 * Created by Administrator on 2017/10/18.
 */

public class AssMainModel extends BaseModel {
    private String ID;
    public void setID(String idp)
    {
        this.ID=idp;
    }
    public String getID()
    {
        return ID;
    }
    private String assCode;
    public void setAssCode(String assCodep){this.assCode=assCodep;}
    public String getAssCode(){return assCode;}

    /// 标题
    private String BiaoTi;

    public void setBiaoTi(String Bt){this.BiaoTi=Bt;}

    public String getBiaoTi(){return BiaoTi;}

    ///内容
    private String NeiRong;

    public void setNeiRong(String Nr){this.NeiRong=Nr;}

    public String getNeiRong(){return NeiRong;}

    ///状态
    private String ZhuangTai;

    public void setZhuangTai(String Zt){this.ZhuangTai=Zt;}

    public String getZhuangTai(){return ZhuangTai;}

    ///发布人
    private String FaBuRen;

    public void setFaBuRen(String Fbr){this.FaBuRen=Fbr;}

    public String getFaBuRen(){return FaBuRen;}

    ///发布时间
    private String FaBuTime;

    public void setFaBuTime(String Fbtime){this.FaBuTime=Fbtime;}

    public String getFaBuTime(){return FaBuTime;}

    // 考核类别
    private String assType;

    public  void setAssType(String assTypep)
    {
        this.assType=assTypep;
    }

    public String getAssType()
    {
        return assType;
    }

    // 考核类别名称
    private String assTypeName;

    public void setAssTypeName(String assTypeNamep){
        this.assTypeName = assTypeNamep;
    }

    public String getAssTypeName(){
        return  assTypeName;
    }

    // 考核对象
    private String assObj;

    public  void setAssObj(String assObjp)
    {
        this.assObj=assObjp;
    }

    public String getAssObj()
    {
        return assObj;
    }

    // 考核对象名称
    private String assObjName;

    public void setAssObjName(String assObjNamep)
    {
        this.assObjName=assObjNamep;
    }

    public String getAssObjName()
    {
        return assObjName;
    }

    // 考核事件
    private String assEvent;

    public  void setAssEvent(String assEventp)
    {
        this.assEvent=assEventp;
    }

    public String getAssEvent()
    {
        return assEvent;
    }

    //扣罚分值
    private String assScore;

    public  void setAssScore(String toScorep)
    {
        this.toScore=toScorep;
    }

    public String getAssScore()
    {
        return toScore;
    }

    // 考核事件名称
    private String assEventName;
    public void setAssEventName(String assEventNamep)
    {
        this.assEventName=assEventNamep;
    }

    public String getAssEventName()
    {
        return assEventName;
    }

    // 是否同步事件
    private String isSendEvent;

    public  void setIsSendEvent(String isSendEventp)
    {
        this.isSendEvent=isSendEventp;
    }

    public String getIsSendEvent()
    {
        return isSendEvent;
    }

    // 经度
    private String assLon;

    public void setAssLon(String assLonp)
    {
        this.assLon=assLonp;
    }

    public String getAssLon()
    {
        return assLon;
    }

    //员工编号
    private String assEmpcode;

    public  void setAssEmpcode(String assEmpcode)
    {
        this.assEmpcode=assEmpcode;
    }

    public String getAssEmpcode()
    {
        return assEmpcode;
    }

    // 维度
    private String assLat;

    public void setAssLat(String assLatp)
    {
        this.assLat=assLatp;
    }

    public String getAssLat()
    {
        return assLat;
    }

    // 状态
    private String assStatus;

    public void setAssStutas(String assStatusp)
    {
        this.assStatus=assStatusp;
    }

    public String getAssStutas()
    {
        return assStatus;
    }

    // 图片
    private String assImg1;

    public void setAssImg1(String assImg1p)
    {
        this.assImg1=assImg1p;
    }

    public String getAssImg1()
    {
        return assImg1;
    }

    // 图片2
    private String assImg2;

    public void setAssImg2(String assImg2p)
    {
        this.assImg2=assImg2p;
    }

    public String getAssImg2()
    {
        return assImg2;
    }

    // 图片2
    private String assImg3;

    public void setAssImg3(String assImg3p)
    {
        this.assImg3=assImg3p;
    }

    public String getAssImg3()
    {
        return assImg3;
    }

    // 状态名称
    private String assStatusName;

    public void setAssStatusName(String assStatusNamep)
    {
        this.assStatusName=assStatusNamep;
    }

    public String getAssStatusName()
    {
        return assStatusName;
    }

    // 扣罚分数
    private String toScore;
    public void setToScore(String toScore){this.toScore=toScore;}
    public String getToScore(){return toScore;}

    // 道路类型
    private String RoadType;
    public void setRoadType(String RoadType){this.RoadType=RoadType;}
    public String getRoadType(){return RoadType;}

    //区域类型
    private String AssRoad;
    public String getAssRoad(){return  AssRoad;}
    public void setAssRoad(String AssRoad){this.AssRoad=AssRoad;}


    /// 详细地址
    private String Addr;
    public void setAddr(String Addr)
    {
        this.Addr=Addr;
    }
    public String getAddr()
    {
        return Addr;
    }
}
