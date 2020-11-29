package com.example.team2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity
{
    EditText profile_email,profile_phone,profile_name,address_profile;
    ImageView profileImage;
    CheckBox isemployee;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);


        Intent data = getIntent();
        String fullname = data.getStringExtra("name");
        String  email = data.getStringExtra("email");
        String  address = data.getStringExtra("address");
        String  phone = data.getStringExtra("phone");
        boolean checkB = data.getBooleanExtra("checkB",false);




        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });



        findViewById(R.id.confirm_botton).setOnClickListener(onClickListener);
        findViewById(R.id.change_Image).setOnClickListener(onClickListener);

        profileImage = findViewById(R.id.profile_image);
        profile_email=findViewById(R.id.profile_email);
        profile_phone=findViewById(R.id.profile_phone);
        profile_name=findViewById(R.id.profile_name);
        address_profile=findViewById(R.id.address_profile);
        isemployee=findViewById(R.id.checkBox);


        isemployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    DocumentReference docRef = fStore.collection("users").document(user.getUid());
                    docRef.update("isEmployee",true);
                }
                else{
                    DocumentReference docRef = fStore.collection("users").document(user.getUid());
                    docRef.update("isEmployee",false);
                }
            }
        });


        profile_email.setText(email);
        profile_phone.setText(phone);
        profile_name.setText(fullname);
        address_profile.setText(address);
        isemployee.setChecked(checkB);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1000){
            if(resultCode== Activity.RESULT_OK){
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);

                uploadedImageToFirebase(imageUri);
            }
        }
    }

    private void uploadedImageToFirebase(Uri imageUri){
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(EditProfileActivity.this,"Image Uploaded.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.confirm_botton:

                    if(profile_email.getText().toString().isEmpty()||profile_phone.getText().toString().isEmpty()||profile_name.getText().toString().isEmpty()
                            ||address_profile.getText().toString().isEmpty())
                    {
                        Toast.makeText(EditProfileActivity.this,"One or many fieilds are empty",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    final String email = profile_email.getText().toString();
                    user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            DocumentReference docRef = fStore.collection("users").document(user.getUid());
                            docRef.update("name",profile_name.getText().toString(),
                                    "address",address_profile.getText().toString(),
                                    "phone",profile_phone.getText().toString(),
                                    "email",email).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(EditProfileActivity.this,"Profile is updated",Toast.LENGTH_SHORT).show();
                                    myStartActivity(setting.class);
                                    finish();

                                }
                            });
                            Toast.makeText(EditProfileActivity.this,"Email is changed",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


                    break;


                case R.id.change_Image:
                    Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(openGalleryIntent,1000);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    };

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}