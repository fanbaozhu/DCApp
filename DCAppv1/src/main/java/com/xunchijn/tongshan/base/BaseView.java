package com.xunchijn.tongshan.base;

public interface BaseView<T> {
    void showError(String error);

    void setPresenter(T presenter);
}
