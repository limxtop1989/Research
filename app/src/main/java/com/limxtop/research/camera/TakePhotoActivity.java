package com.limxtop.research.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.limxtop.research.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private final int thumbnailRequestCode = 0x01;
    private final int fullSizeRequestCode = 0x02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        Button takePhoto = findViewById(R.id.take_photo_thumbnail);
        takePhoto.setOnClickListener(this);
        takePhoto = findViewById(R.id.take_photo_full_size);
        takePhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) == null) {
            return;
        }
        switch(id) {
            case R.id.take_photo_thumbnail:
                startActivityForResult(intent, thumbnailRequestCode);
                break;
            case R.id.take_photo_full_size:
                // Create the file where the photo should save.
                File file = null;
                try {
                    file = createImageFile();
                } catch (IOException e) {
                    break;
                }

                Uri uri = FileProvider.getUriForFile(this,
                        "com.limxtop.research.fileprovider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(intent, fullSizeRequestCode);
        }

    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // The file is private, other applications can't access. Note that the directory will remove
        // once the application is uninstalled.
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",  /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case thumbnailRequestCode:
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                ((ImageView)findViewById(R.id.photo)).setImageBitmap(bitmap);
                break;
            case fullSizeRequestCode:
                updateImageView();
                break;

        }
    }

    private void updateImageView() {
        // Get the dimensions of the View
        ImageView imageView = findViewById(R.id.photo);
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }
}
