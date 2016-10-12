package com.example.wilson.customchat.commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wilson.customchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 9/29/16.
 */
public class MessageDialog extends DialogFragment implements View.OnClickListener {

    /**
     * @ButterKnife bind views
     **/
    @Bind(R.id.dialog_title)
    TextView dialogTitle;
    @Bind(R.id.dialog_message)
    TextView dialogMessage;
    @Bind(R.id.button_cancel)
    Button buttonCancel;
    @Bind(R.id.btn_accept)
    Button buttonAccept;

    /**
     * private variables
     **/

    private View view;
    private Activity activity;
    private String message, title;
    private boolean haveCancel = false;
    private View.OnClickListener acceptListener;
    private View.OnClickListener cancelListener;

    /**
     * returns a new instance for this class
     **/

    public MessageDialog newInstance(Activity activity, String title, String message) {
        this.activity = activity;
        this.title = title;
        this.message = message;
        return this;
    }

    /**
     * create the view for this dialog
     * **/

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.message_dialog, null);

        if (view != null) {
            ButterKnife.bind(this, view);

        }

        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }

    /**
     * called when Dialog starts
     * set onClickListeners if exists
     * **/

    @Override
    public void onStart() {
        super.onStart();
        //if(dialogTitle!=null && dialogMessage!=null){
        dialogTitle.setText(title);
        dialogMessage.setText(message);

        if(acceptListener!=null){
            buttonAccept.setOnClickListener(acceptListener);
        }else{
            buttonAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissMessageView();
                }
            });
        }

        if(cancelListener!=null){
            buttonCancel.setOnClickListener(cancelListener);
        }
        //}
    }

    /**
     * Dismiss the dialog
     * **/
    private void dismissMessageView(){
        this.dismiss();
    }

    /**
     * set the onAcceptClickListener
     * **/
    public void setOnAcceptClickListener(View.OnClickListener onClickListener) {
        this.acceptListener = onClickListener;
    }

    /**
     * set the onCancelClickListener
     * **/
    public void  setOnCancelClickListener(View.OnClickListener cancelListener){
        this.cancelListener = cancelListener;
    }

    /**
     * override method
     * **/
    @Override
    public void onClick(View v) {
        if (v != null) {
            this.onClick(v);
        }
    }
}
