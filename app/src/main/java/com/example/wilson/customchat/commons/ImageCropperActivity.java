package com.example.wilson.customchat.commons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;

import java.io.File;
import java.security.Permission;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageCropperActivity extends AppCompatActivity {

    @Bind(R.id.imageCropper) CropImageView cropImageView;
    public static final String IMAGE_FILE_KEY = "imageFileKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cropper);

        ButterKnife.bind(this);
        String file = getIntent().getStringExtra(IMAGE_FILE_KEY);
        Log.e("Cropper", "image path is " + file);

        File imageFile = new File(file);
        if (imageFile.exists()) {

            boolean permission = new PermissionManager().askForPermission(this,PermissionManager.WRITE_EXTERNAL_STORAGE,PermissionManager.REQUEST_WRITE_PERMISSION);

            if(permission){
                startImageLoad(Uri.fromFile(imageFile));
                startCropping(Uri.fromFile(imageFile));
            }else{
                this.onStop();
                this.onStart();
            }

        } else {
            Log.e("imageFile", "image doesn't exists");
        }

    }


    public void startImageLoad(Uri imageFile){

        cropImageView.startLoad(imageFile, new LoadCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(ImageCropperActivity.this,"load successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(ImageCropperActivity.this,"couldn't load the image",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startCropping(Uri imageFile){
        cropImageView.startCrop(imageFile, startCropCallback(),startSaveCallback());
    }

    protected CropCallback startCropCallback(){

        return new CropCallback() {
            @Override
            public void onSuccess(Bitmap cropped) {

            }

            @Override
            public void onError() {

            }
        };
    }

    protected SaveCallback startSaveCallback(){
        return new SaveCallback() {
            @Override
            public void onSuccess(Uri outputUri) {

            }

            @Override
            public void onError() {

            }
        };
    }

}
