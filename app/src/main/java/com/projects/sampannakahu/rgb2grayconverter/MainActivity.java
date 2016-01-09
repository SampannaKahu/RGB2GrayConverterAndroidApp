package com.projects.sampannakahu.rgb2grayconverter;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int randomActivityCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button captureButton = (Button) findViewById(R.id.capture_button);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                    Context context = getApplicationContext();
                    AlertDialog alertDialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setMessage("No camera found in this device!")
                            .setCancelable(true)
                            .setTitle("No camera!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    alertDialog.show();
                } else {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    randomActivityCode = new Random().nextInt(1000) + 1;
                    startActivityForResult(intent, randomActivityCode);
                }

            }
        });

        final Button convertButton = (Button) findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                if (imageView.getDrawable().equals(R.mipmap.default_camera_image_2)) {
                    Context context = getApplicationContext();
                    AlertDialog alertDialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setMessage("No image selected for conversion!")
                            .setCancelable(true)
                            .setTitle("No image!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    alertDialog.show();
                } else {
                    Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    Bitmap grayBitmap = convertToGrayscale(imageBitmap);
                    imageView.setImageBitmap(grayBitmap);
                }
            }
        });

        final Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(R.mipmap.default_camera_image_2);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==randomActivityCode && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(bp);
        }

    }

    private Bitmap convertToGrayscale(Bitmap inputBitmap) {
        Bitmap outputBitmap = inputBitmap.copy(inputBitmap.getConfig(), true);
        int width = inputBitmap.getWidth();
        int height  = inputBitmap.getHeight();
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                int color = inputBitmap.getPixel(x, y);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                double weightedAvg = red*0.299 + green*0.587 + blue*0.114;
                outputBitmap.setPixel(x, y, Color.rgb((int)weightedAvg,(int)weightedAvg,(int)weightedAvg));
            }
        }
        return outputBitmap;
    }

}
