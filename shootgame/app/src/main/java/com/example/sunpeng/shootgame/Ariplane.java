package com.example.sunpeng.shootgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sunpeng on 15-12-10.
 */
public class Ariplane extends View {
    float currentX;
    float currentY;
    double speed=5;
    Bitmap plane;
    public  Ariplane(Context context){
        super(context);
        plane= BitmapFactory.decodeResource(context.getResources(),R.drawable.hero0);
        setFocusable(true);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(plane,currentX,currentY,p);
    }
}
