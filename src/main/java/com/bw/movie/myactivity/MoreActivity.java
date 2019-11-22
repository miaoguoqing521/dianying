package com.bw.movie.myactivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.myactivity.morefragment.BeFragment;
import com.bw.movie.myactivity.morefragment.HeatFragment;
import com.bw.movie.myactivity.morefragment.HotFragment;
import com.bw.movie.myactivity.morefragment.MoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity {

    @BindView(R.id.more_search)
    ImageView moreSearch;
    @BindView(R.id.more_tab)
    TabLayout moreTab;
    @BindView(R.id.more_view)
    ViewPager moreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        list.add("正在热映");
        list.add("即将上映");
        list.add("热门电影");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HeatFragment());
        fragmentList.add(new BeFragment());
        fragmentList.add(new HotFragment());
        MoreAdapter moreAdapter=new MoreAdapter(getSupportFragmentManager(),list,fragmentList);
        moreView.setAdapter(moreAdapter);
        moreTab.setupWithViewPager(moreView);
    }
}
