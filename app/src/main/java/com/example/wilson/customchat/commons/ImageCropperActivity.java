package com.example.wilson.customchat.commons;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.LoadCallback;

import java.io.File;

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
        String file =  getIntent().getStringExtra(IMAGE_FILE_KEY);
        Log.e("Cropper","image path is "+file);
        startImageLoad(file);

    }

    public void startImageLoad(String imageFile){

        cropImageView.startLoad(Uri.fromFile(new File(imageFile)), new LoadCallback() {
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
}
