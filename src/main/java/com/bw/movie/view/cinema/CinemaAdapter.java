package com.bw.movie.view.cinema;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 15:04
 */
public class CinemaAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private List<Fragment> fragmentList;

    public CinemaAdapter(FragmentManager fm, List<String> list, List<Fragment> fragmentList) {
        super(fm);
        this.list = list;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
