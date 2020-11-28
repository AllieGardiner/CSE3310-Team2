package com.example.team2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class setting extends AppCompatActivity {
    TextView name,email,phone,address;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        phone = findViewById(R.id.profile_phone);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        address = findViewById(R.id.address_profile);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                phone.setText(value.getString("phone"));
                name.setText(value.getString("name"));
                email.setText(value.getString("email"));
                address.setText(value.getString("address"));
            }
        });

        findViewById(R.id.confirm_botton).setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.confirm_botton:
                    startSignActivity(EditProfileActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    };


    private void startSignActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.putExtra("name",name.getText().toString());
        intent.putExtra("address",address.getText().toString());
        intent.putExtra("email",email.getText().toString());
        intent.putExtra("phone",phone.getText().toString());
        startActivity(intent);
    }
}
