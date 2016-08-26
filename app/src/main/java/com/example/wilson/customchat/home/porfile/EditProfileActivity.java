package com.example.wilson.customchat.home.porfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wilson.customchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {

    @Bind(R.id.toolAppbar) Toolbar appBar;
    @Bind(R.id.editCancel) ImageButton cancelButton;
    @Bind(R.id.editSave) ImageButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);

        setSupportActionBar(appBar);

    }

    @OnClick(R.id.editSave)
    protected void sendUserDataToCloud(){
        Toast.makeText(this,"run to the hiiiiiiiils!!!",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.editCancel)
    protected void cancel(){
        finish();
    }

}
