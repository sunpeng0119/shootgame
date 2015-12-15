package com.example.sunpeng.shootgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sunpeng on 15-12-11.
 */
public class EnemyPlane extends View{
    float currentX;
    float currentY;
    Bitmap enmyplane;
    public EnemyPlane(Context context){
        super(context);
        enmyplane= BitmapFactory.decodeResource(context.getResources(),R.drawable.airplane);
        setFocusable(true);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(enmyplane,currentX,currentY,p);
    }
}
