package com.xunchijn.dcappv1.util;

import com.xunchijn.dcappv1.event.model.SettingItem;

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
}