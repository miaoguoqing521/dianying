package com.bw.movie.view.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaBean;
import com.bw.movie.model.bean.MovieIdCinemaBean;
import com.bw.movie.model.bean.PriceMovieQueryCinemaBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.model.bean.RangeBean;
import com.bw.movie.model.bean.SchduBean;
import com.bw.movie.presenter.MovieIdCinemaPresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.buymovie.Buy_Area_Adapter;
import com.bw.movie.view.movies.buymovie.Buy_Price_Adapter;
import com.bw.movie.view.movies.buymovie.Buy_Range_Adapter;
import com.bw.movie.view.movies.buymovie.Buy_Zhu_Adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stx.xhb.xbanner.OnDoubleClickListener;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class BuyMovieActivity extends AppCompatActivity implements IMainView.IRangeView {

    @BindView(R.id.buymovie_back)
    ImageView buymovieBack;
    @BindView(R.id.buymovie_img)
    JCVideoPlayer buymovieImg;
    @BindView(R.id.buymovie_name)
    TextView buymovieName;
    @BindView(R.id.buy_time)
    TextView buyTime;
    @BindView(R.id.buy_mark)
    TextView buyMark;
    @BindView(R.id.buy_dirtor)
    TextView buyDirtor;
    @BindView(R.id.buy_Range)
    TextView buyRange;
    @BindView(R.id.buy_schdu)
    TextView buySchdu;
    @BindView(R.id.buy_Sizer)
    TextView buySizer;
    @BindView(R.id.buy_recycler)
    RecyclerView buyRecycler;
    private MovieIdCinemaPresenter movieIdCinemaPresenter;
    private int movieId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_movie);
        movieIdCinemaPresenter = new MovieIdCinemaPresenter();

        movieIdCinemaPresenter.attach(this);
        ButterKnife.bind(this);


        //查询区域
        buyRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(BuyMovieActivity.this, "444", Toast.LENGTH_SHORT).show();
                movieIdCinemaPresenter.getDate();
            }
        });
        buymovieBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //筛选
        buySizer.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    SharedPreferences isP = getSharedPreferences("isP", MODE_PRIVATE);
                    movieId = isP.getInt("movieId", 0);
                     Log.i("movieIdmovieId", "onClick: "+movieId);
                    movieIdCinemaPresenter.getPrice(movieId);
                }
        });



        //排期
        buySchdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieIdCinemaPresenter.getSchu();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void EventBusName(QueryMovieBean.ResultBean  data){
        Glide.with(this).load(data.imageUrl).into(buymovieImg.ivThumb);
        buymovieImg.setUp(data.shortFilmList.get(0).videoUrl,null);
        buymovieName.setText(data.name);
        buyTime.setText(data.duration);
        buyMark.setText(data.score + "分");

        List<QueryMovieBean.ResultBean.MovieDirectorBean> director = data.movieDirector;
        for (int i = 0; i < director.size(); i++) {
            buyDirtor.setText(director.get(i).name);
        }

    }


    @Override
    public void cinema(CinemaBean cinemaBean) {
        List<CinemaBean.ResultBean> result = cinemaBean.result;

        View regionView = LayoutInflater.from(BuyMovieActivity.this).inflate(R.layout.buy_movie_region_item_layout, null);
        final PopupWindow mPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setContentView(regionView);
        mPopupWindow.showAsDropDown(buyRange, -20, 0);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView rv_buy_movie_region = regionView.findViewById(R.id.rv_buy_movie_region);
        rv_buy_movie_region.setLayoutManager(manager);
        final Buy_Area_Adapter areaAdapter = new Buy_Area_Adapter(R.layout.buy_area_pop, result);
        rv_buy_movie_region.setAdapter(areaAdapter);

        areaAdapter.setOnClickRegion(new Buy_Area_Adapter.OnClickRegion() {
            @Override
            public void region(int regionId, String regionName) {
                buyRange.setText(regionName);
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getrangedata(movieId, regionId);
            }
        });
    }

    @Override
    public void range(RangeBean rangeBean) {
        final List<RangeBean.ResultBean> result = rangeBean.result;
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        buyRecycler.setLayoutManager(manager);
        Buy_Range_Adapter rangeAdapter = new Buy_Range_Adapter(R.layout.recycler_range, result);
        buyRecycler.setAdapter(rangeAdapter);

        rangeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = result.get(position).cinemaId;
                SharedPreferences isP = getSharedPreferences("isP", MODE_PRIVATE);
                movieId = isP.getInt("movieId", 0);
                Intent proom = new Intent(BuyMovieActivity.this, RoomActivity.class);
                proom.putExtra("cinemaId", cinemaId);
                startActivity(proom);
            }
        });
    }

    @Override
    public void schdu(SchduBean schduBean) {
        final List<String> result = schduBean.result;
        View regionView = LayoutInflater.from(BuyMovieActivity.this).inflate(R.layout.buy_schdu, null);
        final PopupWindow mPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setContentView(regionView);
        mPopupWindow.showAsDropDown(buySchdu, -20, 0);
        //周一
        final TextView schdu_Mond = regionView.findViewById(R.id.schdu_Mond);
        schdu_Mond.setText(result.get(0));
        schdu_Mond.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText("今天" + result.get(0));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Mond.getText().toString());
            }
        });
        //周二
        final TextView schdu_Tues = regionView.findViewById(R.id.schdu_Tues);
        schdu_Tues.setText(result.get(1));
        schdu_Tues.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText("明天" + result.get(1));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Tues.getText().toString());
            }
        });
        //周三
        final TextView schdu_Wednes = regionView.findViewById(R.id.schdu_Wednes);
        schdu_Wednes.setText(result.get(2));
        schdu_Wednes.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText("后天" + result.get(2));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Wednes.getText().toString());
            }
        });
        //周四
        final TextView schdu_Thurs = regionView.findViewById(R.id.schdu_Thurs);
        schdu_Thurs.setText(result.get(3));
        schdu_Thurs.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText(result.get(3));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Thurs.getText().toString());
            }
        });
        //周五
        final TextView schdu_Fri = regionView.findViewById(R.id.schdu_Fri);
        schdu_Fri.setText(result.get(4));
        schdu_Fri.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText(result.get(4));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Fri.getText().toString());
            }
        });
        //周六
        final TextView schdu_Sat = regionView.findViewById(R.id.schdu_Sat);
        schdu_Sat.setText(result.get(5));
        schdu_Sat.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText(result.get(5));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Sat.getText().toString());
            }
        });
        //周日
        final TextView schdu_Week = regionView.findViewById(R.id.schdu_Week);
        schdu_Week.setText(result.get(6));
        schdu_Week.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                buySchdu.setText(result.get(6));
                mPopupWindow.dismiss();
                movieIdCinemaPresenter.getzhudata(movieId, schdu_Week.getText().toString());
            }
        });


    }

    @Override
    public void movieIdCinema(MovieIdCinemaBean movieIdCinemaBean) {
        final List<MovieIdCinemaBean.ResultBean> result = movieIdCinemaBean.result;
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        buyRecycler.setLayoutManager(manager);
        Buy_Zhu_Adapter zhuAdapter = new Buy_Zhu_Adapter(R.layout.recycler_range, result);
        buyRecycler.setAdapter(zhuAdapter);
        zhuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = result.get(position).cinemaId;
                SharedPreferences isP = getSharedPreferences("isP", MODE_PRIVATE);
                movieId = isP.getInt("movieId", 0);
                Intent proom = new Intent(BuyMovieActivity.this, RoomActivity.class);
                proom.putExtra("cinemaId",cinemaId);
                startActivity(proom);
            }
        });
    }

    @Override
    public void priceMovie(PriceMovieQueryCinemaBean priceMovieQueryCinemaBean) {
        final List<PriceMovieQueryCinemaBean.ResultBean> result = priceMovieQueryCinemaBean.result;
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        buyRecycler.setLayoutManager(manager);
        Buy_Price_Adapter priceAdapter = new Buy_Price_Adapter(R.layout.recycler_range, result);
        buyRecycler.setAdapter(priceAdapter);

        priceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = result.get(position).cinemaId;
                SharedPreferences isP = getSharedPreferences("isP", MODE_PRIVATE);
                movieId = isP.getInt("movieId", 0);
                Intent proom = new Intent(BuyMovieActivity.this, RoomActivity.class);
                proom.putExtra("cinemaId", cinemaId);
                startActivity(proom);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
