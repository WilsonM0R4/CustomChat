package com.example.wilson.customchat.home;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.home.chats.FragmentChats;
import com.example.wilson.customchat.home.contacts.FragmentContacts;
import com.example.wilson.customchat.home.porfile.FragmentPorfile;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @Bind(R.id.homeVewPager) ViewPager homeViewPager;
    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    User user;
    //Button btnSignOff;
    FragmentPorfile profile;
    FragmentContacts contacts;
    Map<String,String> userData;
    //@Bind(R.id.btnSignOff) Button btnSignOff;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTitleSelected,getTheme()));
        setSupportActionBar(toolbar);

        profile = new FragmentPorfile();
        profile.newInstance(this);

        contacts = new FragmentContacts();
        contacts.newInstance(this);
        //btnSignOff = (Button)findViewById(R.id.btnSignOff);
        setupAdapter();
    }

    private void setupAdapter(){

        //profile.newInstance(HomeActivity.this);

        String[] titles = new String[]{
                getString(R.string.title_chat_page),
                getString(R.string.title_contacts_page),
                getString(R.string.title_profile_page)};
        Fragment[] fragments = new Fragment[]{ new FragmentChats()
                ,contacts
                , profile };
        HomeFragmentPagerAdapter pageAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        homeViewPager.setAdapter(pageAdapter);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorTitleNormal),getResources().getColor(R.color.colorTitleSelected));
        tabLayout.setupWithViewPager(homeViewPager);

    }

    @Override
    public Map getUserData() {
        userData = new HashMap<>();

        return userData;
    }

    @Override
    public void destroy() {
        this.finish();
    }

}
