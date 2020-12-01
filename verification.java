package com.example.team2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class verification extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    EditText code;
    String Code = "123456";





    boolean verified ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        findViewById(R.id.nextBtn).setOnClickListener(onClickListener);

        code = findViewById(R.id.code_num);



    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.nextBtn:
                    if (!code.getText().toString().isEmpty() && code.getText().toString().length() == 6) {

                        if(code.getText().toString().equals(Code)){
                            Toast.makeText(verification.this, "Code is verified",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("verify",true);
                            setResult(Activity.RESULT_OK,intent);


                        }
                        else{
                            Toast.makeText(verification.this, "Invalid Code",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("verify",false);
                            setResult(Activity.RESULT_OK,intent);


                        }
                    }
                    else {
                        Toast.makeText(verification.this, "Invalid Code",Toast.LENGTH_SHORT).show();

                    }
                    finish();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    };

    public static Intent makeIntent (Context context){
        return new Intent(context, verification.class);
    }


}