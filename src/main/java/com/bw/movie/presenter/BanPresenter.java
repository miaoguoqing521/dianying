package com.bw.movie.presenter;

import com.bw.movie.model.bean.BanBean;
import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/10 19:01
 */
public class BanPresenter extends BasePresenter<IMainView.IBanView> implements IBaseView {

    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doBan(new OkHttp.BackBan() {
            @Override
            public void ban(BanBean banBean) {
                getView().succ(banBean);
            }
        });
        instance.doShang(new OkHttp.BackShang() {
            @Override
            public void shang(ShangMovieBean shangMovieBean) {
                getView().suc(shangMovieBean);
            }
        });
        instance.doJi(new OkHttp.BackJi() {
            @Override
            public void Ji(JiMoviewBean jiMoviewBean) {
                getView().ji(jiMoviewBean);
            }
        });
        instance.doRe(new OkHttp.BackRe() {
            @Override
            public void Re(ReMovieBean reMovieBean) {
                getView().re(reMovieBean);
            }
        });
    }
}
