package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    int state;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final RelativeLayout root=(RelativeLayout)findViewById(R.id.root);
        final TextView textsorce=(TextView)findViewById(R.id.sorce);
        final TextView life=(TextView)findViewById(R.id.life);
        final Button puse=(Button)findViewById(R.id.puse);
        airplane=(Airplane)findViewById(R.id.airplane);
        life.setText("life:" + airplane.life);
        final ImageView show=new ImageView(this);

        airplane.currentX=500;
        airplane.currentY=1200;
        b1=new Bullet(this);
        enemyPlane= new EnemyPlane(this);
        enemyPlaneList=new LinkedList<EnemyPlane>();
        bulletList=new LinkedList<Bullet>();
        bulletList.add(0,b1);
        enemyPlaneList.add(0,enemyPlane);
        num=1;
        time=0;
        sorce =0;
        state=RUNNING;
//        MediaPlayer mp=new MediaPlayer();
//        //MediaPlayer.create(this, R.raw.bgm_cunshiqujinbi);
//        try {
//            mp.setDataSource(this, Uri.parse("http://tobeing.cn/test.mp3"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mp.setLooping(true);
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mp.seekTo(0);
//                mp.start();
//            }
//        });
//        try {
//            mp.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mp.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                SoundPool soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 2);
//
//                int num1 = soundPool.load(MainActivity.this, R.raw.bgm_jizhanboss2, 1);
//                soundPool.play(num1, 1, 1, 0, -1, 1);
//            }
//            }).start();

        puse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == RUNNING) {
                    state = PAUSE;
                    puse.setText("重新开始");
                    show.setImageResource(R.drawable.pause);
                    root.addView(show);
                } else {
                    puse.setText("暂停");
                    state = RUNNING;
                    root.removeView(show);
                }
            }
        });

        final Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (state == RUNNING) {
                    sorce += Bullet.hit(enemyPlaneList, bulletList, root, airplane);
                    textsorce.setText("sorce:" + sorce);
                    EnemyPlane.hit(enemyPlaneList, airplane, root);
                    life.setText("life:" + airplane.life);
                    if (airplane.life == 0) {
                        //root.removeView(show);
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        state = GAME_OVER;
                        startActivity(intent);
                        finish();
                        onDestroy();
                    }
                    //敌机进场
                    EnemyPlane.enterEnemy(time, enemyPlaneList, root, MainActivity.this);
                    //英雄级发射子弹
                    Airplane.fire(airplane, bulletList, root, MainActivity.this, time);
                    time++;
                    //刷新英雄机动画
                    airplane.freshen(time);
                }
            }
        };

        Timer t1=new Timer();
        final int time1=10;
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                //子线程不允许修改ui，只有主线程才可以修改

//                MainActivity.this.runOnUiThread(new Runnable() {//匿名内部类
//                    @Override
//                    public void run() {
//                        if (state == RUNNING) {
//                            sorce += Bullet.hit(enemyPlaneList, bulletList, root, airplane);
//                            textsorce.setText("sorce:" + sorce);
//                            EnemyPlane.hit(enemyPlaneList, airplane, root);
//                            life.setText("life:" + airplane.life);
//                            if (airplane.life == 0) {
//                                //root.removeView(show);
//                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                                state = GAME_OVER;
//                                startActivity(intent);
//                                //finish();
//                                onDestroy();
//                            }
//                            //敌机进场
//                            EnemyPlane.enterEnemy(time, enemyPlaneList, root, MainActivity.this);
//                            //英雄级发射子弹
//                            Airplane.fire(airplane, bulletList, root, MainActivity.this, time);
//                            time++;
//                            //刷新英雄机动画
//                            airplane.freshen(time);
//                        }
//                    }
//                });
                Message message=new Message();
                message.what=100;
                handler.sendMessage(message);
            }
        }, time1, time1);
        root.addView(enemyPlaneList.get(0));
        root.addView(bulletList.get(0));

        airplane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (state == RUNNING) {
                    airplane.currentX = event.getX() - airplane.plane.getWidth() / 2;
                    airplane.currentY = event.getY() - airplane.plane.getHeight() / 2;
                    airplane.setMinimumWidth(airplane.plane.getWidth());
                    airplane.setMinimumHeight(airplane.plane.getHeight());
                    airplane.invalidate();
                }
                return true;
            }
        });
       // root.addView(airplane);
    }

}
