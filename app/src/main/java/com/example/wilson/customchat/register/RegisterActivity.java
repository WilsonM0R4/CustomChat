package com.example.wilson.customchat.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.wilson.customchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @Bind(R.id.etRegEmail) EditText etRegEmail;
    @Bind(R.id.etRegPassword) EditText etRegPasss;
    @Bind(R.id.etRegConfirmPass) EditText etRegConfirmPass;
    @Bind(R.id.btnCancelRegister) Button btnCancel;
    @Bind(R.id.btnSignUp) Button btnSignUp;
    @Bind(R.id.buttonContainerRegister) LinearLayout buttonContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgresDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void handleSignUp() {

    }

    @OnClick(R.id.btnCancelRegister)
    @Override
    public void onCancelPressed() {
        this.finish();
    }
}
