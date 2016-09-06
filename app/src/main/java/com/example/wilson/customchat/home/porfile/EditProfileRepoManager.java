package com.example.wilson.customchat.home.porfile;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

/**
 * Created by wmora on 9/5/16.
 */
public class EditProfileRepoManager {

    private FirebaseHelper firebaseHelper;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private Activity context;

    public EditProfileRepoManager(Activity context){
        this.context = context;
        firebaseHelper = FirebaseHelper.getInstance();
        databaseReference = firebaseHelper.getDatabaseReference();
        user = firebaseHelper.getCurrentUserReference();
    }

    public void updateUserData(Map<String,Object> userInfo){


        if(!userInfo.isEmpty()){
            databaseReference.child(User.EXTRA_DATA_KEY).child(User.formatEmail(user.getEmail())).updateChildren(userInfo).addOnCompleteListener(onComplete());
        }else{
            Log.e("Repository","no user data received");
        }
    }

    private OnCompleteListener onComplete(){
        return new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isComplete()){
                    Toast.makeText(context,"Tu información ha sido actualizada",Toast.LENGTH_SHORT).show();
                    context.finish();
                }
            }
        };
    }

}
