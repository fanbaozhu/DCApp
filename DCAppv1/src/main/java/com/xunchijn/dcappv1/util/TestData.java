package com.xunchijn.dcappv1.util;

import com.xunchijn.dcappv1.event.model.DepartmentEntity;
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

    public static List<DepartmentEntity> getDepartments() {
        List<DepartmentEntity> list = new ArrayList<>();
        DepartmentEntity entity = new DepartmentEntity("1", "部门1");
        List<DepartmentEntity> subDepartment = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DepartmentEntity entity1 = new DepartmentEntity(String.valueOf(i), String.format("部门%s", i));
            subDepartment.add(entity1);
        }
        entity.setSubDepartment(subDepartment);
        list.add(entity);
        list.add(entity);
        return list;
    }
}
