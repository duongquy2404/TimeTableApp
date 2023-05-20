package com.duongnq.timetableapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister,btnCancel;
    EditText txtemail,txtpassword;
    protected FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        mFirebaseAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.createUserWithEmailAndPassword(txtemail.getText().toString(),txtpassword.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(RegisterActivity.this,
                                        "Register Success", Toast.LENGTH_SHORT).show();
                                final Intent data = new Intent();
                                // Truyền data vào intent
                                data.putExtra("email", txtemail.getText().toString());
                                data.putExtra("pass", txtpassword.getText().toString());
                                // Đặt resultCode là Activity.RESULT_OK
                                setResult(Activity.RESULT_OK, data);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        btnRegister=findViewById(R.id.btnRegister);
        btnCancel=findViewById(R.id.btnCancelRegister);
        txtemail=findViewById(R.id.inputEmailRegister);
        txtpassword=findViewById(R.id.inputPasswordRegister);
    }
}