package com.example.wilson.customchat.home.porfile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 9/5/16.
 */
public class AvailabilityDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.availability_spinner) Spinner availabilitySpiner;
    @Bind(R.id.dialogTitle)TextView dialogTitle;
    @Bind(R.id.buttonAccept) Button buttonAccept;
    @Bind(R.id.buttonDecline) Button buttonDecline;
    private View dialogView;
    private Activity activity;
    private FragmentPorfile fragment;
    String selectedItem;

    public AvailabilityDialog newInstance(HomeActivity activity, FragmentPorfile fragment){
        AvailabilityDialog dialog = new AvailabilityDialog();
        this.activity = activity;
        this.fragment = fragment;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        if(activity!=null){
            dialogView = LayoutInflater.from(activity).inflate(activity.getResources().getLayout(R.layout.availability_dialog),null);
            ButterKnife.bind(this,dialogView);
            populateSpinner();
            setTitle();
        }else{
            Toast.makeText(getActivity().getBaseContext(),"No podemos cargar la vista, por favor ingresa de nuevo a la aplicación",Toast.LENGTH_SHORT).show();
        }


        return new AlertDialog.Builder(activity).setView(dialogView).create();
    }

    private void populateSpinner(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activity.getBaseContext(),R.array.availability_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        availabilitySpiner.setAdapter(adapter);
        availabilitySpiner.setOnItemSelectedListener(this);
    }

    private void setTitle(){
        if(dialogTitle!=null){
            dialogTitle.setText(R.string.availability_string);
        }else{
            Log.e("AvailabilityDialog","dialogTitle is null");
        }

    }

    @OnClick(R.id.buttonAccept)
    public void onAcceptPressed(){
        if(selectedItem!=null){
            fragment.onAvailabilityDialogFinished(selectedItem);
            this.dismiss();
            Log.e("availabilityDialog","accept pressed, new value is "+selectedItem);
        }

    }

    @OnClick(R.id.buttonDecline)
    public void onDeclinePressed(){
        this.dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = parent.getItemAtPosition(position).toString();
        Log.e("availabilityDialog","selected item is "+selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedItem = availabilitySpiner.getSelectedItem().toString();
        Log.e("availabilityDialog","selected item is "+selectedItem);
    }
}