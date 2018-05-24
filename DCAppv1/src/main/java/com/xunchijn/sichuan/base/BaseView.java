package com.xunchijn.sichuan.base;

public interface BaseView<T> {
    void showError(String error);

    void setPresenter(T presenter);
}
