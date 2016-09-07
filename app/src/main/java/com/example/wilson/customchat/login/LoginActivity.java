package com.example.wilson.customchat.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.ViewHelper;
import com.example.wilson.customchat.home.HomeActivity;
import com.example.wilson.customchat.register.RegisterActivity;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView,ViewHelper{

    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etPassword) EditText etPass;
    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.btnSignIn) Button btnSignIn;
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImplement(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loadingTitle));
        progressDialog.setMessage(getString(R.string.loadingMessage));

        loginPresenter.onCreate();
        if(loginPresenter!=null){
            loginPresenter.checkForActualSessionState();
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        loginPresenter.onDestroy();
    }


    @Override
    public void showProgressDialog() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void enableInputs() {
        etEmail.setEnabled(true);
        etPass.setEnabled(true);
        btnSignIn.setEnabled(true);
        btnSignUp.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        etEmail.setEnabled(false);
        etPass.setEnabled(false);
        btnSignIn.setEnabled(false);
        btnSignUp.setEnabled(false);
    }

    @OnClick(R.id.btnSignIn)
    @Override
    public void handleSignIn() {
        //call presenter function

        if(loginPresenter!=null) {
            loginPresenter.signIn(etEmail.getText().toString(), etPass.getText().toString());
            Log.e("login activity","login requested");
        }
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void onSignUpPressed() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

}
