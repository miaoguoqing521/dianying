package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.fragment.HomeFrag;
import com.bw.movie.view.fragment.MyFrag;
import com.bw.movie.view.fragment.OtherFrag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.radiobtn1)
    RadioButton radiobtn1;
    @BindView(R.id.radiobtn2)
    RadioButton radiobtn2;
    @BindView(R.id.radiobtn3)
    RadioButton radiobtn3;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.fl_home)
    FrameLayout flHome;
    private FragmentManager manager;
    private MyFrag myFrag;
    private HomeFrag homeFrag;
    private OtherFrag otherFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        myFrag = new MyFrag();
        homeFrag = new HomeFrag();
        otherFrag = new OtherFrag();

        manager = getSupportFragmentManager();

        manager.beginTransaction()
                .add(R.id.fl_home, myFrag)
                .add(R.id.fl_home, homeFrag)
                .add(R.id.fl_home, otherFrag)
                .show(myFrag)
                .hide(homeFrag)
                .hide(otherFrag)
                .commit();
    }

    @OnClick({R.id.radiobtn1, R.id.radiobtn2, R.id.radiobtn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radiobtn1:
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                radiobtn1.setVisibility(View.INVISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                manager.beginTransaction()
                        .show(myFrag)
                        .hide(homeFrag)
                        .hide(otherFrag)
                        .commit();
                break;
            case R.id.radiobtn2:
                tv2.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                radiobtn2.setVisibility(View.INVISIBLE);
                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                manager.beginTransaction()
                        .show(homeFrag)
                        .hide(myFrag)
                        .hide(otherFrag)
                        .commit();
                break;
            case R.id.radiobtn3:
                tv3.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                radiobtn3.setVisibility(View.INVISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn1.setVisibility(View.VISIBLE);
                manager.beginTransaction()
                        .show(otherFrag)
                        .hide(myFrag)
                        .hide(homeFrag)
                        .commit();
                break;
        }
    }
}
