package com.example.wilson.customchat.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.wilson.customchat.R;
import com.example.wilson.customchat.login.LoginActivity;

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
        registerPresenter = new RegisterPresenterImplement(this,regContainer);
        registerPresenter.onCreate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        registerPresenter.onDestroy();
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
        String pass = etRegPasss.getText().toString();
        if(pass.equals(etRegConfirmPass.getText().toString()))
            registerPresenter.signUp(etRegEmail.getText().toString(),pass);
        else {
            etRegPasss.setError("las contraseñas no coinciden");
            etRegConfirmPass.setError("las contraseñas no coinciden");
        }
    }

    @OnClick(R.id.btnCancelRegister)
    @Override
    public void onCancelPressed() {
        //startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    @Override
    public void showSnackBar() {
        /*Snackbar snackbar = Snackbar.make(regContainer,"Registro exitoso",Snackbar.LENGTH_LONG);
        snackbar.show();*/
        Snackbar.make(regContainer,"Registro exitoso",Snackbar.LENGTH_INDEFINITE).setAction("Iniciar sesión", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("reg_mail",etRegEmail.getText());
                startActivity(intent);
                finish();
            }
        }).show();

    }
}
