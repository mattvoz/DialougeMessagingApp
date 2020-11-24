package com.example.testingempty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_inActivity extends AppCompatActivity {
    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        backButton = findViewById(R.id.Backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });

    }

    private void backToMain() {
        Intent intent2 = new Intent(Sign_inActivity.this, MainActivity.class);
        startActivity(intent2);
    }
}
