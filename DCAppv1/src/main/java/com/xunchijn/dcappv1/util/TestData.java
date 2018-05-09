package com.xunchijn.dcappv1.util;

import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
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
}
