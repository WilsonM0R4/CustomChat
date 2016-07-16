package com.example.wilson.customchat.home;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.chats.FragmentChats;
import com.example.wilson.customchat.home.contacts.FragmentContacts;
import com.example.wilson.customchat.home.porfile.FragmentPorfile;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.homeVewPager) ViewPager homeViewPager;
    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.btnSignOff) Button btnSignOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        btnSignOff.setVisibility(View.GONE);
        setupAdapter();
    }

    private void setupAdapter(){
        String[] titles = new String[]{getString(R.string.title_chat_page),
                getString(R.string.title_contacts_page),
                getString(R.string.title_profile_page)};
        Fragment[] fragments = new Fragment[]{new FragmentChats(), new FragmentContacts(), new FragmentPorfile()};
        HomeFragmentPagerAdapter pageAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        homeViewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(homeViewPager);
    }




}
