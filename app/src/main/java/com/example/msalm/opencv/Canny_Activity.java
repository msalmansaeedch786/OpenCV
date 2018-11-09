package com.example.msalm.opencv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Canny_Activity extends AppCompatActivity {

    Button btn;
    Bitmap b, bm;
    ImageView img;
    Mat mRgba, mGray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canny_);

        img = findViewById(R.id.imageView);

        Intent intent = getIntent();
        final String path = (String) intent.getStringExtra("ImagePath");

        try {
            File f = new File(path, "canny.jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        btn = (Button) findViewById(R.id.goF);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRgba = new Mat(b.getHeight(), b.getWidth(), CvType.CV_8UC4);
                mGray = new Mat(b.getHeight(), b.getWidth(), CvType.CV_8UC1);
                Utils.bitmapToMat(b, mRgba);
                Imgproc.cvtColor(mRgba, mGray, Imgproc.COLOR_RGB2GRAY);
                bm = Bitmap.createBitmap(mGray.cols(), mGray.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(mGray, bm);
                img.setImageBitmap(bm);
            }
        });
    }
}