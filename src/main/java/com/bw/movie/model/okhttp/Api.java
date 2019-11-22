package com.bw.movie.model.okhttp;

import com.bw.movie.model.bean.AddCommentBean;
import com.bw.movie.model.bean.AttentActionBean;
import com.bw.movie.model.bean.AttentCinemaBean;
import com.bw.movie.model.bean.AttentMovieBean;
import com.bw.movie.model.bean.AttentionBean;
import com.bw.movie.model.bean.BanBean;
import com.bw.movie.model.bean.BookMovieBean;
import com.bw.movie.model.bean.CinemaBean;
import com.bw.movie.model.bean.CinemaEvaluationBean;
import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.bw.movie.model.bean.ErarBean;
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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 作者： 姓名
 * 日期： 2019/9/29 14:03
 */
public interface Api {
    //发送验证码
    @FormUrlEncoded
    @POST("user/v2/sendOutEmailCode")
    Observable<SendEmail> sendEmail(@Field("email") String email);
    //注册
    @FormUrlEncoded
    @POST("user/v2/register")
    Observable<ReginBean> reginBean(@Field("nickName") String nickName, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);
    //登录
    @FormUrlEncoded
    @POST("user/v2/login")
    Observable<LoginBean> loginBean(@Field("email") String email, @Field("pwd") String pwd);
    //首页banner
    @GET("tool/v2/banner")
    Observable<BanBean> banBean();
    //首页 正在上映
    @GET("movie/v2/findReleaseMovieList")
    Observable<ShangMovieBean> shangBean(@Query("page") int page, @Query("count") int count);
    //首页 即将上映
    @GET("movie/v2/findComingSoonMovieList")
    Observable<JiMoviewBean> jiBean(@Query("page") int page, @Query("count") int count, @Header("userId") int userId,@Header("sessionId") String sessionId);
    //首页 热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<ReMovieBean> reBean(@Query("page") int page, @Query("count") int count);
    //推荐 影院
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendedCinemaBean> recommendedBean(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("page") int page, @Query("count") int count);
    //附近 影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearCinema> nearBean(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("page") int page, @Query("count") int count,@Query("longitude") String longitude,@Query("latitude")String latitude);
    //关注 电影
    @GET("movie/v1/verify/followMovie")
    Observable<AttentionBean> attentionBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("movieId") int movieId);
    //关注 影院
    @GET("cinema/v1/verify/followCinema")
    Observable<AttentActionBean> attentActionBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("cinemaId") int cinemaId);
    //查询关注 电影
    @GET("user/v2/verify/findUserFollowMovieList")
    Observable<AttentMovieBean> attentMoviewBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("page") int pagt,@Query("count") int count);
    //查询关注 影院
    @GET("user/v2/verify/findUserFollowCinemaList")
    Observable<AttentCinemaBean> attentCinemaBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("page") int pagt,@Query("count") int count);
    //查询预约 电影
    @GET("user/v2/verify/findUserReserve")
    Observable<BookMovieBean> bookMovieBean(@Header("userId") String userId,@Header("sessionId") String sessionId);
    //查询 电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<QueryMovieBean> queryMovieBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("movieId") int movieId);
    //根据用户ID 查询用户信息
    @GET("user/v1/verify/getUserInfoByUserId")
    Observable<IdNameBean> idNameBean(@Header("userId") String userId,@Header("sessionId") String sessionId);
    //查询区域列表
    @GET("tool/v2/findRegionList")
    Observable<QueryRegionBean> queryRegionBean();
    //根据区域查询影院
    @GET("cinema/v2/findCinemaByRegion")
    Observable<RegionCinemaBean> regionCinemaBean(@Query("regionId") int regionId);
    //上传头像
    @FormUrlEncoded
    @POST("user/v1/verify/uploadHeadPic")
    Observable<FileImageBean> fileImageBean(@Header("userId") String userId, @Header("sessionId") String sessionId, @Part List<MultipartBody.Part>map);
    //微信登录
    @FormUrlEncoded
    @POST("user/v1/weChatBindingLogin")
    Observable<WxBean> wxBean(@Field("code") String code);
    //搜索电影
    @GET("movie/v2/findMovieByKeyword")
    Observable<SearchBean> searchBean(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);
    //添加用户对影片的评论
    @FormUrlEncoded
    @POST("movie/v1/verify/movieComment")
    Observable<AddCommentBean> addCommentBean(@Header("userId") String userId,@Header("sessionId") String sessionId,
                                              @Field("movieId") int movieId,@Field("commentContent") String commentContent,@Field("score") double score);
    //根据电影的id查询电影评论

    @GET("movie/v2/findAllMovieComment")
    Observable<QueryCommentBean> queryCommentBean(@Header("userId") String userId,@Header("sessionId") String sessionId,
                                                  @Query("movieId") int movieId,@Query("page") int pagt,@Query("count") int count);
    //根据区域进行查询
    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<RangeBean> range(@Query("movieId") int movieId,
                                @Query("regionId") int regionId,
                                @Query("page") int page,
                                @Query("count") int count);
    //根据区域查询影院
    @GET("cinema/v2/findCinemaByRegion")
    Observable<ErarBean> erar(@Query("regionId") int regionIds);
    //查询一周排期的时间
    @GET("tool/v2/findDateList")
    Observable<SchduBean> schdu();

    //根据电影id，时间 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByDate")
    Observable<MovieIdCinemaBean> movieIdCinemaBean(@Query("movieId") int movieId,@Query("date") String date,@Query("page") int page,@Query("count") int count );

    //查询区域列表
    @GET
    Observable<CinemaBean> cinemaBean(@Url String url);

    //根据电影价格查询播放影院信息
    @GET("movie/v2/findCinemasInfoByPrice")
    Observable<PriceMovieQueryCinemaBean> priceCinemaBean(@Query("movieId") int movieId,@Query("page") int page,@Query("count") int count);

    //根据电影ID和影院ID查询电影排期列表
    @GET("movie/v2/findMovieSchedule")
    Observable<MovieCinemaDateBean> movieCinemaDateBean(@Query("movieId") int movieId,@Query("cinemaId") int cinemaId);

    //根据影厅id 查询座位信息
    @GET("movie/v2/findSeatInfo")
    Observable<FileSeatBean> fileSeatBean(@Query("hallId") int hallId);

    //版本更新
    @GET("tool/v1/findNewVersion")
    Observable<VersionUpdateBean> versionUpdateBean(@Header("userId") String userId,@Header("sessionId") String sessionId,@Header("ak") int ak);

    //根据电影名称查询电影院
    @GET("cinema/v1/findAllCinemas")
    Observable<SearchCinemaBean> searchCinamaBean(@Query("page") int page,@Query("count") int count,@Query("cinemaName") String cinemaName);

    //查询电影信息明细
    @GET("cinema/v1/findCinemaInfo")
    Observable<QueryCinemaDetails> queryCinemaDetails(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("cinemaId") int cinemaId);

    //查询影院评论
    @GET("cinema/v1/findAllCinemaComment")
    Observable<CinemaEvaluationBean> cinemaEvaluetion(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("cinemaId") int cinemaId,@Query("page") int page,@Query("count") int count);

    //查询影院下的排期
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<CinemaSchedulingBean> cinemaScheduling(@Query("cinemaId") int cinemaId,@Query("page") int page,@Query("count") int count);
}
