package com.bw.movie.view.cinema.scheduling;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.model.bean.SchduBean;
import com.bw.movie.presenter.DatePresenter;
import com.bw.movie.presenter.OneDatePresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchedulingActivity extends AppCompatActivity implements IMainView.OneDateView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.tab_lay)
    TabLayout tabLay;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int cinemaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        ButterKnife.bind(this);
        OneDatePresenter oneDatePresenter = new OneDatePresenter();
        oneDatePresenter.attach(this);
        oneDatePresenter.getDate();
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences isL = getSharedPreferences("isL", MODE_PRIVATE);
        cinemaId = isL.getInt("cinemaId", 0);
    }

    @Override
    public void schdu(SchduBean schduBean) {
        final List<String> result = schduBean.result;
        for (int i = 0; i < result.size(); i++) {
            final String s = result.get(i);
            TabLayout.Tab tab = tabLay.newTab();
            if (tab != null) {
                tab.setText(s);
                tabLay.addTab(tab);
            }
        }
        ScheduleTabAdapter scheduleTabAdapter = new ScheduleTabAdapter(getSupportFragmentManager(), result,cinemaId);
        tabLay.setupWithViewPager(viewPager);
        viewPager.setAdapter(scheduleTabAdapter);
    }
}
