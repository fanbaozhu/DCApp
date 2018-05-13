package com.xunchijn.dcappv1.map.model;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.event.model.SelectItem;

public class User extends SelectItem {
    public User(String id, String name) {
        super(id, name);
    }

    @SerializedName("position")
    private String mPosition;

    public String getPosition() {
        return mPosition;
    }
}
