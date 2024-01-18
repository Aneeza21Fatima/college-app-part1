package com.example.part1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class uploadNotice extends AppCompatActivity {

    private ImageView preview_image;
    private ImageView noticeImageView;
    private EditText noticeTitle;
    private Button upload_notice_button;
    private final int REQ = 1;
    private Bitmap bit_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        setContentView(R.layout.activity_upload_notice);

        preview_image = findViewById(R.id.noticeImage);

        noticeImageView = findViewById(R.id.uploadImage);

        noticeTitle = findViewById(R.id.notice_heading);
        upload_notice_button = findViewById(R.id.upload_noticeButton);

        noticeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }

        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bit_map = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            preview_image.setImageBitmap(bit_map);

        }
    }
}