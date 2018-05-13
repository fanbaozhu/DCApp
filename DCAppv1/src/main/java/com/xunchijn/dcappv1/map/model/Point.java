package com.xunchijn.dcappv1.map.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class Point  {
  private double x;
  private double y;

    public Point(String point) {
        String[] ass = point.split(",");
        if (ass.length==2){
            y=Double.valueOf(ass[0]);
            x=Double.valueOf(ass[1]);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
