package com.example.wilson.customchat.register;

import android.app.ProgressDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    @Bind(R.id.registerContainer) CoordinatorLayout regContainer;
    ProgressDialog progressDialogRegister;
    RegisterPresenter registerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenterImplement(this);
        ButterKnife.bind(this);
    }

    @Override
    public void enableInputs() {
        etRegEmail.setEnabled(true);
        etRegPasss.setEnabled(true);
        etRegConfirmPass.setEnabled(true);
        btnCancel.setEnabled(true);
        btnSignUp.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        etRegEmail.setEnabled(false);
        etRegPasss.setEnabled(false);
        etRegConfirmPass.setEnabled(false);
        btnCancel.setEnabled(false);
        btnSignUp.setEnabled(false);
    }

    @Override
    public void showProgresDialog() {
        progressDialogRegister = new ProgressDialog(this);
        progressDialogRegister.setTitle(getString(R.string.register_title));
        progressDialogRegister.setMessage(getString(R.string.loadingMessage));
        progressDialogRegister.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialogRegister.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialogRegister!=null)
            progressDialogRegister.dismiss();
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {
        registerPresenter.signUp("qw","qw");
    }

    @OnClick(R.id.btnCancelRegister)
    @Override
    public void onCancelPressed() {
        this.finish();
    }

    @Override
    public void showSnackBar() {
        Snackbar snackbar = Snackbar.make(regContainer,"Registro exitoso",Snackbar.LENGTH_LONG);
        snackbar.show();

    }
}
