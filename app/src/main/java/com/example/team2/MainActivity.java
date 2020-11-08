package com.example.team2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    if(FirebaseAuth.getInstance().getCurrentUser()==null){
        startSignActivity();
    }

    findViewById(R.id.Logoutbutton).setOnClickListener(onclickListner);
    }

    View.OnClickListener onclickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Logoutbutton:
                    FirebaseAuth.getInstance().signOut();
                    startSignActivity();
                    break;

            }
        }
    };


    private void startSignActivity(){
        Intent intent = new Intent(this, SingupActivity.class);
        startActivity(intent);
    }
    private void sport(){
        Intent intent = new Intent(this, SportsTrainingFragment.class);
        startActivity(intent);
    }
}