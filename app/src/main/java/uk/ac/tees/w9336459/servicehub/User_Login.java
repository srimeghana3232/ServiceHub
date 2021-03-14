package uk.ac.tees.w9336459.servicehub;

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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class User_Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

            mEmail = findViewById(R.id.U_L_loginid);
            mPassword = findViewById(R.id.U_L_passwordenter);
            progressBar = findViewById(R.id.progressBar);
            fAuth = FirebaseAuth.getInstance();
            mLoginBtn = findViewById(R.id.U_L_LoginInbt);

            mLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        mEmail.setError("Email is Required.");
                        return;
                    }

                    if(TextUtils.isEmpty(password)){
                        mPassword.setError("Password is Required.");
                        return;
                    }

                    if(password.length() < 8){
                        mPassword.setError("Password Must be >= 6 Characters");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(User_Login.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Intent intent = new Intent(User_Login.this, U_MainScreen.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(User_Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });

                }
            });

        }
    }