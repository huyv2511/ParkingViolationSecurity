package com.example.softwareengineeringproject_ian_huy.Officer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.R;

public class MakeATicket extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE =  1001;
    Button captureBtn;
    ImageView imageView;
    Uri uri_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_aticket);

        captureBtn = findViewById(R.id.capturePicture_btn);
        imageView = findViewById(R.id.imageView_CarPic);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == IMAGE_CAPTURE_CODE){
                            Toast.makeText(MakeATicket.this, "GRanted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MakeATicket.this, "Clicked!", Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//request runtime permission
                    if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //if the permission is denied
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_CODE);
                    }
                    else{
                        //permission is granted
                        Toast.makeText(MakeATicket.this, "Permission is already granted!", Toast.LENGTH_SHORT).show();
                        callYourCamera();
                    }
                }
                else{
                    //permission is granted
                    Toast.makeText(MakeATicket.this, "Permission is already granted!", Toast.LENGTH_SHORT).show();
                    callYourCamera();
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION_CODE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    callYourCamera();
                }
                else{
                    Toast.makeText(this, "Permission denied! Please try again", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            imageView.setImageURI((uri_image));
        }

    }
    private void callYourCamera(){
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE,"New picture");
        cv.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        uri_image = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cv);

        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri_image);
        activityResultLauncher.launch(cameraIntent);

    }
}