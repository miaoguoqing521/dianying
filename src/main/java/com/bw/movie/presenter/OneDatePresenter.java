package com.bw.movie.presenter;

import com.bw.movie.model.bean.SchduBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 20:46
 */
public class OneDatePresenter extends BasePresenter<IMainView.OneDateView> implements IBaseView {
    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doSchdu(new OkHttp.Callschdu() {
            @Override
            public void schdu(SchduBean schduBean) {
                getView().schdu(schduBean);
            }
        });
    }
}
