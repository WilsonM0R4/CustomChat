package com.example.wilson.customchat.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.HomeActivity;
import com.example.wilson.customchat.register.RegisterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{

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
        loginPresenter = new LoginPresenterImplement();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loadingTitle));
        progressDialog.setMessage(getString(R.string.loadingMessage));

        loginPresenter.startAuthStateListener();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        loginPresenter.stopAuthStateListener();
    }

    @Override
    public void showProgresDialog() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void hideProgresDialog() {
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
            loginPresenter.getLoginActivityReference(this);
            loginPresenter.signIn(etEmail.getText().toString(), etPass.getText().toString());
            //loginPresenter.startAuthStateListener();
        }
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void onSignUpPressed() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToHomeScreen() {
        //hideProgresDialog();
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

}
