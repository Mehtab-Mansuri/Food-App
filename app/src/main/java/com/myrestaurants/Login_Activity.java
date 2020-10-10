package com.myrestaurants;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    EditText Email,Password;
    Button Login;
    FirebaseAuth fAuth;
    TextView NewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Sign in");

        Email=findViewById(R.id.lEmail);
        Password=findViewById(R.id.lPassword);
        Login=findViewById(R.id.lSignin);
        fAuth=FirebaseAuth.getInstance();
        NewUser=findViewById(R.id.tsignup);

        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Register_form.class);
                startActivity(intent);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lEmail = Email.getText().toString().trim();
                String lPassword = Password.getText().toString().trim();

                if (TextUtils.isEmpty(lEmail)) {
                    Email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(lPassword)) {
                    Password.setError("password is Required");
                    return;
                }
                if (lPassword.length() < 6) {
                    Password.setError("password must be >=6");
                    return;
                }

                /*progressBar.setVisibility(View.VISIBLE);*/


                //authenticate the user
                fAuth.signInWithEmailAndPassword(lEmail,lPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        }else{
                            Toast.makeText(Login_Activity.this, "Error !!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }



                    }
                });
            }
        });




    }
}










