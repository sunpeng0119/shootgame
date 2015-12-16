package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    EnemyPlane enemyPlane;
    List<EnemyPlane> enemyPlaneList;
    int time;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final RelativeLayout root=(RelativeLayout)findViewById(R.id.root);
        enemyPlane=new EnemyPlane(this);
        enemyPlaneList=new LinkedList<EnemyPlane>();
        enemyPlaneList.add(0,enemyPlane);
        num=1;
        time=0;
        Timer t1=new Timer();
        int time1=10;
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                //子线程不允许修改ui，只有主线程才可以修改

                MainActivity.this.runOnUiThread(new Runnable() {//匿名内部类
                    @Override
                    public void run() {
                        EnemyPlane temp=new EnemyPlane(MainActivity.this,(int)(Math.random()*root.getWidth()));
                        if(time%40==0){
                        enemyPlaneList.add(0,temp);
                        root.addView(enemyPlaneList.get(0));}
                        time++;
                        for(int i=0;i<enemyPlaneList.size();i++){
                            if(enemyPlaneList.get(i).currentX>root.getWidth()){
                                enemyPlaneList.get(i).speedX=-enemyPlaneList.get(i).speedX;
                            }
                            if(enemyPlaneList.get(i).currentX<0){
                                enemyPlaneList.get(i).speedX=-enemyPlaneList.get(i).speedX;
                            }
                            enemyPlaneList.get(i).move();
                        }
//                        if(enemyPlane.currentX>root.getWidth()){
//                            enemyPlane.speedX=-enemyPlane.speedX;
//                        }
//                        if(enemyPlane.currentX<0){
//                            enemyPlane.speedX=-enemyPlane.speedX;
//                        }
//                        enemyPlane.move();
                    }
                });

            }
        },time1,time1);
        root.addView(enemyPlaneList.get(0));

        final Ariplane ariplane=new Ariplane(this);
        ariplane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ariplane.currentX = event.getX() - ariplane.plane.getWidth() / 2;
                ariplane.currentY = event.getY() - ariplane.plane.getHeight() / 2;
                ariplane.setMinimumWidth(ariplane.plane.getWidth());
                ariplane.setMinimumHeight(ariplane.plane.getHeight());
                ariplane.invalidate();
                return true;
            }
        });
        root.addView(ariplane);
        final Bullet b1=new Bullet(this);
        b1.currentX=ariplane.currentX+ariplane.plane.getWidth()/2;
        b1.currentY=ariplane.currentY;
        root.addView(b1);
    }
}
