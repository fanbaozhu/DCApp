package com.xunchijn.dcappv1.test;

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