package com.example.dialogue.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.*;
import com.example.dialogue.R;
import com.example.dialogue.logic.RegistrationLogic;
import com.example.dialogue.network.ServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
/**
 * @author Jacob Nett
 */
public class SignUp extends AppCompatActivity implements IView {
    private EditText username;
    private EditText password;
    private EditText rePassword;
    private Button sub;
    private ProgressDialog pDialog;

    /**
     * builds the screen for registering a user
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.SUser);
        password = findViewById(R.id.SPass);
        rePassword = findViewById(R.id.SPassRe);
        sub = findViewById(R.id.SignUpButton);
        //errText = findViewById(R.id.errorTextView);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        ServerRequest serverRequest = new ServerRequest();
        final RegistrationLogic registrationLogic = new RegistrationLogic(this, serverRequest);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String inputUser = username.getText().toString();
                final String inputPass = password.getText().toString();
                final String inputRePass = rePassword.getText().toString();
                boolean noError = true;

                if (TextUtils.isEmpty(inputUser)) {
                    username.setError("Please enter your username");
                    username.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(inputPass)) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(inputRePass)) {
                    rePassword.setError("Please enter your password");
                    rePassword.requestFocus();
                    return;
                }

                //check for appropriate name length
                if (username.getText().toString().length() < 5) {
                    username.setError("Username must be At least five characters");
                    return;
                }
                //checks for appropriate password length
                if (inputPass.length() < 5) {
                    password.setError("Password must be at least 5 characters");
                    return;
                }
                //confirms user did mess up password
                else if (!inputPass.equals(inputRePass)) {
                    password.setError("Passwords must match");
                    rePassword.setError("Passwords must match");
                    return;
                }
                showProgressDialog();
               registrationLogic.Register(inputUser, inputPass, inputRePass);
               hideProgressDialog();
            }
        });
    }


    public void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();

    }

    @Override
    public void toastText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();}

    @Override
    public void changeScreen(Class c) {
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    @Override
    public void setUser(String u) {

    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }


}