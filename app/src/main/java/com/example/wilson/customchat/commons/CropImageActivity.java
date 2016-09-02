package com.example.wilson.customchat.commons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wilson.customchat.R;

import butterknife.ButterKnife;

public class CropImageActivity extends AppCompatActivity {

    //@Bind()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        ButterKnife.bind(this);
    }
}
