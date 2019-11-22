package com.bw.movie.myactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.FileImageBean;
import com.bw.movie.model.bean.IdNameBean;
import com.bw.movie.presenter.IdNamePresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 11:22
 */
public class PersonActivity extends AppCompatActivity implements IMainView.IdNameView {


    @BindView(R.id.head_back)
    ImageView headBack;
    @BindView(R.id.header)
    TextView header;
    @BindView(R.id.header_img)
    ImageView headerImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.header_name)
    TextView headerName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.head_sex)
    TextView headSex;
    @BindView(R.id.header_time)
    TextView headerTime;
    @BindView(R.id.person_email)
    TextView personEmail;
    private IdNamePresenter idNamePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);
        ButterKnife.bind(this);
        idNamePresenter = new IdNamePresenter();
        idNamePresenter.attach(this);
        SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        idNamePresenter.getDate(userId,sessionId);
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        headerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });
    }

    @Override
    public void idname(IdNameBean idNameBean) {
        IdNameBean.ResultBean result = idNameBean.result;
        headerName.setText(result.nickName);
        if (result.sex==1) {
            headSex.setText("男");
        }else {
            headSex.setText("女");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd");
        String format = simpleDateFormat.format(result.lastLoginTime);
        headerTime.setText(format);
        Glide.with(this).load(result.headPic).into(headerImg);
    }

    @Override
    public void image(FileImageBean fileImageBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&resultCode==RESULT_OK){
            Uri data1 = data.getData();
            Glide.with(this).load(data1).into(headerImg);
        }
    }
}
