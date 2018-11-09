package com.example.msalm.opencv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Canny_Activity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canny_);

        Intent intent = getIntent();
        String path = (String) intent.getStringExtra("ImagePath");
        try {
            File f = new File(path, "canny.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            img = findViewById(R.id.imageView);
            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}