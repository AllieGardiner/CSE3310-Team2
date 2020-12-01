package com.example.team2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pw);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.reset_pw_button).setOnClickListener(onClickListener);
    }

    private void startLoginActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.reset_pw_button:
                    send();
                    startLoginActivity(LoginActivity.class);

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    };


    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }


    private void send(){
        String email = ((EditText)findViewById(R.id.LoginID)).getText().toString();

        if(email.length()>0){

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("Email sent.");
                                finish();
                            }
                            else {
                                if(task.getException()!=null){
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }
        else
        {
            startToast( "Please enter Email");
        }
    }

}
