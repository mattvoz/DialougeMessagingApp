package com.example.dialogue.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.logic.LoginLogic;
import com.example.dialogue.network.ServerRequest;
/**
 * @author Jacob Nett
 */

public class Login extends AppCompatActivity implements IView {


    private EditText eTname;
    private EditText eTpassword;
    private Button loginB;
    private TextView tVsignup;
    private ProgressDialog pDialog;

    /**
     * builds the screen for logging in
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eTname = findViewById(R.id.Username);
        eTpassword = findViewById(R.id.Password);
        loginB = findViewById(R.id.loginButton);
        tVsignup = findViewById(R.id.EnterSignUp);

        //test = findViewById(R.id.tVtest);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);


        tVsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this , SignUp.class);
                startActivity(intent);
            }
        });
        ServerRequest serverRequest = new ServerRequest();
        final LoginLogic loginLogic = new LoginLogic(this , serverRequest);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = eTname.getText().toString();
                final String password = eTpassword.getText().toString();
                setUser(username);                //validating inputs
                if (TextUtils.isEmpty(username)) {
                    eTname.setError("Please enter your username");
                    eTname.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    eTpassword.setError("Please enter your password");
                    eTpassword.requestFocus();
                    return;
                }
                showProgressDialog();
                loginLogic.Login(username , password);
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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    public void changeScreen(Class c){
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    @Override
    public void setUser(String u) {
        AppController.getInstance().setUser(u);
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }


}
