package com.bw.movie.myactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.myactivity.attention.AttentAdapter;
import com.bw.movie.myactivity.attention.AttentionActivity;
import com.bw.movie.myactivity.attention.AttentionCinemaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 11:25
 */
public class PayActivity extends AppCompatActivity {
    @BindView(R.id.attention_back)
    ImageView attentionBack;
    @BindView(R.id.attention_tab)
    TabLayout attentionTab;
    @BindView(R.id.attention_view)
    ViewPager attentionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_action);
        ButterKnife.bind(this);
        List<String> list=new ArrayList<>();
        list.add("电影");
        list.add("影院");
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new AttentionActivity());
        fragmentList.add(new AttentionCinemaActivity());
        AttentAdapter attentAdapter=new AttentAdapter(getSupportFragmentManager(),list,fragmentList);
        attentionView.setAdapter(attentAdapter);
        attentionTab.setupWithViewPager(attentionView);

        attentionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
