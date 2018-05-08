package com.xunchijn.dcappv1.base;

public interface BaseView<T> {
    void showError(String error);

    void setPresenter(T presenter);
}
