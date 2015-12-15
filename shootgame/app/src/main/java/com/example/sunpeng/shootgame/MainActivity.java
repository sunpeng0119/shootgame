package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private UIHandler mUIHandler;
    private WorkHandler mWorkHander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        RelativeLayout root=(RelativeLayout)findViewById(R.id.root);
        final EnemyPlane enemyPlane=new EnemyPlane(this);
        final EnemyPlane  enmy[];
        enemyPlane.currentX=50;
        enemyPlane.currentY=50;
        //        enemyPlane.setBackgroundColor(Color.RED);
        //enemyPlane.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        mUIHandler=new UIHandler(Looper.getMainLooper());
        HandlerThread thread=new HandlerThread("my work");
        thread.start();
        mWorkHander=new WorkHandler(thread.getLooper());
//        Timer t1=new Timer();
//        int time1=10000;
//        t1.schedule(new TimerTask() {
//            @Override
//            public void run() {
                mUIHandler.obtainMessage(1001,enemyPlane).sendToTarget();
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        enemyPlane.currentY+=100;
//                        enemyPlane.invalidate();
//                    }
//                });

//            }
//        },10,time1);

        mWorkHander.sendEmptyMessageDelayed(MSG_REFRESH_START,10000);
        mWorkHander.sendEmptyMessage(MSG_REFRESH_START);
        root.addView(enemyPlane);

        final Ariplane ariplane=new Ariplane(this);
       // setContentView(ariplane);
        //ariplane.setBackgroundResource(R.drawable.background);
//        ariplane.setBackgroundColor(Color.GREEN);
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
    private class UIHandler extends android.os.Handler{
        public UIHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1001:
                {
                    Ariplane enemyPlane=(Ariplane)msg.obj;
                    enemyPlane.currentY+=100;
                    enemyPlane.invalidate();
                }
                break;
            }
        }
    }
    public static final int MSG_WORK_ONE1=1;
    private static final int MSG_REFRESH_START=2;
    private static final int MSG_INtERVAL=3;
    private class WorkHandler extends Handler{
        public WorkHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_WORK_ONE1:{
                    //do something
                    mWorkHander.sendEmptyMessageDelayed(MSG_WORK_ONE1,1000);
                }
            }
        }
    }
}
