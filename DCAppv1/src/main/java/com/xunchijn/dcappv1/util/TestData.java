package com.xunchijn.dcappv1.util;

import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.event.model.EventEntity;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.event.model.SettingItem;
import com.xunchijn.dcappv1.map.model.Point;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<SettingItem> getSettingItems() {
        List<SettingItem> items = new ArrayList<>();
        SettingItem item1 = new SettingItem(0, "所在位置", "");
        SettingItem item2 = new SettingItem(1, "部门", "");
        SettingItem item3 = new SettingItem(2, "子部门", "");
        SettingItem item4 = new SettingItem(3, "事件类型", "");
        SettingItem item5 = new SettingItem(4, "考核项目", "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        return items;
    }

    public static List<NestingItem> getDepartments(String type) {
        List<NestingItem> list = new ArrayList<>();
        NestingItem history = new NestingItem("0", "历史", "清空");
        List<SelectItem> items = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            SelectItem item = new SelectItem(String.valueOf(i), String.format(type.equals("车辆") ? "川B2345%s" : "郎德贵%s", i));
            items.add(item);
        }
        history.setItems(items);

        NestingItem nestingItem = new NestingItem("1", String.format("所有%s", type), "筛选");
        List<SelectItem> departments = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SelectItem item = new SelectItem(String.valueOf(i), String.format(type.equals("车辆") ? "川B2345%s" : "郎德贵%s", i % 10));
            departments.add(item);
        }
        nestingItem.setItems(departments);
        list.add(history);
        list.add(nestingItem);
        return list;
    }

    public static List<SelectItem> getSelectItems(int count) {
        List<SelectItem> selectItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SelectItem item = new SelectItem(String.valueOf(i), String.format("部门%s", i));
            selectItems.add(item);
        }
        return selectItems;
    }

    public static List<EventEntity> getEventHistory(int count) {
        List<EventEntity> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            EventEntity item = new EventEntity("事件描述：这个地方做的不好，需要重新做一遍",
                    "山东省济南市高新区齐鲁文化创新基地9号楼", "",
                    "2018年05年15日 20:20", "未处理");
            items.add(item);
        }
        return items;
    }

    public static ArrayList<UserInfo> getEmpLocation(int count) {
        ArrayList<UserInfo> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            UserInfo item = new UserInfo();
            item.setUserPoint("116.32,34.27");
            items.add(item);
        }
        return items;
    }

    public static ArrayList<Point> getEmpPoint() {
        ArrayList<Point> items = new ArrayList<>();
        Point item0 = new Point("117.022921,36.670375");
        Point item1 = new Point("117.023334,36.670346");
        Point item2 = new Point("117.024789,36.670506");
        Point item3 = new Point("117.025903,36.670607");
        Point item4 = new Point("117.027789,36.67081");
        Point item5 = new Point("117.030161,36.671056");

        items.add(item0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        return items;
    }
}