package com.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;
   /* TextView skp;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Transperent Actionbar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getSupportActionBar().hide();

       login=findViewById(R.id.btnlogin);
        signup=findViewById(R.id.btnsignup);
        /*skp=findViewById(R.id.tskp);*/






        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login has been clicked", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(MainActivity.this,Login_Activity.class);
                startActivity(intent);


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Registe has been clicked", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(MainActivity.this,Register_form.class);
                startActivity(intent);
            }
        });

    }
}