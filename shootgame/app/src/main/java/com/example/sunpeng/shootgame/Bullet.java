package com.example.sunpeng.shootgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by sunpeng on 15-12-15.
 */
public class Bullet extends View {
    float currentX;
    float currentY;
    double speed=50;
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
    Bullet(Context context,Airplane ariplane,int dx){
        super(context);
        bullet= BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet);
        this.currentX=ariplane.currentX+ariplane.plane.getWidth()/2-(float)dx;
        this.currentY=ariplane.currentY;
        setFocusable(true);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(bullet, currentX, currentY, p);
    }
    public void move(){
        currentY-=speed;
        this.invalidate();
    }
    public static int hit(List<EnemyPlane> enemyPlaneList,List<Bullet> bulletList,RelativeLayout root,Airplane airplane){
        for(int i=0;i<bulletList.size();i++){
            for(int k=0;k<enemyPlaneList.size();k++){
                if((bulletList.get(i).currentX<=enemyPlaneList.get(k).currentX+enemyPlaneList.get(k).enmyplane.getWidth()
                &&bulletList.get(i).currentX>=enemyPlaneList.get(k).currentX)&&(
                        bulletList.get(i).currentY<=enemyPlaneList.get(k).currentY+enemyPlaneList.get(k).enmyplane.getHeight()&&
                                bulletList.get(i).currentY>=enemyPlaneList.get(k).currentY
                        )){
                    airplane.doublefire+=enemyPlaneList.get(k).doublefrie;
                    root.removeView(bulletList.get(i));
                    root.removeView(enemyPlaneList.get(k));
                    bulletList.remove(i);
                    enemyPlaneList.remove(k);
                    return 5;

                }
            }
        }
        return 0;
    }
}
