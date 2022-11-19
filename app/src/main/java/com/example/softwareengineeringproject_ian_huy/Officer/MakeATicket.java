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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.softwareengineeringproject_ian_huy.Object.Ticket;
import com.example.softwareengineeringproject_ian_huy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class MakeATicket extends AppCompatActivity {
    public static final String FIREBASE = "Firebase";
    ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE =  1001;
    Button uploadBtn;
    ImageButton captureBtn;
    ImageView imageView;
    EditText licensePlate_et, carModel_et;
    Spinner carColor_sp,violationType_sp,carState_sp;
    private Uri uri_image;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageTask mTask;
    private FirebaseFirestore db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_aticket);
        //declaring firebase objects
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        db = FirebaseFirestore.getInstance();
        //declaring views
        captureBtn = findViewById(R.id.capturePicture_btn);
        imageView = findViewById(R.id.imageView_CarPic);
        uploadBtn = findViewById(R.id.uploadTicket_btn);
        licensePlate_et = findViewById(R.id.et_carLicensePlate);
        carModel_et = findViewById(R.id.et_carModel);
        carColor_sp = findViewById(R.id.spinner_carColor);
        violationType_sp = findViewById(R.id.spinner_parkingViolationType);
        carState_sp = findViewById(R.id.spinner_carState);

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
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        if(uri_image!=null){
            String randomID = UUID.randomUUID().toString();
            String filePath = "images/" + randomID;
            StorageReference ref = storageRef.child(filePath);
            mTask = ref.putFile(uri_image)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           if(taskSnapshot.getMetadata()!= null) if(taskSnapshot.getMetadata().getReference()!=null) {
                               Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                               result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       //store this string to tickets database
                                       String imageUri = uri.toString();
                                       String licensePlate = licensePlate_et.getText().toString();
                                       String carModel = carModel_et.getText().toString();
                                       String carColor = carColor_sp.getSelectedItem().toString();
                                       String violationType = violationType_sp.getSelectedItem().toString();
                                       String carState = carState_sp.getSelectedItem().toString();
                                       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                                       String currentDateandTime = sdf.format(new Date());
                                       //initialize new ticket object
                                       DocumentReference newDocRef = db.collection("tickets")
                                                                            .document();
                                       Ticket ticket = new Ticket(newDocRef.getId(),licensePlate,carModel,carState,carColor,violationType,currentDateandTime,imageUri);
                                       newDocRef.set(ticket)
                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete( Task<Void> task) {
                                                       if(task.isSuccessful()){
                                                           Log.i(FIREBASE,"Successfully adding tickets to database");
                                                       }
                                                   }
                                               }).addOnFailureListener(new OnFailureListener() {
                                                       @Override
                                                       public void onFailure( Exception e) {
                                                           Log.e(FIREBASE,e.getMessage().toString());
                                                       }
                                       });

                                   }
                               });
                           }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure( Exception e) {
                            Toast.makeText(MakeATicket.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
//            Log.e("Image",uri_image.toString());
//            ref.putFile(uri_image)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(MakeATicket.this, "image uploaded", Toast.LENGTH_SHORT).show();

//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(Exception e) {
//                            Toast.makeText(MakeATicket.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
        }
        else{
            Toast.makeText(this, "No picture was uploaded", Toast.LENGTH_SHORT).show();
        }
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