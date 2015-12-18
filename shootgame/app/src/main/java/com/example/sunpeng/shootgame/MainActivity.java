package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    EnemyPlane enemyPlane;
    Bullet b1;
    List<EnemyPlane> enemyPlaneList;
    List<Bullet>  bulletList;
    Airplane airplane;
    int time;
    int num;
    int sorce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final RelativeLayout root=(RelativeLayout)findViewById(R.id.root);
        final TextView textsorce=(TextView)findViewById(R.id.sorce);
        final TextView life=(TextView)findViewById(R.id.life);
        airplane=new Airplane(this);
        life.setText("life:"+airplane.life);
        airplane.currentX=300;
        airplane.currentY=1000;
        b1=new Bullet(this);
        enemyPlane= new EnemyPlane(this);
        enemyPlaneList=new LinkedList<EnemyPlane>();
        bulletList=new LinkedList<Bullet>();
        bulletList.add(0,b1);
        enemyPlaneList.add(0,enemyPlane);
        num=1;
        time=0;
        sorce =0;
        Timer t2=new Timer();
        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sorce+=Bullet.hit(enemyPlaneList,bulletList,root,airplane);
                        textsorce.setText("sorce:"+sorce);
                        EnemyPlane.hit(enemyPlaneList, airplane, root);
                        life.setText("life:"+airplane.life);
                    }
                });
            }
        },10,10);
        Timer t1=new Timer();
        final int time1=10;
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                //子线程不允许修改ui，只有主线程才可以修改

                MainActivity.this.runOnUiThread(new Runnable() {//匿名内部类
                    @Override
                    public void run() {
                        //敌机进场
                        EnemyPlane.enterEnemy(time,enemyPlaneList,root,MainActivity.this);
                        //英雄级发射子弹
                        Airplane.fire(airplane,bulletList,root,MainActivity.this,time);
                        time++;
                        //刷新英雄机动画
                        airplane.freshen(time);
                    }
                });

            }
        }, time1, time1);
        t1.cancel();
        root.addView(enemyPlaneList.get(0));
        root.addView(bulletList.get(0));



        airplane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                airplane.currentX = event.getX() - airplane.plane.getWidth() / 2;
                airplane.currentY = event.getY() - airplane.plane.getHeight() / 2;
                airplane.setMinimumWidth(airplane.plane.getWidth());
                airplane.setMinimumHeight(airplane.plane.getHeight());
                airplane.invalidate();
                return true;
            }
        });
        root.addView(airplane);
    }

}
