package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.myactivity.Buyctivity;
import com.bw.movie.myactivity.EmailActivity;
import com.bw.movie.myactivity.IdeaActivity;
import com.bw.movie.myactivity.NoticeActivity;
import com.bw.movie.myactivity.PayActivity;
import com.bw.movie.myactivity.PersonActivity;
import com.bw.movie.myactivity.ReviewActivity;
import com.bw.movie.myactivity.SeenActivity;
import com.bw.movie.myactivity.SetActivity;
import com.bw.movie.myactivity.TicketActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/10 16:43
 */
public class OtherFrag extends Fragment {
    @BindView(R.id.ppp)
    TextView ppp;
    @BindView(R.id.my_email)
    ImageView myEmail;
    @BindView(R.id.my_img)
    ImageView myImg;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.iii)
    ImageView iii;
    @BindView(R.id.my_ticket)
    TextView myTicket;
    @BindView(R.id.my_pay)
    ImageView myPay;
    @BindView(R.id.aaa)
    TextView aaa;
    @BindView(R.id.my_notice)
    ImageView myNotice;
    @BindView(R.id.bbb)
    TextView bbb;
    @BindView(R.id.my_buy)
    ImageView myBuy;
    @BindView(R.id.ccc)
    TextView ccc;
    @BindView(R.id.my_seen)
    ImageView mySeen;
    @BindView(R.id.ddd)
    TextView ddd;
    @BindView(R.id.my_review)
    ImageView myReview;
    @BindView(R.id.eee)
    TextView eee;
    @BindView(R.id.my_idea)
    ImageView myIdea;
    @BindView(R.id.fff)
    TextView fff;
    @BindView(R.id.my_set)
    ImageView mySet;
    @BindView(R.id.ggg)
    TextView ggg;
    Unbinder unbinder;
    @BindView(R.id.my_ticket_img)
    ImageView myTicketImg;
    private SharedPreferences isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.otherfrag, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLogin = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String nickName = isLogin.getString("nickName", "");
        String headPic = isLogin.getString("headPic", "");
        myName.setText(nickName);
        Glide.with(this).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(myImg);

    }
    @OnClick({R.id.my_email, R.id.my_img, R.id.my_name, R.id.my_back, R.id.my_ticket, R.id.my_ticket_img, R.id.my_pay, R.id.my_notice, R.id.my_buy, R.id.my_seen, R.id.my_review, R.id.my_idea, R.id.my_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_email:
                Intent email = new Intent(getContext(), EmailActivity.class);
                startActivity(email);
                break;
            case R.id.my_img:
                break;
            case R.id.my_name:
                break;
            case R.id.my_back:
                Intent intent = new Intent(getContext(), PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.my_ticket:
                Intent ticket = new Intent(getContext(), TicketActivity.class);
                startActivity(ticket);
                break;
            case R.id.my_ticket_img:
                Intent tickets = new Intent(getContext(), TicketActivity.class);
                startActivity(tickets);
                break;
            case R.id.my_pay:
                Intent pay = new Intent(getContext(), PayActivity.class);
                startActivity(pay);
                break;
            case R.id.my_notice:
                Intent notice = new Intent(getContext(), NoticeActivity.class);
                startActivity(notice);
                break;
            case R.id.my_buy:
                Intent buy = new Intent(getContext(), Buyctivity.class);
                startActivity(buy);
                break;
            case R.id.my_seen:
                Intent seen = new Intent(getContext(), SeenActivity.class);
                startActivity(seen);
                break;
            case R.id.my_review:
                Intent review = new Intent(getContext(), ReviewActivity.class);
                startActivity(review);
                break;
            case R.id.my_idea:
                Intent idea = new Intent(getContext(), IdeaActivity.class);
                startActivity(idea);
                break;
            case R.id.my_set:
                Intent setn = new Intent(getContext(), SetActivity.class);
                startActivity(setn);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
