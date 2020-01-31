package com.bisboguicompany.chispas.quinela;

public interface BasePresenter<T> {

    void takeView(T view);


    void dropView();

}