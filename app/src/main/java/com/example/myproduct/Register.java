package com.example.myproduct;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText userName, email, pass;
    private Button submit;
    private FirebaseAuth Auth;
    private ProgressBar progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Auth = FirebaseAuth.getInstance();

        userName = (EditText) findViewById(R.id.text_userName);
        email = (EditText) findViewById(R.id.text_email);
        pass = (EditText) findViewById(R.id.text_pass);

        submit = (Button) findViewById(R.id.submitRegister);
        submit.setOnClickListener(this);

        progress = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitRegister:
                register();
                break;
        }
    }

    private void register() {
        String txt_userName = userName.getText().toString().trim();
        String txt_email = email.getText().toString().trim();
        String txt_pass = pass.getText().toString().trim();

        if (txt_userName.isEmpty()){
            userName.setError("User Name is required!");
            userName.requestFocus();
            return;
        }
        if (txt_email.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()){
            email.setError("Invalid email!");
            email.requestFocus();
            return;
        }
        if (txt_pass.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        Auth.createUserWithEmailAndPassword(txt_email, txt_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(txt_userName, txt_email, txt_pass);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progress.setVisibility(View.GONE);
                                onBackPressed();
                                Toast.makeText(Register.this, "User has been registered successfully!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                progress.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Failed to register, try again!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Email already exists!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}