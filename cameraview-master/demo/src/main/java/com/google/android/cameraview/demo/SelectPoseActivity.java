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

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 김윤영 on 2017-11-09.
 */

public class SelectPoseActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView showPose;
    ImageView imgV[]=new ImageView[6];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose);

        showPose = (ImageView) findViewById(R.id.imageView);

        imgV[0] = (ImageView) findViewById(R.id.imgview01);
        imgV[1] = (ImageView) findViewById(R.id.imgview02);
        imgV[2] = (ImageView) findViewById(R.id.imgview03);
        imgV[3] = (ImageView) findViewById(R.id.imgview04);
        imgV[4] = (ImageView) findViewById(R.id.imgview05);
        imgV[5] = (ImageView) findViewById(R.id.imgview06);

        for(int i = 0 ; i < 6; i++){
            imgV[i].setOnClickListener(this);
        }

    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
//        showPose.setBackground(v.getBackground());
    }
}
