package com.bw.movie.presenter;

import com.bw.movie.model.bean.ReginBean;
import com.bw.movie.model.bean.SendEmail;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/12 10:48
 */
public class ReginPresenter extends BasePresenter<IMainView.IReginView> implements IBaseView {
    public void getDate(String trim) {
        OkHttp instance = OkHttp.getInstance();
        instance.doSend(new OkHttp.BackSend() {
            @Override
            public void send(SendEmail sendEmail) {
                getView().send(sendEmail);
            }
        },trim);
    }

    public void getDates(String name, String encrypt, String s, String s1) {
        OkHttp instance = OkHttp.getInstance();
        instance.doRegin(new OkHttp.BackRegin() {
            @Override
            public void regin(ReginBean reginBean) {
                getView().regin(reginBean);
            }
        },name,encrypt,s,s1);
    }
}
