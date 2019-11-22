package com.bw.movie.presenter;

import com.bw.movie.model.bean.VersionUpdateBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/11 20:40
 */
public class VersionUpdatePresenter extends BasePresenter<IMainView.VersionUpdateView> implements IBaseView {
    public void getDate(String userId, String sessionId, int versionCode) {
        OkHttp instance = OkHttp.getInstance();
        instance.doVersionUpdate(new OkHttp.CallUpdate() {
            @Override
            public void versionupdate(VersionUpdateBean versionUpdateBean) {
                getView().versionUpdate(versionUpdateBean);
            }
        },userId,sessionId,versionCode);
    }
}
