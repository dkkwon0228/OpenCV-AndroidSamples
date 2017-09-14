package com.jnardari.opencv_androidsamples.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jnardari.opencv_androidsamples.R;

public class SamplesActivity extends AppCompatActivity {

    Intent sampleIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);

        // First check android version
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            //Check if permission is already granted
            //thisActivity is your activity. (e.g.: MainActivity.this)
            if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

                // Give first an explanation, if needed.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
                }
            }
        }
    }

    public void tutorial1(View v) {
        sampleIntent = new Intent(this, Tutorial1Activity.class);
        startActivity(sampleIntent);
    }

    public void tutorial2(View v) {
        sampleIntent = new Intent(this, Tutorial2Activity.class);
        startActivity(sampleIntent);
    }

    public void tutorial3(View v) {
        sampleIntent = new Intent(this, Tutorial3Activity.class);
        startActivity(sampleIntent);
    }

    public void imageManipulations(View v) {
        sampleIntent = new Intent(this, ImageManipulationsActivity.class);
        startActivity(sampleIntent);
    }
    public void Hough(View v) {
        sampleIntent = new Intent(this, com.jnardari.opencv_androidsamples.activities.hough.HoughActivity.class);
        startActivity(sampleIntent);
    }

    public void faceDetection(View v) {
        sampleIntent = new Intent(this, FaceDetectionActivity.class);
        startActivity(sampleIntent);
    }

    public void colorBlobDetection(View v) {
        sampleIntent = new Intent(this, ColorBlobDetectionActivity.class);
        startActivity(sampleIntent);
    }

    public void cameraCalibration(View v) {
        sampleIntent = new Intent(this, CameraCalibrationActivity.class);
        startActivity(sampleIntent);
    }

    public void puzzle15(View v) {
        sampleIntent = new Intent(this, Puzzle15Activity.class);
        startActivity(sampleIntent);
    }
}
