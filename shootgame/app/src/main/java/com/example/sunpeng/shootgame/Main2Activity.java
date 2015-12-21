package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Button start =(Button)findViewById(R.id.start);
//        final MediaPlayer mp=MediaPlayer.create(this,R.raw.bgm_zhuxuanlv);
//        mp.start();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                Intent intent=new Intent();
                intent.setAction("startGame");
                intent.addCategory("startGame");
                startActivity(intent);
//                mp.stop();
                finish();
            }
        });
    }
}
