package com.bw.movie.presenter;

import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMoreView;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 19:55
 */
public class BePresenter extends BasePresenter<IMoreView.BeView> implements IBaseView {
    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doJi(new OkHttp.BackJi() {
            @Override
            public void Ji(JiMoviewBean jiMoviewBean) {
                getView().ji(jiMoviewBean);
            }
        });
    }
}
