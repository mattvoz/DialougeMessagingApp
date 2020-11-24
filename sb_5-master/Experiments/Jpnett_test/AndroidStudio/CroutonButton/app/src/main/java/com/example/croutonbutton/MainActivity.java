package com.example.croutonbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean show = false;
    private ImageView imageViewCrouton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewCrouton = findViewById(R.id.image_view_crouton);
    }
    public void crouton(View v){
        if(show == false){
            imageViewCrouton.setImageResource(R.drawable.crouton);
            show = true;
        }
        else {
            imageViewCrouton.setImageResource(0);
            show = false;
        }
    }

}