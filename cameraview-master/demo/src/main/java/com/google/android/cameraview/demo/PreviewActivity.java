/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.cameraview.demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by USER on 2017-11-09.
 */

public class PreviewActivity extends AppCompatActivity {

    private ImageView previewImage;
    private String filename;
    private ImageView saveImage;
    private boolean flag_save=false;
    private Uri uri;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        previewImage = (ImageView) findViewById(R.id.preview_img);
        saveImage=(ImageView)findViewById(R.id.save_img);

        Intent intent=getIntent();
        filename=intent.getExtras().getString("filename");
        if(filename.equals("")){Toast.makeText(this,"없넹",Toast.LENGTH_LONG).show();}
        Toast.makeText(this,filename,Toast.LENGTH_SHORT).show();
        uri = Uri.parse("file://"+filename);

        try
        {
            // 비트맵 이미지로 가져온다
            String imagePath = uri.getPath();
            Bitmap image = BitmapFactory.decodeFile(imagePath);

            // 이미지를 상황에 맞게 회전시킨다
            ExifInterface exif = new ExifInterface(imagePath);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int exifDegree = exifOrientationToDegrees(exifOrientation);
            image = rotate(image, exifDegree);

            // 변환된 이미지 사용
            previewImage.setImageBitmap(image);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "오류발생: " + e.getLocalizedMessage(),
                Toast.LENGTH_LONG).show();
        }

        saveImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"save image",Toast.LENGTH_SHORT).show();
                flag_save=true;
            }
        });
    }
    public int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){ return 90;}
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){return 180;}
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){return 270;}
        return 0;
    }

    public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null){
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);
            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted){
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex){

                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }
    public void onBackPressed() {

        if(flag_save==true){
            Toast.makeText(getApplicationContext(),"save it!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"delete it!!",Toast.LENGTH_SHORT).show();
            File file =new File(filename);
            file.delete();

            //파일삭제 후 갤러리 썸네일 남는 현상 해결
            ContentResolver resolver = getContentResolver();
            Uri resolver_uri  = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Images.Media.DATA + " = ?";
            String[] selectionArgs = {filename}; // 실제 파일의 경로
            resolver.delete(resolver_uri, selection,selectionArgs);
        }
        super.onBackPressed();
    }


}



