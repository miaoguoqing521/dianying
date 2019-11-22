package com.bw.movie.myactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.model.bean.BookMovieBean;
import com.bw.movie.myactivity.notice.NoticeAdapter;
import com.bw.movie.presenter.NoticePresenter;
import com.bw.movie.view.interfaces.IAttent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 11:27
 */
public class NoticeActivity extends AppCompatActivity implements IAttent.INoticeView {
    @BindView(R.id.make_back)
    ImageView makeBack;
    @BindView(R.id.notice_recycler)
    RecyclerView noticeRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_activity);
        ButterKnife.bind(this);

        NoticePresenter noticePresenter = new NoticePresenter();
        noticePresenter.attach(this);
        SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        noticePresenter.getDate(userId,sessionId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        noticeRecycler.setLayoutManager(linearLayoutManager);

        makeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void succ(BookMovieBean bookMovieBean) {
        List<BookMovieBean.ResultBean> result = bookMovieBean.result;
        NoticeAdapter noticeAdapter = new NoticeAdapter(result,this);
        noticeRecycler.setAdapter(noticeAdapter);
    }
}
