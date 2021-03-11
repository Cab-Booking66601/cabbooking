package com.example.cabbooking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.firebase.ui.auth.IdpResponse.fromResultIntent;

public class Login extends AppCompatActivity {

    List<AuthUI.IdpConfig> providers;
    private int MY_REQUEST_CODE = 2332;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init
        providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());  //Email Login

        showSignInOption();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            IdpResponse response = fromResultIntent(data);

            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Toast.makeText(this, "Please Enter Email id", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } else {
                if (resultCode == Activity.RESULT_OK) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  //   get current user.
                    Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Home.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, " Please Enter email id" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                    //Intent(this, SignUp_activity::class.java)
                }
            }
        }
    }

    private void showSignInOption() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build(), MY_REQUEST_CODE);
    }
}