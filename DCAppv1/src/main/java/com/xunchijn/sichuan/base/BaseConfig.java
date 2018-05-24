package com.xunchijn.sichuan.base;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.common.module.SettingItem;
import com.xunchijn.sichuan.common.module.SubTitleItem;
import com.xunchijn.sichuan.common.module.TitleItem;

import java.util.ArrayList;
import java.util.List;

public class BaseConfig {
    public static final String WEBSITE = "http://www.xunchijn.com/Reception/About.aspx";

    public static List<TitleItem> getMainTitles() {
        List<TitleItem> titleItems = new ArrayList<>();
        List<SubTitleItem> list = new ArrayList<>();
        SubTitleItem item = new SubTitleItem(1, "车辆信息", R.mipmap.ic_car_info);
        SubTitleItem item1 = new SubTitleItem(2, "车辆定位", R.mipmap.ic_location_car);
        SubTitleItem item2 = new SubTitleItem(3, "车辆轨迹", R.mipmap.ic_trace);
        list.add(item);
        list.add(item1);
        list.add(item2);
        TitleItem titleItem = new TitleItem("车辆管理", R.mipmap.ic_car_manage, list);
        titleItems.add(titleItem);

        List<SubTitleItem> list1 = new ArrayList<>();
        SubTitleItem item3 = new SubTitleItem(4, "人员信息", R.mipmap.ic_user_info);
        SubTitleItem item4 = new SubTitleItem(5, "人员定位", R.mipmap.ic_location_car);
        SubTitleItem item5 = new SubTitleItem(6, "人员轨迹", R.mipmap.ic_trace);
        list1.add(item3);
        list1.add(item4);
        list1.add(item5);
        TitleItem titleItem1 = new TitleItem("人员管理", R.mipmap.ic_user_manage, list1);
        titleItems.add(titleItem1);

        List<SubTitleItem> list2 = new ArrayList<>();
        SubTitleItem item6 = new SubTitleItem(7, "统计报表", R.mipmap.ic_statistics);
        SubTitleItem item7 = new SubTitleItem(8, "上报历史", R.mipmap.ic_report_history);
        SubTitleItem item8 = new SubTitleItem(9, "事件上报", R.mipmap.ic_report);
        list2.add(item6);
        list2.add(item7);
        list2.add(item8);
        TitleItem titleItem2 = new TitleItem("事件与其他", R.mipmap.ic_report_manage, list2);
        titleItems.add(titleItem2);

        return titleItems;
    }

    public static List<SettingItem> getSettingItems() {
        List<SettingItem> items = new ArrayList<>();
        SettingItem item1 = new SettingItem(0, "所在位置", "");
        SettingItem item2 = new SettingItem(1, "部门", "");
        SettingItem item3 = new SettingItem(2, "子部门", "");
        SettingItem item4 = new SettingItem(3, "考核类型", "");
        SettingItem item5 = new SettingItem(4, "考核项目", "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        return items;
    }

    public static List<SettingItem> getMineSettings() {
        List<SettingItem> items = new ArrayList<>();
        SettingItem item1 = new SettingItem(0, "消息通知", "");
        SettingItem item2 = new SettingItem(1, "修改密码", "");
        SettingItem item3 = new SettingItem(2, "意见反馈", "");
        SettingItem item4 = new SettingItem(3, "关于我们", "");
        SettingItem item5 = new SettingItem(4, "退出登录", "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        return items;
    }
}
