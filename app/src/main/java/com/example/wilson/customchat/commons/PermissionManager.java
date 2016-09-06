package com.example.wilson.customchat.commons;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by wmora on 9/5/16.
 */
public class PermissionManager extends Activity{

    public static final int REQUEST_WRITE_PERMISSION = 112;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public boolean askForPermission(Activity activity, String permission, int requestPermission){
        boolean hasPermission =(ContextCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED);

        if(!hasPermission){
            ActivityCompat.requestPermissions(activity,new String[]{permission},requestPermission);
        }

        return hasPermission;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        switch(requestCode){
            case REQUEST_WRITE_PERMISSION:

                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Log.e("Permissions manager","permission denied by the user");
                }else{
                    Log.e("Permissions manager","permission granted by the user");
                }

                break;
        }

    }
}
