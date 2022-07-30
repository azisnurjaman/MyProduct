package com.example.myproduct;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText email, pass;
    private Button login;
    private ProgressBar progress;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Auth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        email = (EditText) findViewById(R.id.text_email);
        pass = (EditText) findViewById(R.id.text_pass);
        login = (Button) findViewById(R.id.submitLogin);
        login.setOnClickListener(this);

        progress = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.submitLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String txt_email = email.getText().toString().trim();
        String txt_pass = pass.getText().toString().trim();

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

        Auth.signInWithEmailAndPassword(txt_email, txt_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Login successfully!",
                            Toast.LENGTH_LONG).show();
                    } else {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Login Failed, please check your credentials and try again!",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}