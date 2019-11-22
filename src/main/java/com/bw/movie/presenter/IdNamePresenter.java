package com.bw.movie.presenter;

import com.bw.movie.model.bean.IdNameBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/18 15:59
 */
public class IdNamePresenter extends BasePresenter<IMainView.IdNameView> implements IBaseView {

    public void getDate(String userId, String sessionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doIdName(new OkHttp.CallIdName() {
            @Override
            public void idname(IdNameBean idNameBean) {
                getView().idname(idNameBean);
            }
        },userId,sessionId);
    }
}
