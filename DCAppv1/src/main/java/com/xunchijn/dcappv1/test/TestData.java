package com.xunchijn.dcappv1.test;

import com.xunchijn.dcappv1.map.model.Point;

import java.util.ArrayList;

public class TestData {

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