package com.bw.movie.presenter;

import com.bw.movie.view.interfaces.IBaseView;


/**
 * 作者： 姓名
 * 日期： 2019/9/29 16:28
 */
public class BasePresenter<T extends IBaseView> {
    public T t;
    public void attach(T loginActivity) {
        t=loginActivity;
    }
    public T getView(){
        return t;
    }
}
