package com.bw.movie.view.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.AttentionBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.presenter.QueryMoviePresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.detailsfrag.FilmReviewFrag;
import com.bw.movie.view.movies.detailsfrag.IntroductionFrag;
import com.bw.movie.view.movies.detailsfrag.StillsFrag;
import com.bw.movie.view.movies.detailsfrag.TrailerFrag;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.OnDoubleClickListener;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： 姓名
 * 日期： 2019/10/17 11:42
 */
public class DetailsMovie extends AppCompatActivity implements IMainView.IMovieView {
    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.details_img)
    ImageView detailsImg;
    @BindView(R.id.details_mark)
    TextView detailsMark;
    @BindView(R.id.details_critic)
    TextView detailsCritic;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_type)
    TextView detailsType;
    @BindView(R.id.details_long)
    TextView detailsLong;
    @BindView(R.id.details_btn_img)
    ImageView detailsBtnImg;
    @BindView(R.id.details_time)
    TextView detailsTime;
    @BindView(R.id.details_add)
    TextView detailsAdd;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.details_tab)
    TabLayout detailsTab;
    @BindView(R.id.details_view)
    ViewPager detailsView;
    @BindView(R.id.btn_reviews)
    Button btnReviews;
    @BindView(R.id.btn_seat)
    Button btnSeat;
    private Boolean ischeck=false;
    private QueryMoviePresenter queryMoviePresenter;
    private int movieId;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_movie);
        ButterKnife.bind(this);
        SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        SharedPreferences sp = getSharedPreferences("isP", MODE_PRIVATE);
        movieId = sp.getInt("movieId", 0);
        final String userId = isLogin.getString("userId", "");
        final String sessionId = isLogin.getString("sessionId", "");
        queryMoviePresenter = new QueryMoviePresenter();
        queryMoviePresenter.attach(this);
        queryMoviePresenter.getDate(userId,sessionId, movieId);



        btnSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsMovie.this,BuyMovieActivity.class);
                startActivity(intent);
            }
        });
        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(DetailsMovie.this,WriteCommentActivity.class);
                startActivity(intents);
            }
        });

        detailsBack.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                finish();
            }
        });
        detailsBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ischeck){
                    ischeck=false;
                    if (ischeck){
                        detailsBtnImg.setImageResource(R.drawable.emptyheart);
                    }else {
                        detailsBtnImg.setImageResource(R.drawable.xinxin);
                        queryMoviePresenter.getDetailDate(userId,sessionId, movieId);
                    }
                }else {
                    ischeck=true;
                    detailsBtnImg.setImageResource(R.drawable.emptyheart);
                    Toast.makeText(DetailsMovie.this, "取消关注", Toast.LENGTH_SHORT).show();
                }
            }
        });

        List<String> list=new ArrayList<>();
        list.add("介绍");
        list.add("预告");
        list.add("剧照");
        list.add("影评");
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new IntroductionFrag());
        fragmentList.add(new TrailerFrag());
        fragmentList.add(new StillsFrag());
        fragmentList.add(new FilmReviewFrag());
        DetailsAdapter detailsAdapter = new DetailsAdapter(getSupportFragmentManager(),list,fragmentList);
        detailsView.setAdapter(detailsAdapter);
        detailsTab.setupWithViewPager(detailsView);
    }

    @Override
    public void movie(QueryMovieBean queryMovieBean) {
        QueryMovieBean.ResultBean result = queryMovieBean.result;

        EventBus.getDefault().postSticky(result);

        detailsMark.setText("评分:  "+result.score+"分");
        SharedPreferences isJ = getSharedPreferences("isJ", MODE_PRIVATE);
        SharedPreferences.Editor edits = isJ.edit();
        edits.putString("name", result.name);
        edits.commit();
        detailsName.setText(result.name);
        detailsCritic.setText("评论:  "+result.commentNum+"万条");
        detailsType.setText(result.movieType);
        String s = String.valueOf(result.releaseTime);
        String format = DateFormatUtils.format(s);
        detailsTime.setText(format);
        detailsAdd.setText(result.placeOrigin);
        detailsLong.setText(result.duration);
        Glide.with(this).load(result.imageUrl).into(detailsImg);
    }

    @Override
    public void attent(AttentionBean attentionBean) {

    }

    private static class DateFormatUtils {
        public static String format(String date){
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Long aLong = new Long(date);
            return dateFormat.format(aLong);
        }
    }
}
