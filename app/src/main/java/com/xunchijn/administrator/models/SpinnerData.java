package com.xunchijn.administrator.models;

/**
 * Created by Administrator on 2016/1/6.
 * spinner实现text，value帮助类
 */
public class SpinnerData {
    private String value = "";
    private String text = "";

    public SpinnerData() {
        value = "";
        text = "";
    }

    public SpinnerData(String _value, String _text) {
        value = _value;
        text = _text;
    }

    @Override
    public String toString() {

        return text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
