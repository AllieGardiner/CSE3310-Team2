package com.example.team2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity
{
    EditText profile_email,profile_phone,profile_name,address_profile;
    ImageView profileImage;
    Button save_button;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;


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

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();


        findViewById(R.id.confirm_botton).setOnClickListener(onClickListener);

        profileImage = findViewById(R.id.profile_image);
        profile_email=findViewById(R.id.profile_email);
        profile_phone=findViewById(R.id.profile_phone);
        profile_name=findViewById(R.id.profile_name);
        address_profile=findViewById(R.id.address_profile);



        profile_email.setText(email);
        profile_phone.setText(phone);
        profile_name.setText(fullname);
        address_profile.setText(address);

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



                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    };




}