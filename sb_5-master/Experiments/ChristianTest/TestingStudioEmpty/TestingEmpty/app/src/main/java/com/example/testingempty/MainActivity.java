package com.example.testingempty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_in_button = findViewById(R.id.sign_in_button);
       sign_in_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              moveToSign_In();
           }
       });

        }

        private void moveToSign_In(){
        Intent intent2 = new Intent(MainActivity.this, Sign_inActivity.class);
            startActivity(intent2);

    }
}