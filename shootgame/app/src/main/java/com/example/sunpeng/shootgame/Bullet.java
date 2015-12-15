package com.example.sunpeng.shootgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sunpeng on 15-12-15.
 */
public class Bullet extends View {
    float currentX;
    float currentY;
    Bitmap bullet;
    Bullet(Context context){
        super(context);
        bullet= BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(bullet,currentX,currentY,p);

    }
}
