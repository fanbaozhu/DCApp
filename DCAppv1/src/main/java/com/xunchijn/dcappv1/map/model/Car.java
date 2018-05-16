package com.xunchijn.dcappv1.map.model;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.event.model.SelectItem;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午2:50
 * Description:
 **/
public class Car extends SelectItem {

    public Car(String id, String name) {
        super(id, name);
    }

    @SerializedName("position")
    private String mPosition;

    public String getPosition() {
        return mPosition;
    }
}
