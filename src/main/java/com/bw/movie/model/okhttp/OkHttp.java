package com.bw.movie.model.okhttp;

import android.util.Log;
import android.widget.Toast;

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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： 姓名
 * 日期： 2019/9/28 15:07
 */
public class OkHttp {
    private static OkHttp okHttp=new OkHttp();
    private final Api api;

    public static OkHttp getInstance(){
        return okHttp;
    }
    public OkHttp(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.17.8.100/movieApi/")
                .build();
        api = retrofit.create(Api.class);
    }
    //登录
    public void doPost(String email,String pwd, final CallBack back){
        Observable<LoginBean> loginBean = okHttp.api.loginBean(email, pwd);
        loginBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        back.ok(loginBean);
                    }
                });
    }
    public interface CallBack{
        void ok(LoginBean loginBean);
    }
    //Banner轮播图
    public void doBan(final BackBan backBan){
        Observable<BanBean> banBean = okHttp.api.banBean();
        banBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BanBean banBean) {
                        backBan.ban(banBean);
                    }
                });
    }
    public interface BackBan{
        void ban(BanBean banBean);
    }
    //正在热映
    public void doShang(final BackShang backShang){
        Observable<ShangMovieBean> shangBean = okHttp.api.shangBean(1, 5);
        shangBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShangMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShangMovieBean shangMovieBean) {
                        backShang.shang(shangMovieBean);
                    }
                });
    }
    public interface BackShang{
        void shang(ShangMovieBean shangMovieBean);
    }
    //即将上映
    public void doJi(final BackJi backJi){
        Observable<JiMoviewBean> jiBean = okHttp.api.jiBean(1, 3,13565,"157068736071213565");
        jiBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<JiMoviewBean>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(JiMoviewBean jiMoviewBean) {
                        backJi.Ji(jiMoviewBean);
                   }
               });
    }
    public interface BackJi{
        void Ji(JiMoviewBean jiMoviewBean);
    }
    //热门电影
    public void doRe(final BackRe backRe){
        Observable<ReMovieBean> reBean = okHttp.api.reBean(1, 5);
        reBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ReMovieBean>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(ReMovieBean reMovieBean) {
                        backRe.Re(reMovieBean);
                   }
               });
    }
    public interface BackRe{
        void Re(ReMovieBean reMovieBean);
    }
    //推荐 影院
    public void doRecommend(final BackRecommend backRecommend){
        Observable<RecommendedCinemaBean> recommendedBean = okHttp.api.recommendedBean(13565, "157068736071213565", 1, 10);
        recommendedBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendedCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommendedCinemaBean recommendedCinemaBean) {
                        backRecommend.Recommedd(recommendedCinemaBean);
                    }
                });
    }
    public interface BackRecommend{
        void Recommedd(RecommendedCinemaBean recommendedCinemaBean);
    }
    //附近 影院
    public void doNear(final BackNear backNear,int page){
        Observable<NearCinema> nearBean = okHttp.api.nearBean(13565, "157068736071213565", page, 10, "116.30551391385724", "40.04571807462411");
        nearBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearCinema>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NearCinema nearCinema) {
                        backNear.Near(nearCinema);
                    }
                });
    }
    public interface BackNear{
        void Near(NearCinema nearCinema);
    }
    //发送验证码
    public void doSend(final BackSend backSend, String email){
        Observable<SendEmail> sendEmail = okHttp.api.sendEmail(email);
        sendEmail.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendEmail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SendEmail sendEmail) {
                        backSend.send(sendEmail);
                    }
                });
    }
    public interface BackSend{
        void send(SendEmail sendEmail);
    }
    //注册
    public void doRegin(final BackRegin backRegin, String nickName, String pwd, String email, String code){
        Observable<ReginBean> reginBean = okHttp.api.reginBean(nickName, pwd, email, code);
        reginBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReginBean reginBean) {
                        backRegin.regin(reginBean);
                    }
                });
    }
    public interface BackRegin{
        void regin(ReginBean reginBean);
    }
    //关注电影
    public void doAttention(final CallAttention attentions, String userId, String sessionId,int movieId){
        Observable<AttentionBean> attentionBean = okHttp.api.attentionBean(userId,sessionId,movieId);
        attentionBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttentionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AttentionBean attentionBean) {
                        attentions.attention(attentionBean);
                    }
                });
    }
    public interface CallAttention{
        void attention(AttentionBean attentionBean);
    }
    //关注影院
    public void doAttentAction(final CallAttentAction callAttentAction,String userId,String sessionId,int cinemaId){
        Observable<AttentActionBean> bean = okHttp.api.attentActionBean(userId, sessionId, cinemaId);
        bean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttentActionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AttentActionBean attentActionBean) {
                        callAttentAction.attentaction(attentActionBean);
                    }
                });
    }
    public interface CallAttentAction{
        void attentaction(AttentActionBean attentActionBean);
    }
    //查询关注电影
    public void doAttentMovie(final CallAttentMovie callAttentMovie,String userId,String sessionId){
        Observable<AttentMovieBean> moviewBean = okHttp.api.attentMoviewBean(userId, sessionId, 1, 5);
        moviewBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttentMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AttentMovieBean attentMovieBean) {
                        callAttentMovie.attentMovie(attentMovieBean);
                    }
                });
    }
    public interface CallAttentMovie{
        void attentMovie(AttentMovieBean attentMovieBean);
    }
    //查询关注影院
    public void doAttentCinema(final CallAttentCinema callAttentCinema,String userId,String sessionId){
        final Observable<AttentCinemaBean> cinemaBean = okHttp.api.attentCinemaBean(userId, sessionId, 1, 5);
        cinemaBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttentCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AttentCinemaBean attentCinemaBean) {
                        callAttentCinema.attentCinema(attentCinemaBean);
                    }
                });
    }
    public interface CallAttentCinema{
        void attentCinema(AttentCinemaBean attentCinemaBean);
    }
    //查询预约 电影
    public void doBookMovie(final CallBookMovie callBookMovie, String userId, String sessionId){
        Observable<BookMovieBean> movieBean = okHttp.api.bookMovieBean(userId, sessionId);
        movieBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookMovieBean bookMovieBean) {
                        callBookMovie.bookmovie(bookMovieBean);
                    }
                });
    }
    public interface CallBookMovie{
        void bookmovie(BookMovieBean bookMovieBean);
    }
    //查询电影详情
    public void doMovie(final CallMovie callMovie, String userId, String sessionId,int movieId){
        Observable<QueryMovieBean> movieBean = okHttp.api.queryMovieBean(userId, sessionId, movieId);
        movieBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryMovieBean queryMovieBean) {
                            callMovie.movie(queryMovieBean);
                    }
                });
    }
    public interface CallMovie{
        void movie(QueryMovieBean queryMovieBean);
    }
    //根据用户ID 查询用户信息
    public void doIdName(final CallIdName callIdName, String userId, String sessionId){
        Observable<IdNameBean> idNameBean = okHttp.api.idNameBean(userId, sessionId);
        idNameBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IdNameBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IdNameBean idNameBean) {
                        callIdName.idname(idNameBean);
                    }
                });
    }
    public interface CallIdName{
        void idname(IdNameBean idNameBean);
    }
    //查询区域列表
    public void doRegion(final CallRegion callRegion){
        Observable<QueryRegionBean> regionBean = okHttp.api.queryRegionBean();
        regionBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryRegionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryRegionBean queryRegionBean) {
                        callRegion.queryregion(queryRegionBean);
                    }
                });
    }
    public interface CallRegion{
        void queryregion(QueryRegionBean regionBean);
    }
    //根据区域查询影院
    public void doRegionCinema(final CallRegionCinema callRegionCinema, int regionId){
        Observable<RegionCinemaBean> cinemaBean = okHttp.api.regionCinemaBean(regionId);
        cinemaBean.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegionCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegionCinemaBean regionCinemaBean) {
                            callRegionCinema.regioncinema(regionCinemaBean);
                    }
                });
    }
    public interface CallRegionCinema{
        void regioncinema(RegionCinemaBean regionCinemaBean);
    }
    //搜索电影
    public void doSearch(final CallSearch callSearch, String keyword){
        Observable<SearchBean> searchBean = okHttp.api.searchBean(keyword, 1, 5);
        searchBean.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        callSearch.search(searchBean);
                    }
                });
    }
    public interface CallSearch{
        void search(SearchBean searchBean);
    }
    //添加用户对电影的评论
    public void doCallAddComment(final CallAdd callAdd, String userId, String sessionId, int movieId, String commentContent, double score){
        Observable<AddCommentBean> addCommentBeanObservable = okHttp.api.addCommentBean(userId, sessionId, movieId, commentContent, score);
        addCommentBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCommentBean addCommentBean) {
                        callAdd.addcomment(addCommentBean);
                    }
                });
    }
    public interface CallAdd{
        void addcomment(AddCommentBean addCommentBean);
    }
    //查询电影的评论
    public void doQueryComment(final CallQueryComment queryComment, String userId, String sessionId, int movieId){
        Observable<QueryCommentBean> queryCommentBeanObservable = okHttp.api.queryCommentBean(userId, sessionId, movieId, 1, 5);
        queryCommentBeanObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<QueryCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryCommentBean queryCommentBean) {
                        queryComment.queryComment(queryCommentBean);
                    }
                });
    }
    public interface CallQueryComment{
        void queryComment(QueryCommentBean queryCommentBean);
    }
    //根据区域进行查询
    public void doRange(final CallRange range, int movieId, int regionId){
        Observable<RangeBean> rangeBeanObservable = okHttp.api.range(movieId, regionId, 1, 5);
        rangeBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RangeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RangeBean rangeBean) {
                        range.range(rangeBean);
                    }
                });
    }
    public interface CallRange{
        void range(RangeBean rangeBean);
    }
    //查询区域列表
    public void doCinema(final CallcinemaBean callcinemaBean){
        okHttp.api.cinemaBean("http://172.17.8.100/movieApi/tool/v2/findRegionList")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaBean cinemaBean) {
                        callcinemaBean.cinemas(cinemaBean);
                        Log.e("qqq",""+cinemaBean);
                       // Toast.makeText(App.context, ""+cinemaBean.result.get(0), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public interface CallcinemaBean{
        void cinemas(CinemaBean cinemaBean);
    }

    //查询一周排期的时间
    public void doSchdu(final Callschdu callschdu){
        Observable<SchduBean> schdu = okHttp.api.schdu();
        schdu.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SchduBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("xxx", "onError: "+e);
                    }

                    @Override
                    public void onNext(SchduBean schduBean) {
                        callschdu.schdu(schduBean);
                    }
                });
    }
    public interface Callschdu{
        void schdu(SchduBean schduBean);
    }
    //根据电影id，时间 查询播放影院信息
    public void doMovieIdCinema(final CallmovieIdCinema callmovieIdCinema,int movieId,String date){
        Observable<MovieIdCinemaBean> movieIdCinemaBean = okHttp.api.movieIdCinemaBean(movieId, date, 1, 7);
        movieIdCinemaBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieIdCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieIdCinemaBean movieIdCinemaBean) {
                        callmovieIdCinema.movieIdCinema(movieIdCinemaBean);
                    }
                });
    }
    public interface CallmovieIdCinema{
        void movieIdCinema(MovieIdCinemaBean movieIdCinemaBean);
    }
    //根据电影价格查询播放影院信息
    public void doPriceCinema(final CallpriceCinemaBean callpriceCinemaBean,int movieId){
        Observable<PriceMovieQueryCinemaBean> priceMovieQueryCinemaBeanObservable = okHttp.api.priceCinemaBean(movieId, 1, 7);
        priceMovieQueryCinemaBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PriceMovieQueryCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PriceMovieQueryCinemaBean priceMovieQueryCinemaBean) {
                        callpriceCinemaBean.priceCinemaBean(priceMovieQueryCinemaBean);
                    }
                });
    }
    public interface CallpriceCinemaBean{
        void priceCinemaBean(PriceMovieQueryCinemaBean priceMovieQueryCinemaBean);
    }
    //上传头像
    public void doImage(final CallImgge callImgge, String userId, String sessionId, List<MultipartBody.Part> map){
        Observable<FileImageBean> imageBean = okHttp.api.fileImageBean(userId, sessionId, map);
        imageBean.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<FileImageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FileImageBean fileImageBean) {
                        callImgge.imgge(fileImageBean);
                    }
                });
    }
    public interface CallImgge{
        void imgge(FileImageBean fileImageBean);
    }

    //根据电影ID和影院ID查询电影排期列表
    public void doCinemaDate(final CallCinemaMovieDate callCinemaMovieDate, int movieId, int cinemaId){
        Observable<MovieCinemaDateBean> movieCinemaDateBeanObservable = okHttp.api.movieCinemaDateBean(movieId, cinemaId);
        movieCinemaDateBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCinemaDateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieCinemaDateBean movieCinemaDateBean) {
                            callCinemaMovieDate.cinemaDate(movieCinemaDateBean);
                    }
                });
    }
    public interface CallCinemaMovieDate{
        void cinemaDate(MovieCinemaDateBean movieCinemaDateBean);
    }

    //根据影厅id 查询座位信息
    public void doFileSeat(final Callseat callseat, int hallId){
        Observable<FileSeatBean> fileSeatBean = okHttp.api.fileSeatBean(hallId);
        fileSeatBean.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<FileSeatBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FileSeatBean fileSeatBean) {
                            callseat.fileseat(fileSeatBean);
                    }
                });
    }
    public interface Callseat{
        void fileseat(FileSeatBean fileSeatBean);
    }
    //更新版本
    public void doVersionUpdate(final CallUpdate callUpdate, String userId, String sessionId,int ak){
        Observable<VersionUpdateBean> versionUpdateBeanObservable = okHttp.api.versionUpdateBean(userId, sessionId, ak);
        versionUpdateBeanObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<VersionUpdateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VersionUpdateBean versionUpdateBean) {
                        callUpdate.versionupdate(versionUpdateBean);
                    }
                });
    }
    public interface CallUpdate{
        void versionupdate(VersionUpdateBean versionUpdateBean);
    }

    //根据电影院名查询影院
    public void doSearchCinema(final CallSearchCinema searchCinema, String cinemaName){
        Observable<SearchCinemaBean> searchCinamaBean = okHttp.api.searchCinamaBean(1, 5, cinemaName);
        searchCinamaBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchCinemaBean searchCinemaBean) {
                        searchCinema.searchcinema(searchCinemaBean);
                    }
                });
    }
    public interface CallSearchCinema{
        void searchcinema(SearchCinemaBean searchCinemaBean);
    }
    //查询影院详情
    public void doDetails(final CallDetails callDetails, String userId, String sessionId, int cinemaId){
        Observable<QueryCinemaDetails> cinemaDetails = okHttp.api.queryCinemaDetails(userId, sessionId, cinemaId);
        cinemaDetails.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<QueryCinemaDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryCinemaDetails queryCinemaDetails) {
                        callDetails.details(queryCinemaDetails);
                    }
                });
    }
    public interface CallDetails{
        void details(QueryCinemaDetails queryCinemaDetails);
    }
    //查询影院评价
    public void doEvaluetion(final CallEvalue callEvalue, String userId, String sessionId, int cinemaId){
        Observable<CinemaEvaluationBean> cinemaEvaluationBeanObservable = okHttp.api.cinemaEvaluetion(userId, sessionId, cinemaId, 1, 5);
        cinemaEvaluationBeanObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<CinemaEvaluationBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaEvaluationBean cinemaEvaluationBean) {
                        callEvalue.evaluetion(cinemaEvaluationBean);
                    }
                });
    }
    public interface CallEvalue{
        void evaluetion(CinemaEvaluationBean cinemaEvaluationBean);
    }
    //查询影院下的排期
    public void doScheduling(final CallScheduling callScheduling, int cinemaId){
        Observable<CinemaSchedulingBean> cinemaScheduling = okHttp.api.cinemaScheduling(cinemaId, 1, 5);
        cinemaScheduling.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaSchedulingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaSchedulingBean cinemaSchedulingBean) {
                        callScheduling.scheduling(cinemaSchedulingBean);
                    }
                });
    }
    public interface CallScheduling{
        void scheduling(CinemaSchedulingBean cinemaSchedulingBean);
    }
}
