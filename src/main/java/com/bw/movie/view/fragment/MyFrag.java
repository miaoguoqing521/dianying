package com.bw.movie.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.BanBean;
import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.myactivity.MoreActivity;
import com.bw.movie.presenter.BanPresenter;
import com.bw.movie.view.activity.SearchActivity;
import com.bw.movie.view.adapter.RecyAdapt;
import com.bw.movie.view.adapter.RecyAdapte;
import com.bw.movie.view.adapter.RecyAdapter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.DetailsMovie;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.xuezj.cardbanner.CardBanner;
import com.xuezj.cardbanner.ImageData;
import com.xuezj.cardbanner.imageloader.CardImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者： 姓名
 * 日期： 2019/10/10 16:42
 */
public class MyFrag extends Fragment implements IMainView.IBanView {
    @BindView(R.id.ban)
    CardBanner ban;
    Unbinder unbinder;
    @BindView(R.id.recy1)
    RecyclerView recy1;
    @BindView(R.id.recy2)
    RecyclerView recy2;
    @BindView(R.id.recy3)
    RecyclerView recy3;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.Heat_more)
    TextView HeatMore;
    @BindView(R.id.be_more)
    TextView beMore;
    @BindView(R.id.hot_more)
    TextView hotMore;
    @BindView(R.id.imagedw)
    ImageView imagedw;
    @BindView(R.id.textdw)
    TextView textdw;
    @BindView(R.id.film_bannershu)
    TextView filmBannershu;
    @BindView(R.id.i1)
    ImageView i1;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.hit_layout1)
    RelativeLayout hitLayout1;
    @BindView(R.id.i2)
    ImageView i2;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.coming_layout1)
    RelativeLayout comingLayout1;
    @BindView(R.id.i3)
    ImageView i3;
    @BindView(R.id.t3)
    TextView t3;
    @BindView(R.id.hot_layout1)
    RelativeLayout hotLayout1;
    @BindView(R.id.hot_bjt)
    ImageView hotBjt;
    private List<String> list = new ArrayList<>();
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private int GPS_REQUEST_CODE = 10;
    private List<ImageData> listbanner = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.myfrag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        BanPresenter banPresenter = new BanPresenter();
        banPresenter.attach(this);
        banPresenter.getDate();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy1.setLayoutManager(linearLayoutManager);
        hotMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MoreActivity.class));
            }
        });

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recy2.setLayoutManager(linearLayoutManager1);
        beMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MoreActivity.class));
            }
        });

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy3.setLayoutManager(linearLayoutManager2);
        HeatMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MoreActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //点击定位
        imagedw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                    //开启定位权限,200是标识码
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                } else {
                    MyLocation(getActivity());//开始定位
                }
            }
        });
    }

    @Override
    public void succ(BanBean banBean) {
        final List<BanBean.ResultBean> result = banBean.result;
//        ban.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView sim = view.findViewById(R.id.sim);
//                DraweeController build = Fresco.newDraweeControllerBuilder()
//                        .setUri(result.get(position).imageUrl)
//                        .setAutoPlayAnimations(true)
//                        .build();
//                sim.setController(build);
//            }
//        });
        for (int i = 0; i < result.size(); i++) {
            ImageData imageData = new ImageData();
            imageData.setImage(result.get(i).imageUrl);
            int rank = result.get(i).rank;
            listbanner.add(imageData);
        }
        ban.setDatas(listbanner)
                .setCardImageLoader(new CardImageLoader() {
                    @Override
                    public void load(Context context, ImageView imageView, Object path) {
                        Glide.with(getContext()).load(path).into(imageView);
                    }
                }).setPlay(true).setDelayTime(3000).start();

    }

    @Override
    public void suc(ShangMovieBean shangMovieBean) {
        final List<ShangMovieBean.ResultBean> result = shangMovieBean.result;
        RecyAdapter recyAdapter = new RecyAdapter(result, getActivity());
        recy1.setAdapter(recyAdapter);

        recyAdapter.setSetOnItem(new RecyAdapter.setOnItem() {

            private SharedPreferences isP;

            @Override
            public void setOn(int position) {
                Intent intent = new Intent(getContext(), DetailsMovie.class);
                int movieId = result.get(position).movieId;
                isP = getContext().getSharedPreferences("isP", Context.MODE_PRIVATE);
                isP.edit().putInt("movieId", movieId).commit();
                startActivity(intent);
            }
        });
    }

    @Override
    public void ji(JiMoviewBean jiMoviewBean) {
        final List<JiMoviewBean.ResultBean> result = jiMoviewBean.result;
        RecyAdapte recyAdapte = new RecyAdapte(result, getActivity());
        recyAdapte.setSetOnItem(new RecyAdapte.setOnItem() {
            @Override
            public void setOn(int position) {
                Intent intent = new Intent(getContext(), DetailsMovie.class);
                int movieId = result.get(position).movieId;
                SharedPreferences isP = getContext().getSharedPreferences("isP", Context.MODE_PRIVATE);
                isP.edit().putInt("movieId",movieId).commit();
                startActivity(intent);
            }
        });
        recy2.setAdapter(recyAdapte);
        recy2.setHasFixedSize(true);
        recy2.setNestedScrollingEnabled(false);
        recy2.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recy2.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public void re(ReMovieBean reMovieBean) {
        List<ReMovieBean.ResultBean> result = reMovieBean.result;
        Glide.with(getContext()).load(result.get(1).imageUrl).into(hotBjt);
        RecyAdapt recyAdapt = new RecyAdapt(result, getActivity());
        recy3.setAdapter(recyAdapt);
    }

    /*
     * 定位 判断是否开启权限
     * 有 执行
     * 无 弹框提示进入设置开启权限
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    MyLocation(getActivity());//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getActivity(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    /*
     * 定位成功回调信息，设置相关消息
     * */
    public void MyLocation(Context context) {
        mlocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                try {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息

                            //获取当前定位结果来源，如网络定位结果，详见定位类型表
                            Log.i("定位类型", amapLocation.getLocationType() + "");
                            Log.i("获取纬度", amapLocation.getLatitude() + "");
                            Log.i("获取经度", amapLocation.getLongitude() + "");
                            Log.i("获取精度信息", amapLocation.getAccuracy() + "");
                            //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                            Log.i("地址", amapLocation.getAddress());
                            Log.i("国家信息", amapLocation.getCountry());
                            Log.i("省信息", amapLocation.getProvince());
                            Log.i("城市信息", amapLocation.getCity());
                            Log.i("城区信息", amapLocation.getDistrict());
                            Log.i("街道信息", amapLocation.getStreet());
                            Log.i("街道门牌号信息", amapLocation.getStreetNum());
                            Log.i("城市编码", amapLocation.getCityCode());
                            Log.i("地区编码", amapLocation.getAdCode());
                            Log.i("获取当前定位点的AOI信息", amapLocation.getAoiName());
                            Log.i("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
                            Log.i("获取当前室内定位的楼层", amapLocation.getFloor());
                            Log.i("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");
                            //获取定位时间
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            Log.i("获取定位时间", df.format(date));
                            textdw.setText(amapLocation.getDistrict());
                            // 停止定位
                            mlocationClient.stopLocation();
                        } else {
                            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                            Toast.makeText(getActivity(), "没有权限，请打开权限...", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("定位服务未开启")
                                    .setMessage("请在系统设置中开启定位服务\n" +
                                            "以便为您提供更好的服务")
                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, GPS_REQUEST_CODE);
                                        }
                                    })
                                    .show();
                        }
                    }
                } catch (Exception e) {
                }

            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(5000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //启动定位
        mlocationClient.startLocation();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);

        } else {
            MyLocation(getActivity());//开始定位
            //Toast.makeText(getActivity(),"已开启定位权限",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 停止定位
        if (null != mlocationClient) {
            mlocationClient.stopLocation();
        }

    }

    //内存泄露和定位
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (null != mlocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mlocationClient.onDestroy();
            mlocationClient = null;
        }
    }
}
