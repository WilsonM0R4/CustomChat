package com.example.wilson.customchat.home.porfile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.ImageCropperActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {

    @Bind(R.id.toolAppbar) Toolbar appBar;
    @Bind(R.id.editCancel) ImageButton cancelButton;
    @Bind(R.id.editSave) ImageButton saveButton;
    @Bind(R.id.editProfileImage) ImageView editProfileImage;
    @Bind(R.id.editUsername) EditText txtEditUsername;
    @Bind(R.id.editState) EditText txtEditState;
    @Bind(R.id.editPassword) EditText txtEditPassword;
    @Bind(R.id.editPassConfirm) EditText txtEditConfPass;

    private static final int REQUEST_CAPTURE = 0;
    private String newProfileImagePath;
    File profileImageFile;
    public String imageFolderName = "/CustomChatImages/";
    final String imagesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + imageFolderName;

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

        if ((requestCode == REQUEST_CAPTURE) && (resultCode == Activity.RESULT_OK) && (profileImageFile!=null)) {

            newProfileImagePath = profileImageFile.getAbsolutePath();
            Log.e("activity result","newProfileImagePath is "+newProfileImagePath);

            Intent intent = new Intent(EditProfileActivity.this, ImageCropperActivity.class);
            intent.putExtra(ImageCropperActivity.IMAGE_FILE_KEY, newProfileImagePath);
            startActivity(intent);
        }

    }

    @OnClick(R.id.editSave)
    protected void sendUserDataToCloud() {
        //Toast.makeText(this, "run to the hiiiiiiiils!!!", Toast.LENGTH_SHORT).show();

        Map<String,Object> userInfo = new HashMap<>();

        if(checkUserData()){
            userInfo.put(User.USER_AVALIABILITY,String.valueOf(User.USER_ONLINE));
            userInfo.put(User.USER_PROFILE_IMAGE,User.NONE_IMAGE);
            userInfo.put(User.USER_STATE,txtEditState.getText().toString());
            userInfo.put(User.USERNAME,txtEditUsername.getText().toString());
        }

        EditProfileRepoManager repository = new EditProfileRepoManager(this);
        repository.updateUserData(userInfo);

    }

    @OnClick(R.id.editCancel)
    protected void cancel() {
        finish();
    }

    @OnClick(R.id.editProfileImage)
    protected void editImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            profileImageFile = createImageFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (profileImageFile != null) {
            Log.e("editImageActivity", "image path is " + profileImageFile.getAbsolutePath());
            Uri uri = Uri.fromFile(profileImageFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent, REQUEST_CAPTURE);
        } else {
            Log.e("edit image void", "profileImageFile is null");

        }

    }

    private File createImageFile() throws IOException {

        String imageName = "CChat_profile_img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageType = ".jpg";

        File directory = new File(imagesDirectory);
        File image = new File(imagesDirectory + imageName + imageType);

        Boolean isCreated = directory.mkdirs();

        if (isCreated && directory.isDirectory()) {
            Boolean imageCreated = image.createNewFile();

            if(imageCreated){
                newProfileImagePath = image.getAbsolutePath();
                Log.e("imageFile","image created successfully");
            }else{
                Log.e("imageFile","image not created");
            }
        } else {
            Log.e("imageDir", "directory not created");
        }

        return image;
    }

    private Bitmap imageFromFile(File imageFile) {

        return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
    }

    private boolean checkUserData(){
        return (txtEditUsername.getText().toString()!=null && txtEditState.getText().toString()!=null);
    }
}
