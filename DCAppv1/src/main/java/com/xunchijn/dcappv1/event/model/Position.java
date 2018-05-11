package com.xunchijn.dcappv1.event.model;

public class Position {
    private Double mX;
    private Double mY;

    public Position(Double x, Double y) {
        mX = x;
        mY = y;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", mX, mY);
    }
}
