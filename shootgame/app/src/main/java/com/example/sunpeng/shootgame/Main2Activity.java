package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends Activity {
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    TextView frist;
    TextView scond;
    TextView thrid;
    EditText userName;
    String user;
    Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Button start =(Button)findViewById(R.id.start);
        frist=(TextView)findViewById(R.id.frist);
        scond=(TextView)findViewById(R.id.scond);
        thrid=(TextView)findViewById(R.id.thrid);
        userName=(EditText)findViewById(R.id.userName);
//        final MediaPlayer mp=MediaPlayer.create(this,R.raw.bgm_zhuxuanlv);
//        mp.start();
        preference = getSharedPreferences("result", MODE_APPEND);
        editor=preference.edit();
        String fristName=preference.getString("fristName","user01");
        int  fristResult=preference.getInt("fristResult", 0);
        frist.setText(fristName+":"+fristResult);
        String scondName=preference.getString("scondName","user02");
        int  scondResult=preference.getInt("scondResult", 0);
        scond.setText(scondName + ":" + scondResult);
        String thridName=preference.getString("thridName","user03");
        int  thridResult=preference.getInt("thridResult", 0);
        thrid.setText(thridName + ":" + thridResult);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent (Main2Activity.this,MainActivity.class);
                user = userName.getText().toString();
                Log.v("user", user);
                Intent intent = new Intent();
                intent.setAction("startGame");
                intent.addCategory("startGame");
                intent.putExtra("userName", user);
                startActivity(intent);
//                mp.stop();
                finish();
            }
        });
        clear=(Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                String fristName=preference.getString("fristName","user01");
                int  fristResult=preference.getInt("fristResult", 0);
                frist.setText(fristName+":"+fristResult);
                String scondName=preference.getString("scondName","user02");
                int  scondResult=preference.getInt("scondResult", 0);
                scond.setText(scondName + ":" + scondResult);
                String thridName=preference.getString("thridName","user03");
                int  thridResult=preference.getInt("thridResult", 0);
                thrid.setText(thridName + ":" + thridResult);
            }
        });

    }
}
