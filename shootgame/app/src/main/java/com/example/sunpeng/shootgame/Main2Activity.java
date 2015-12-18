package com.example.sunpeng.shootgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Button start =(Button)findViewById(R.id.start);
    }
}
