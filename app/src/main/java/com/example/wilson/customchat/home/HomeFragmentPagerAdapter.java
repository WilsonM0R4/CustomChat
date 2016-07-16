package com.example.wilson.customchat.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by wmora on 7/15/16.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Fragment[] fragments;


    public HomeFragmentPagerAdapter(FragmentManager fm,String[] titles,Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position){
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }
}