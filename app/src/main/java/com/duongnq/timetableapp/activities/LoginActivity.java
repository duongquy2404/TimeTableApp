package com.duongnq.timetableapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnq.timetableapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_REGISTER=10000;
    Button btnLogin;
    EditText txtemail,txtpassword;
    TextView resetPassword,register;
    protected FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        mFirebaseAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signInWithEmailAndPassword(
                                txtemail.getText().toString(),txtpassword.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,
                                        "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mFirebaseAuth.sendPasswordResetEmail(txtemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(LoginActivity.this, "An email has been sent to you.", Toast.LENGTH_LONG).show();
//                        } else {
//
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
                mFirebaseAuth.sendPasswordResetEmail(txtemail.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this,"Vui lòng kiểm tra Email!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivityForResult(intent,REQUEST_CODE_REGISTER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_REGISTER) {
            if(resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                final String email = data.getStringExtra("email");
                final String password = data.getStringExtra("pass");
                //Set lại giá trị cho txtEmail and password
                txtemail.setText(email);
                txtpassword.setText(password);
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }

    private void initView() {
        btnLogin=findViewById(R.id.btnLogin);
        txtemail=findViewById(R.id.inputEmail);
        txtpassword=findViewById(R.id.inputPassword);
        resetPassword=findViewById(R.id.forgotPassword);
        register=findViewById(R.id.gotoRegister);
    }
}