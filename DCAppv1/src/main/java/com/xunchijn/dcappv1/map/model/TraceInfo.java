package com.xunchijn.dcappv1.map.model;

import android.text.TextUtils;

public class TraceInfo {
    private String position;
    private String time;

    public String getPosition() {
        return position;
    }

    public String getTime() {
        return time;
    }

    public double getPositionX() {
        if (TextUtils.isEmpty(position)) {
            return 0;
        }
        String[] anchor = position.split(",");
        if (anchor.length == 2) {
            return Double.valueOf(anchor[1]);
        }
        return 0;
    }

    public double getPositionY() {
        if (TextUtils.isEmpty(position)) {
            return 0;
        }
        String[] anchor = position.split(",");
        if (anchor.length == 2) {
            return Double.valueOf(anchor[0]);
        }
        return 0;
    }
}
