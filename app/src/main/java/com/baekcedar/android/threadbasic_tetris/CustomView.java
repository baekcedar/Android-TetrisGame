package com.baekcedar.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by HM on 2016-10-17.
 */
public class CustomView extends View {
    Context context;
    int unit;
    static int[][] stageBlocks ;
    Paint blockPaint;
    Paint[][] stageBlockPaint;
    int r=0,g=0,b=0;

    public CustomView(Context context){
        super(context);
        this.context = context;
        blockPaint = new Paint();
        unit = MainActivity.block_size;
        stageBlocks = new int[12][22];
        stageBlockPaint= new Paint[12][22];
        for(int x = 0 ; x < 12; x++){
            for(int y = 0 ; y < 22; y++) {
                stageBlockPaint[x][y] = new Paint();
            }
        }
        for(int i = 0 ; i < 12; i++){
            stageBlocks[i][21] = 1;
        }
    }


    public void setBlockColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
        blockPaint.setColor(Color.rgb(r,g,b));

    }
    public void setStageBlockColor(int x, int y){

     //   Log.i("x / y : ", ""+x+" / "+y);
        stageBlockPaint[x][y].setColor(Color.rgb(r,g,b));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i=0; i < 4; i ++){
            canvas.drawRect(SetBlock.block[i][0]*unit, SetBlock.block[i][1]*unit, SetBlock.block[i][0]*unit+unit,SetBlock.block[i][1]*unit+unit, blockPaint);
        }

        for(int x=0; x < 12; x ++){
            for(int y = 0; y < 21; y ++){
                if(stageBlocks[x][y]!= 0) {
                    canvas.drawRect(x * unit, y * unit, x * unit + unit, y * unit + unit, stageBlockPaint[x][y]);
                }
            }
        }



    }
}
