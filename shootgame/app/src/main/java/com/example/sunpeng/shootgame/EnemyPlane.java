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
 * Created by sunpeng on 15-12-11.
 */
public class EnemyPlane extends View{
    float currentX;
    float currentY;
    int speedX=2;
    int speedY=3;
    int doublefrie=0;
    Bitmap enmyplane;
    public EnemyPlane(Context context){
        super(context);
        enmyplane= BitmapFactory.decodeResource(context.getResources(),R.drawable.airplane);
        setFocusable(true);
    }
    public  EnemyPlane(Context context,int currentX){
        super(context);
        enmyplane=BitmapFactory.decodeResource(context.getResources(),R.drawable.airplane);
        setFocusable(true);
        this.currentX=currentX;
        if(Math.random()*2>1){
            this.speedX=-speedX;
        }
    }
    public  EnemyPlane(Context context,int currentX,int doublefrie){
        super(context);
        enmyplane=BitmapFactory.decodeResource(context.getResources(),R.drawable.bee);
        setFocusable(true);
        this.currentX=currentX;
        if(Math.random()*2>1){
            this.speedX=-speedX;
        }
        this.doublefrie=doublefrie;

    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(enmyplane, currentX, currentY, p);
    }
    public void move(){
        this.currentY+=this.speedY;
        this.currentX+=this.speedX;
        this.invalidate();
    }
    public static void hit(List<EnemyPlane> enemyPlaneList , Airplane airplane,RelativeLayout root){
        //List<EnemyPlane> planes=new ArrayList<>();
        for(int i=0;i<enemyPlaneList.size();i++){
            if((enemyPlaneList.get(i).currentX>=airplane.currentX&&
            enemyPlaneList.get(i).currentX<=airplane.currentX+airplane.plane.getWidth())&&(
                    enemyPlaneList.get(i).currentY>=airplane.currentY&&
                            enemyPlaneList.get(i).currentY<=airplane.currentY+airplane.plane.getHeight()
                    )){
                //planes.add(enemyPlaneList.get(i));

                root.removeView(enemyPlaneList.get(i));
                enemyPlaneList.remove(i--);
                airplane.life--;
            }
        }
        //enemyPlaneList.removeAll(planes);
    }
    public static void enterEnemy(int time,List<EnemyPlane> enemyPlaneList,RelativeLayout root,Activity mainactivity){

        if (time % 30 == 0) {
            EnemyPlane temp = new EnemyPlane(mainactivity, (int) (Math.random() * (root.getWidth())));
            if(temp.currentX>root.getWidth()-temp.enmyplane.getWidth()){
                temp.currentX=temp.currentX-temp.enmyplane.getWidth();
            }
            enemyPlaneList.add(0, temp);
            root.addView(enemyPlaneList.get(0));
        }
        if(time%100==0){
            EnemyPlane temp = new EnemyPlane(mainactivity, (int) (Math.random() * root.getWidth()),50);
            if(temp.currentX>root.getWidth()-temp.enmyplane.getWidth()){
                temp.currentX=temp.currentX-temp.enmyplane.getWidth();}
            enemyPlaneList.add(0, temp);
            root.addView(enemyPlaneList.get(0));
        }
        time++;
        for (int i = 0; i < enemyPlaneList.size(); i++) {
            if (enemyPlaneList.get(i).currentX > root.getWidth()-enemyPlaneList.get(i).enmyplane.getWidth()) {
                enemyPlaneList.get(i).speedX = -enemyPlaneList.get(i).speedX;
            }
            if (enemyPlaneList.get(i).currentX < 0) {
                enemyPlaneList.get(i).speedX = -enemyPlaneList.get(i).speedX;
            }
            enemyPlaneList.get(i).move();
        }
        for (int i = 0; i < enemyPlaneList.size(); i++) {
            if (enemyPlaneList.get(i).currentY > root.getHeight()) {
                root.removeView(enemyPlaneList.get(i));
                enemyPlaneList.remove(i--);

            }
        }
    }
}
