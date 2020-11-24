package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean x = true;
    private ImageView imageViewPiece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewPiece = findViewById(R.id.imageView1);
    }

    public void xOrO1(View v){
        imageViewPiece = findViewById(R.id.imageView1);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
   public void xOrO2(View v){
        imageViewPiece = findViewById(R.id.imageView2);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;

        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
    public void xOrO3(View v){
        imageViewPiece = findViewById(R.id.imageView3);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }

    }
    public void xOrO4(View v){
        imageViewPiece = findViewById(R.id.imageView4);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }

    }
    public void xOrO5(View v){
        imageViewPiece = findViewById(R.id.imageView5);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }

    }
    public void xOrO6(View v){
        imageViewPiece = findViewById(R.id.imageView6);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
    public void xOrO7(View v){
        imageViewPiece = findViewById(R.id.imageView7);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
    public void xOrO8(View v){
        imageViewPiece = findViewById(R.id.imageView8);
        if(x == true){
        imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
    public void xOrO9(View v){
    imageViewPiece = findViewById(R.id.imageView9);
        if(x == true){
            imageViewPiece.setImageResource(R.drawable.x);
            x = false;
        }
        else {
            imageViewPiece.setImageResource(R.drawable.o);
            x = true;
        }
    }
    public void clear(View v){
        for(int i = 1; i <= 9; i++){
            switch(i){
                case 1: imageViewPiece = findViewById(R.id.imageView1);
                        break;
                case 2: imageViewPiece = findViewById(R.id.imageView2);
                    break;
                case 3: imageViewPiece = findViewById(R.id.imageView3);
                    break;
                case 4: imageViewPiece = findViewById(R.id.imageView4);
                    break;
                case 5: imageViewPiece = findViewById(R.id.imageView5);
                    break;
                case 6: imageViewPiece = findViewById(R.id.imageView6);
                    break;
                case 7: imageViewPiece = findViewById(R.id.imageView7);
                    break;
                case 8: imageViewPiece = findViewById(R.id.imageView8);
                    break;
                case 9: imageViewPiece = findViewById(R.id.imageView9);
                    break;
            }
            imageViewPiece.setImageResource(0);
        }



    }
}