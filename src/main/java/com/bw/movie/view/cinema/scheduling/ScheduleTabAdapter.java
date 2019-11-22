package com.bw.movie.view.cinema.scheduling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 20:50
 */
public class ScheduleTabAdapter extends FragmentPagerAdapter {
    List<String> result;
    int cinemaId;

    public ScheduleTabAdapter(FragmentManager fm, List<String> result, int cinemaId) {
        super(fm);
        this.result = result;
        this.cinemaId = cinemaId;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("did",cinemaId);
        ScheduleTabFragment scheduleTabFragment = new ScheduleTabFragment();
        scheduleTabFragment.setArguments(bundle);
        return scheduleTabFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return result.get(position);
    }

    @Override
    public int getCount() {
        return result.size();
    }


}
