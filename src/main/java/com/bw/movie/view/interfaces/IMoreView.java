package com.bw.movie.view.interfaces;

import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.model.bean.ShangMovieBean;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 19:48
 */
public interface IMoreView {
    //即将上映
    interface BeView extends IBaseView{
        void ji(JiMoviewBean jiMoviewBean);
    }
    //热门电影
    interface HotView extends IBaseView{
        void re(ReMovieBean reMovieBean);
    }
    //正在热映
    interface HeatView extends IBaseView{
        void suc(ShangMovieBean shangMovieBean);
    }
}
