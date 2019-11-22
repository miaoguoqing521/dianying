package com.bw.movie.view.cinema;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryCinemaDetails;
import com.bw.movie.model.bean.RegionCinemaBean;
import com.bw.movie.presenter.CinemaDetailsPresenter;
import com.bw.movie.view.cinema.cinemafragment.CinemaDetailsFragment;
import com.bw.movie.view.cinema.cinemafragment.CinemaEvaluationFragment;
import com.bw.movie.view.cinema.scheduling.SchedulingActivity;
import com.bw.movie.view.interfaces.IMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaDetails extends AppCompatActivity implements IMainView.DetaulsView {


    @BindView(R.id.invalid_name)
    TextView invalidName;
    @BindView(R.id.yyxq_recy)
    TextView yyxqRecy;
    @BindView(R.id.yyxq_tab)
    TabLayout yyxqTab;
    @BindView(R.id.yyxq_page)
    ViewPager yyxqPage;
    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.Schedulingbtn)
    Button schedulingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details);
        ButterKnife.bind(this);

        SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        SharedPreferences isL = getSharedPreferences("isL", MODE_PRIVATE);
        int cinemaId = isL.getInt("cinemaId", 0);
        CinemaDetailsPresenter cinemaDetailsPresenter = new CinemaDetailsPresenter();
        cinemaDetailsPresenter.attach(this);
        cinemaDetailsPresenter.getDate(userId, sessionId, cinemaId);

        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> list = new ArrayList<>();
        list.add("影院详情");
        list.add("影院评价");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new CinemaDetailsFragment());
        fragmentList.add(new CinemaEvaluationFragment());
        CinemaAdapter cinemaAdapter = new CinemaAdapter(getSupportFragmentManager(), list, fragmentList);
        yyxqPage.setAdapter(cinemaAdapter);
        yyxqTab.setupWithViewPager(yyxqPage);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void EventName(RegionCinemaBean.ResultBean regionCinemaBean) {
        invalidName.setText(regionCinemaBean.name + "");
    }

    @Override
    public void details(QueryCinemaDetails queryCinemaDetails) {
        final QueryCinemaDetails.ResultBean result = queryCinemaDetails.result;
        yyxqRecy.setText(result.label);
        schedulingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaDetails.this, SchedulingActivity.class);
                intent.putExtra("did",result.id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
