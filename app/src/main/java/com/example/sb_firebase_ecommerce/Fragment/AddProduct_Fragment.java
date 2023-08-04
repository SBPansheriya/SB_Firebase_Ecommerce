package com.example.sb_firebase_ecommerce.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sb_firebase_ecommerce.DataModal;
import com.example.sb_firebase_ecommerce.databinding.ActivityAddProductFragmentBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.v;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class AddProduct_Fragment extends Fragment {

    ActivityAddProductFragmentBinding binding;
    FirebaseDatabase database;
    String id;
    DatabaseReference myRef;
    FirebaseStorage mainbucket;
    StorageReference imgfolder;
    UploadTask uploadTask;
    String imgurl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityAddProductFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mainbucket = FirebaseStorage.getInstance();
        String imgname = "Img" + new Random().nextInt(100000) + ".jpg";
        imgfolder = mainbucket.getReference().child("Images/" + imgname);


        binding.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getimages();
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Product").push();
        id = myRef.getKey();

        binding.submitproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimages();
                DataModal modal = new DataModal(id, binding.productname.getText().toString(), binding.productprice.getText().toString(), binding.discription.getText().toString(), imgurl);
                myRef.setValue(modal);
                Toast.makeText(getContext(), "Product Add Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void getimages() {
        CropImage.activity()
                .start(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                binding.addimage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void uploadimages() {
        binding.addimage.setDrawingCacheEnabled(true);
        binding.addimage.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) binding.addimage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        uploadTask = imgfolder.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return imgfolder.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            Log.d("GGG", "onComplete: "+downloadUri);
                            imgurl = String.valueOf(downloadUri);

                        } else {

                        }
                    }
                });
            }
        });
    }


}