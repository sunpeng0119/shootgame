package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by sunpeng on 15-12-10.
 */
public class Airplane extends View {
    float currentX;
    float currentY;
    double speed=5;
    int life=3;
    int doublefire=100;
    Bitmap planes[]=new Bitmap[2];
    Bitmap plane=planes[0];
    public  Airplane(Context context){
        super(context);
        planes[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.hero0);
        planes[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.hero1);
        setFocusable(true);
        plane=planes[0];
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(plane, currentX, currentY, p);

    }
    public void freshen(int n){
        int m=n%2;
        plane=planes[m];
        this.invalidate();
    }
    public static void fire(Airplane airplane,List<Bullet> bulletList,RelativeLayout root,Activity mainactivity){
        if(airplane.doublefire==0){
            Bullet bulletTemp = new Bullet(mainactivity, airplane);
            bulletList.add(0, bulletTemp);
            root.addView(bulletList.get(0));}else{
            int dx=100;
            Bullet bulletTemp1=new Bullet(mainactivity,airplane,dx);
            bulletList.add(0, bulletTemp1);
            root.addView(bulletList.get(0));
            Bullet bulletTemp2=new Bullet(mainactivity,airplane,-dx);
            bulletList.add(0, bulletTemp2);
            root.addView(bulletList.get(0));
            airplane.doublefire-=2;
        }
        for (int k = 0; k < bulletList.size(); k++) {
            bulletList.get(k).move();
        }
        for (int k = 0; k < bulletList.size(); k++) {
            if (bulletList.get(k).currentY < 0) {
                root.removeView(bulletList.get(k));
                bulletList.remove(k--);

            }
        }

    }

}
