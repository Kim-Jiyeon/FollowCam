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

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Viewer.DrawLine;


public class DrawActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    DrawLine dr;
    Button c_green,c_blue, c_yellow, c_pink, c_lavanda, c_orange, c_black, c_red;
    Spinner spinner;
    short execution;
    View tutorialview;
    ImageView tutorialImgview1, tutorialImgview2, tutorialImgview3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        dr = (DrawLine) findViewById(R.id.drwLine);

        c_green = (Button) findViewById(R.id.c_green);
        c_blue = (Button) findViewById(R.id.c_blue);
        c_yellow = (Button) findViewById(R.id.c_yellow);
        c_pink = (Button) findViewById(R.id.c_pink);
        c_lavanda = (Button) findViewById(R.id.c_lavanda);
        c_orange = (Button) findViewById(R.id.c_orange);
        c_black = (Button) findViewById(R.id.c_black);
        c_red = (Button) findViewById(R.id.c_red);
        spinner = (Spinner) findViewById(R.id.spinner);



        c_green.setOnClickListener(this);
        c_blue.setOnClickListener(this);
        c_yellow.setOnClickListener(this);
        c_pink.setOnClickListener(this);
        c_lavanda.setOnClickListener(this);
        c_orange.setOnClickListener(this);
        c_black.setOnClickListener(this);
        c_red.setOnClickListener(this);




        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr.saveImage();
                Toast.makeText(DrawActivity.this,"Image saved", Toast.LENGTH_SHORT).show();
            }
        });

        Button back_btn = (Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ArrayList<String> width = new ArrayList<>();
        width.add("5");
        width.add("10");
        width.add("15");
        width.add("30");
        width.add("50");
        width.add("70");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, width);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //튜토리얼 숨기기
        execution = 0;
        tutorialview = (View)findViewById(R.id.hide_01);
        tutorialImgview1 = (ImageView)findViewById(R.id.hide_02);
        tutorialImgview2 = (ImageView)findViewById(R.id.hide_03);
        tutorialImgview3 = (ImageView)findViewById(R.id.hide_04);

        tutorialview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (execution==0){
                    tutorialImgview1.setVisibility(View.GONE);
                    tutorialImgview2.setVisibility(View.VISIBLE);
                    execution=1;
                }
                else if (execution==1){
                    tutorialImgview2.setVisibility(View.GONE);
                    tutorialImgview3.setVisibility(View.VISIBLE);
                    execution=2;
                }
                else if (execution==2){
                    tutorialview.setVisibility(View.GONE);
                    tutorialImgview3.setVisibility(View.GONE);
                    execution=3;
                }
                return false;
            }
        });




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c_green:
                dr.changeColor(Color.rgb(156, 204, 101));
                break;
            case R.id.c_blue:
                dr.changeColor(Color.rgb(63, 81, 181));
                break;
            case R.id.c_yellow:
                dr.changeColor(Color.rgb(255, 255, 0));
                break;
            case R.id.c_pink:
                dr.changeColor(Color.rgb(213, 0, 249));
                break;
            case R.id.c_lavanda:
                dr.changeColor(Color.rgb(126, 87, 194));
                break;
            case R.id.c_orange:
                dr.changeColor(Color.rgb(255, 87, 34));
                break;
            case R.id.c_black:
                dr.changeColor(Color.rgb(0, 0, 0));
                break;
            case R.id.c_red:
                dr.changeColor(Color.rgb(204, 24, 18));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l)
    {
        String item = adapterView.getItemAtPosition(pos).toString();
        switch (item) {
            case "5":
                dr.changeWidth(5f);
                break;
            case "10":
                dr.changeWidth(10f);
                break;
            case "15":
                dr.changeWidth(15f);
                break;
            case "30":
                dr.changeWidth(30f);
                break;
            case "50":
                dr.changeWidth(50f);
                break;
            case "70":
                dr.changeWidth(70f);
                break;
            default:
                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

