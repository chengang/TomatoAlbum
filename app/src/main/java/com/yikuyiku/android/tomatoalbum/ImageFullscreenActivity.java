package com.yikuyiku.android.tomatoalbum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class ImageFullscreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fullscreen_layout);

        Intent intent = getIntent();
        String mediaType = intent.getStringExtra("mediaType");
        int pos = intent.getIntExtra("pos", 0);
        imageView = (ImageView) findViewById(R.id.fullscreen_image);

        String imageUri = MediaLibrary.getSystemImages().get(pos).getUri();
        Glide.with(this).load(imageUri).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
