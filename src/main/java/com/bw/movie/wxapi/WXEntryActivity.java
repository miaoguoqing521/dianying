package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bw.movie.view.activity.HomeActivity;
import com.bw.movie.model.bean.WxBean;
import com.bw.movie.model.okhttp.Api;
import com.bw.movie.model.okhttp.App;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther:
 * @Date: 19/09/01 19:25:27
 * @Description:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
    private static final int RETURN_MSG_TYPE_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int errCode = baseResp.errCode;
        switch (baseResp.errCode) {

            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        String state = ((SendAuth.Resp) baseResp).state;
                        if (state.equals("wechat_sdk_login")) {
                            String code = ((SendAuth.Resp) baseResp).code;
                            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                    .build();
                            Retrofit retrofit=new Retrofit.Builder()
                                    .client(okHttpClient)
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl("http://172.17.8.100/movieApi/")
                                    .build();
                            Api api = retrofit.create(Api.class);
                            api.wxBean(code)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(new Observer<WxBean>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onNext(WxBean wxBean) {
                                            if (wxBean.getStatus().indexOf("0000") != -1) {
                                                Intent intent = new Intent(WXEntryActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                        break;
                }

                break;
        }

    }

}
