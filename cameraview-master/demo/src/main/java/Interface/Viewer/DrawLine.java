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

package Interface.Viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.cameraview.demo.R;
//import com.vladislav.canvaswc.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import Interface.ChangeWidthColor;

public class DrawLine extends android.support.v7.widget.AppCompatImageView implements
        ChangeWidthColor
{

    private Path path = new Path();
    private Paint paint = new Paint();
    private int currentColor = Color.BLACK;
    private float width = 1f;
    public float x;
    public float y;

    private LinkedList<Integer> color = new LinkedList<>();
    private LinkedList<Float> widthSize = new LinkedList<>();
    private LinkedList<Path> paths = new LinkedList<>();

    DrawLine dView = (DrawLine) findViewById(R.id.drwLine);
    public DrawLine(Context context)
    {
        super(context);
        init(context);
    }
    public DrawLine(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public DrawLine(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setColor(currentColor);
        paint.setStrokeWidth(5f);
    }

    @Override
    public void changeColor(int color)
    {
        currentColor = color;
        path = new Path();
    }

    @Override
    public void changeWidth(float width)
    {
        this.width = width;
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++)
        {
            paint.setStrokeWidth(widthSize.get(i));
            paint.setColor(color.get(i));
            canvas.drawPath(paths.get(i), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                path.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                path.lineTo(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                color.add(currentColor);
                paths.add(path);
                widthSize.add(width);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void saveImage()
    {
        FileOutputStream os;
        try
        {
            dView.buildDrawingCache();
            Bitmap bm = dView.getDrawingCache();
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/saved_images");
            Toast.makeText(getContext().getApplicationContext(),myDir.toString(), Toast.LENGTH_SHORT).show();
            myDir.mkdirs();
            String filename = "pic"+ System.currentTimeMillis()+".png";
            File file = new File(myDir, filename);
            if (file.exists ()) file.delete ();
            file.createNewFile();
            os = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadImage()
    {
        try
        {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();
            String filename = "pic.png";
            File file = new File(myDir, filename);
            Bitmap bmap = BitmapFactory.decodeStream(new FileInputStream(file));
            dView.setImageBitmap(bmap);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}