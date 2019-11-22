package com.bw.movie.presenter;

import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;


/**
 * 作者： 姓名
 * 日期： 2019/9/29 17:19
 */
public class LoginPresenter extends BasePresenter<IMainView.ILoginView> implements IBaseView {

    public void getDate(String email, String pwd) {
        OkHttp instance = OkHttp.getInstance();
        instance.doPost(email, pwd, new OkHttp.CallBack() {
            @Override
            public void ok(LoginBean loginBean) {
                getView().success(loginBean);
            }
        });
    }
}
