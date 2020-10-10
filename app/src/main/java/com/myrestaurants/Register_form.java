package com.myrestaurants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_form extends AppCompatActivity {
    TextView Already;
    EditText mName,mNumber,mEmail,mPassword;
    Button mRegister;
    private FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        getSupportActionBar().setTitle("Sign Up");

        Already = findViewById(R.id.tlogin);
        mName = findViewById(R.id.regName);
        mNumber = findViewById(R.id.regNumber);
        mEmail = findViewById(R.id.regEmail);
        mPassword = findViewById(R.id.regPassword);
        mRegister = findViewById(R.id.regRegister);





                fAuth = FirebaseAuth.getInstance();
                progressBar = findViewById(R.id.progressBar);

                if (fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    finish();

                }



                Already.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Register_form.this, Login_Activity.class);
                        startActivity(intent);
                    }
                });

                mRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String regEmail=mEmail.getText().toString().trim();
                        String regPassword=mPassword.getText().toString().trim();

                        if(TextUtils.isEmpty(regEmail)){
                            mEmail.setError("Email is Required");
                            return;
                        }
                        if(TextUtils.isEmpty(regPassword)){
                            mPassword.setError("password is Required");
                            return;
                        }
                        if(regPassword.length()< 6){
                            mPassword.setError("password must be >=6");
                            return;
                        }

                        progressBar.setVisibility(View.VISIBLE);

                        //register the user in firebase

                        fAuth.createUserWithEmailAndPassword(regEmail,regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Register_form.this, "User Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Dashboard.class));

                                }else{
                                    Toast.makeText(Register_form.this, "Error !!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                                }

                        /*Toast.makeText(Register_form.this, "Register the user", Toast.LENGTH_SHORT).show();*/
                    }
                });

            }


           /*  Already.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Register_form.this, Login_Activity.class);
            startActivity(intent);

        });*/
    });
    }
}




