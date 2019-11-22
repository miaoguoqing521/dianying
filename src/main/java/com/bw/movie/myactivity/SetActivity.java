package com.bw.movie.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.utils.CleanDateUtils;
import com.bw.movie.myactivity.setactivity.UpdateVersion;
import com.bw.movie.view.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 11:28
 */
public class SetActivity extends AppCompatActivity {
    @BindView(R.id.set_back)
    ImageView setBack;
    @BindView(R.id.set_clear)
    TextView setClear;
    @BindView(R.id.set_update)
    ImageView setUpdate;
    @BindView(R.id.set_reset)
    ImageView setReset;
    @BindView(R.id.set_btn)
    Button setBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);
        ButterKnife.bind(this);

        try {
            String totalCacheSize = CleanDateUtils.getTotalCacheSize(this);
            setClear.setText(totalCacheSize + "M");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.set_back, R.id.set_clear, R.id.set_update, R.id.set_reset, R.id.set_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_back:
                finish();
                break;
            case R.id.set_clear:
                CleanDateUtils.clearAllCache(this);
                setClear.setText(0+"M");
                break;
            case R.id.set_update:
                startActivity(new Intent(SetActivity.this, UpdateVersion.class));
                break;
            case R.id.set_reset:

                break;
            case R.id.set_btn:
                startActivity(new Intent(SetActivity.this, LoginActivity.class));
                break;
        }
    }
}
