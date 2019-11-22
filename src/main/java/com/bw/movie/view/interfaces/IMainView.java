package com.bw.movie.view.interfaces;

import com.bw.movie.model.bean.AddCommentBean;
import com.bw.movie.model.bean.AttentionBean;
import com.bw.movie.model.bean.BanBean;
import com.bw.movie.model.bean.CinemaBean;
import com.bw.movie.model.bean.CinemaEvaluationBean;
import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.bw.movie.model.bean.FileImageBean;
import com.bw.movie.model.bean.FileSeatBean;
import com.bw.movie.model.bean.IdNameBean;
import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.MovieCinemaDateBean;
import com.bw.movie.model.bean.MovieIdCinemaBean;
import com.bw.movie.model.bean.NearCinema;
import com.bw.movie.model.bean.PriceMovieQueryCinemaBean;
import com.bw.movie.model.bean.QueryCinemaDetails;
import com.bw.movie.model.bean.QueryCommentBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.model.bean.QueryRegionBean;
import com.bw.movie.model.bean.RangeBean;
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.model.bean.RecommendedCinemaBean;
import com.bw.movie.model.bean.ReginBean;
import com.bw.movie.model.bean.RegionCinemaBean;
import com.bw.movie.model.bean.SchduBean;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.bean.SearchCinemaBean;
import com.bw.movie.model.bean.SendEmail;
import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.model.bean.VersionUpdateBean;
import com.bw.movie.model.bean.WxBean;

/**
 * 作者： 姓名
 * 日期： 2019/9/29 17:17
 */
public interface IMainView {
    interface ILoginView extends IBaseView{
        void success(LoginBean loginBean);
    }
    interface IBanView extends IBaseView{
        void succ(BanBean banBean);
        void suc(ShangMovieBean shangMovieBean);
        void ji(JiMoviewBean jiMoviewBean);
        void re(ReMovieBean reMovieBean);
    }
    interface IRecommend extends IBaseView{
        void reco(RecommendedCinemaBean recommendedCinemaBean);
    }
    interface INearView extends IBaseView{
        void nearcinema(NearCinema nearCinema);
    }
    interface IReginView extends IBaseView{
        void send(SendEmail sendEmail);
        void regin(ReginBean reginBean);
    }
    interface IMovieView extends IBaseView{
        void movie(QueryMovieBean queryMovieBean);
        void attent(AttentionBean attentionBean);
    }
    interface IdNameView extends IBaseView{
        void idname(IdNameBean idNameBean);
        void image(FileImageBean fileImageBean);
    }
    //查询区域
    interface IRangeView extends IBaseView{

        //查询区域列表
        void cinema(CinemaBean cinemaBean);

        //根据区域进行查询
        void range(RangeBean rangeBean);

        //查询一周排期的时间
        void schdu(SchduBean schduBean);

        //根据电影id，时间 查询播放影院信息
        void movieIdCinema(MovieIdCinemaBean movieIdCinemaBean);

        //根据电影价格查询播放影院信息
        void priceMovie(PriceMovieQueryCinemaBean priceMovieQueryCinemaBean);
    }

    //根据区域查询影院
    interface IRegionCinema extends IBaseView{
        void region(QueryRegionBean queryRegionBean);
        void regioncinema(RegionCinemaBean regionCinemaBean);
    }
    //搜索电影
    interface ISearch extends IBaseView{
        void search(SearchBean searchBean);
    }
    //添加对影片的评论
    interface AddComentView extends IBaseView{
        void addComent(AddCommentBean addCommentBean);
    }
    //查询电影的评论
    interface QueryCommentView extends IBaseView{
        void queryComment(QueryCommentBean queryCommentBean);
    }
    //选座
    interface DateView extends IBaseView{
        void cinemaDate(MovieCinemaDateBean movieCinemaDateBean);
        void fileSeat(FileSeatBean fileSeatBean);
    }
    //更新
    interface VersionUpdateView extends IBaseView{
        void versionUpdate(VersionUpdateBean versionUpdateBean);
    }
    //根据电影院名查询影院
    interface SearchCinemaView extends IBaseView{
        void searchCinema(SearchCinemaBean searchCinemaBean);
    }
    //查询影院详情
    interface DetaulsView extends IBaseView{
        void details(QueryCinemaDetails queryCinemaDetails);
    }
    //查询影院评价
    interface CinemaEvaluetion extends IBaseView{
        void evaluetion(CinemaEvaluationBean cinemaEvaluationBean);
    }
    //查询一周排期的时间
    interface OneDateView extends IBaseView{
        void schdu(SchduBean schduBean);
    }
    interface SchedulingView extends IBaseView{
        void scheduling(CinemaSchedulingBean cinemaSchedulingBean);
    }
}
