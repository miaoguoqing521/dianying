package com.bw.movie.view.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.bw.movie.model.bean.FileSeatBean;
import com.bw.movie.model.bean.MovieCinemaDateBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.presenter.DatePresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.buymovie.MovieSeatAdapter;
import com.bw.movie.view.movies.buymovie.Room_Recycler_Adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RoomActivity extends AppCompatActivity implements IMainView.DateView {


    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.room_VideoPlayer)
    JCVideoPlayer roomVideoPlayer;
    @BindView(R.id.room_movieSeat)
    RecyclerView roomMovieSeat;
    @BindView(R.id.real)
    RelativeLayout real;
    @BindView(R.id.room_time)
    TextView roomTime;
    @BindView(R.id.room_recycler)
    RecyclerView roomRecycler;
    @BindView(R.id.btn_purchaseOrder)
    Button btnPurchaseOrder;
    @BindView(R.id.room_btn)
    Button roomBtn;
    @BindView(R.id.room_back)
    ImageView roomBack;
    @BindView(R.id.room_name)
    TextView roomName;
    private DatePresenter datePresenter;
    private String string;
    private long sum;
    private double fare;
    private double zf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);

        SharedPreferences isP = getSharedPreferences("isP", MODE_PRIVATE);
        int movieId = isP.getInt("movieId", 0);
        Intent intent = getIntent();
        int cinemaId = intent.getIntExtra("cinemaId", 0);
        datePresenter = new DatePresenter();
        datePresenter.attach(this);
        datePresenter.getDate(movieId, cinemaId);
        SharedPreferences isJ = getSharedPreferences("isJ", MODE_PRIVATE);
        SharedPreferences isV = getSharedPreferences("isV", MODE_PRIVATE);
        String videos = isV.getString("videos", "");
        String name = isJ.getString("name", "");
        roomName.setText(name);
        roomVideoPlayer.setUp(videos,null);
        roomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getSharedPreferences("isLogins", MODE_PRIVATE);
//                final String userId = sp.getString("userId", "");
//                final String sessionId = sp.getString("sessionId", "");
//                SharedPreferences pi = getSharedPreferences("hallId", MODE_PRIVATE);
//                int id = pi.getInt("id", 0);
//                SharedPreferences isZz = getSharedPreferences("isZz", MODE_PRIVATE);
//                String seat = isZz.getString("seat", "");
//                String sign = MD5(userId + id + "movie");
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void EventBusName(QueryMovieBean.ResultBean data) {
        List<QueryMovieBean.ResultBean.ShortFilmListBean> shortFilmList = data.shortFilmList;
        roomVideoPlayer.setUp(shortFilmList.get(0).videoUrl, null);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void EventBusNames(CinemaSchedulingBean.ResultBean data) {
        roomVideoPlayer.setUp(data.trailerUrl, null);
    }

    @Override
    public void cinemaDate(MovieCinemaDateBean movieCinemaDateBean) {
        final List<MovieCinemaDateBean.ResultBean> result = movieCinemaDateBean.result;
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        roomRecycler.setLayoutManager(manager);

        Room_Recycler_Adapter adapter = new Room_Recycler_Adapter(R.layout.recycler_room, result);
        roomRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedPreferences sp = getSharedPreferences("hallId", MODE_PRIVATE);
                int hallId = sp.getInt("hallId", 0);
                datePresenter.getseatdata(hallId);
                double fare = result.get(position).fare;
            }
        });
    }

    @Override
    public void fileSeat(FileSeatBean fileSeatBean) {
        final List<FileSeatBean.ResultBean> result = fileSeatBean.result;
        LinearLayoutManager manager = new GridLayoutManager(this, 8);
        roomMovieSeat.setLayoutManager(manager);
        MovieSeatAdapter seatAdapter = new MovieSeatAdapter(R.layout.movie_seat_layout, result);
        roomMovieSeat.setAdapter(seatAdapter);
        seatAdapter.setCallBack(new MovieSeatAdapter.CallBack() {
            @Override
            public void getBack(String s) {
                Toast.makeText(RoomActivity.this, s, Toast.LENGTH_SHORT).show();
                string = s;
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getStatus() == 3) {
                        sum++;
                    }
                }
                //设置价格
                if (sum != 0) {
                    zf = sum * fare;
                    btnPurchaseOrder.setText("￥:" + sum * fare);
                    btnPurchaseOrder.setVisibility(View.VISIBLE);
                    roomBtn.setVisibility(View.INVISIBLE);
                    sum = 0;
                } else if (sum == 0) {
                    roomBtn.setVisibility(View.VISIBLE);
                    btnPurchaseOrder.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    //MD5加密
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
