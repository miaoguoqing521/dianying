package com.bw.movie.view.interfaces;

import com.bw.movie.model.bean.AttentCinemaBean;
import com.bw.movie.model.bean.AttentMovieBean;
import com.bw.movie.model.bean.BookMovieBean;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 16:32
 */
public interface IAttent {
    interface IAttentMovie extends IBaseView{
        void succe(AttentMovieBean attentMovieBean);
    }
    interface IAttentCinema extends IBaseView{
        void success(AttentCinemaBean attentCinemaBean);
    }
    interface INoticeView extends IBaseView{
        void succ(BookMovieBean bookMovieBean);
    }
}
