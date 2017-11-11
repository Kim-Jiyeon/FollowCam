package kr.hs.emirim.gjy00727.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView Imgview01 = (ImageView)findViewById(R.id.imgview01);
        ImageView Imgview02 = (ImageView)findViewById(R.id.imgview02);
        ImageView Imgview03 = (ImageView)findViewById(R.id.imgview03);
        ImageView Imgview04 = (ImageView)findViewById(R.id.imgview04);
        ImageView Imgview05 = (ImageView)findViewById(R.id.imgview05);
        ImageView Imgview06 = (ImageView)findViewById(R.id.imgview06);

        final ImageView showPose = (ImageView)findViewById(R.id.showpose);
        Imgview01.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showPose.setImageResource(R.drawable.o_stuff_1);
            }
        });
    }

}
