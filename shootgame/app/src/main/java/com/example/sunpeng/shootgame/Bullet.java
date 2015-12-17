package com.example.sunpeng.shootgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sunpeng on 15-12-15.
 */
public class Bullet extends View {
    float currentX;
    float currentY;
    double speed=10;
    Bitmap bullet;
    Bullet(Context context){
        super(context);
        bullet= BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet);
        setFocusable(true);
    }
    Bullet(Context context,Airplane ariplane){
        super(context);
        bullet= BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet);
        this.currentX=ariplane.currentX+ariplane.plane.getWidth()/2;
        this.currentY=ariplane.currentY;
        setFocusable(true);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(bullet,currentX,currentY,p);
    }
    public void move(){
        currentY-=speed;
        this.invalidate();
    }
}
