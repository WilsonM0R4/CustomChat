package com.example.wilson.customchat.home;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.chats.FragmentChats;
import com.example.wilson.customchat.home.contacts.FragmentContacts;
import com.example.wilson.customchat.home.porfile.FragmentPorfile;
import com.example.wilson.customchat.home.porfile.HomeView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @Bind(R.id.homeVewPager) ViewPager homeViewPager;
    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    //@Bind(R.id.btnSignOff) Button btnSignOff;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter(){

        FragmentPorfile profile = new FragmentPorfile();

        String[] titles = new String[]{getString(R.string.title_chat_page),
                getString(R.string.title_contacts_page),
                getString(R.string.title_profile_page)};
        Fragment[] fragments = new Fragment[]{new FragmentChats(), new FragmentContacts(), profile.newInstance(HomeActivity.this)};
        HomeFragmentPagerAdapter pageAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        homeViewPager.setAdapter(pageAdapter);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorTitleNormal),getResources().getColor(R.color.colorTitleSelected));
        tabLayout.setupWithViewPager(homeViewPager);
    }

    @Override
    public void destroy() {
        this.finish();
    }
}
