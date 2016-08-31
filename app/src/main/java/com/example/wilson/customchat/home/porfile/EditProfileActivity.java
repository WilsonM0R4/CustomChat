package com.example.wilson.customchat.home.porfile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.commons.ImageCropperActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {

    @Bind(R.id.toolAppbar) Toolbar appBar;
    @Bind(R.id.editCancel) ImageButton cancelButton;
    @Bind(R.id.editSave)
    ImageButton saveButton;
    @Bind(R.id.editProfileImage)
    ImageView editProfileImage;

    private static final int REQUEST_CAPTURE = 0;
    private String newProfileImagePath;
    File profileImageFile;
    public String imageFolderName = "/CustomChatImages/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setSupportActionBar(appBar);

        ButterKnife.bind(this);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap imageBitmap;

        if (requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK) {

            imageBitmap = (Bitmap) data.getExtras().get("data");

            if (imageBitmap != null) {
                Intent intent = new Intent(EditProfileActivity.this, ImageCropperActivity.class);
                intent.putExtra(ImageCropperActivity.IMAGE_FILE_KEY,newProfileImagePath);
                startActivity(intent);
            } else {
                Log.e("activity result", "bitmap is null");
            }
        }

    }

    @OnClick(R.id.editSave)
    protected void sendUserDataToCloud() {
        Toast.makeText(this, "run to the hiiiiiiiils!!!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.editCancel)
    protected void cancel() {
        finish();
    }

    @OnClick(R.id.editProfileImage)
    protected void editImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {

            try {
                profileImageFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (profileImageFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, profileImageFile.getAbsolutePath());
                startActivityForResult(cameraIntent, REQUEST_CAPTURE);
            }

        }

    }

    private File createImageFile() throws IOException {

        String imageName = "CChat_profile_img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageType = ".jpg";

        String storagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)+imageFolderName;
        File image = new File(storagePath+imageName+imageType);

        newProfileImagePath = image.getAbsolutePath();

        return image;
    }

    private Bitmap imageFromFile(File imageFile) {

        return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
    }

}
