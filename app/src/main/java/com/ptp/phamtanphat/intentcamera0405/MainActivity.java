package com.ptp.phamtanphat.intentcamera0405;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btnCamera,btnGallery;
    int Request_Code_Camera = 123;
    int Request_Code_Gallery = 234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageview);
        btnCamera = findViewById(R.id.buttoncamera);
        btnGallery = findViewById(R.id.buttongallery);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Request_Code_Camera);
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_Code_Gallery);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Request_Code_Camera && resultCode == RESULT_OK ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if (requestCode == Request_Code_Gallery && resultCode == RESULT_OK){
            Uri uri = data.getData();

            try {
                // Lay du lieu duong dan va chuyen ve kieu Inputsteam de co the thay doi
//                kieu du lieu
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
