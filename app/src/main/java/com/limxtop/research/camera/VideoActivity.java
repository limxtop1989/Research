package com.limxtop.research.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.VideoView;

import com.limxtop.research.R;


/**
 * TODO: The video can't play automatically.
 */
public class VideoActivity extends AppCompatActivity implements View.OnClickListener,
        MediaPlayer.OnPreparedListener {

    private final int takeVideoRequestCode = 0x01;

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findViewById(R.id.take_video).setOnClickListener(this);
        mVideoView = findViewById(R.id.video_view);
        mVideoView.setOnPreparedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void takeVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) == null) {
            return;
        }

        startActivityForResult(intent, takeVideoRequestCode);
    }


    @Override
    public void onClick(View v) {
        takeVideo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case takeVideoRequestCode:
                Uri uri = getIntent().getData();
                mVideoView.setVideoURI(uri);
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}



