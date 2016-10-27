package com.baekcedar.android.threadbasic_tetris;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button upBtn, downBtn, leftBtn, rightBtn;
    int stageLevel=2;
    static int mainStage_Height;
    static int mainStage_Width;
    static int rotation=1;
    static int block_size;
    static int levelSpeed = 400;
    float displayW;
    static float displayH;
    Stages stages;
    static CustomView cv;
    SetBlock setBlock;
    DropBlock dropBlock;
    static boolean hit = true;

    //static boolean hitBlock = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics ds = getResources().getDisplayMetrics();
        displayW =  ds.widthPixels;
        displayH =  ds.heightPixels;
        block_size = (int)(displayW/16);
        mainStage_Height = block_size*21;
        mainStage_Width = block_size*12;
        setContentView(R.layout.activity_main);

        Log.i("mainStage_Height: ",""+mainStage_Height);
        FrameLayout mainStage  = (FrameLayout)findViewById(R.id.frameLayout);
        LinearLayout mainStagelinear   = (LinearLayout)findViewById(R.id.linearLayout);
        Log.i("mainStage", ""+mainStage);
        LinearLayout subStagre  = (LinearLayout) findViewById(R.id.subLayout);
        upBtn   = (Button) findViewById(R.id.upBtn);
        downBtn = (Button)findViewById(R.id.downBtn);
        leftBtn = (Button)findViewById(R.id.leftBtn);
        rightBtn= (Button)findViewById(R.id.rightBtn);

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setBlock.blockchange(rotation);
                rotation++;
                if (rotation > 4) {
                    rotation = 1;
                }
                dropBlock.hitCheck();
                cv.postInvalidate();

            }
        });

        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBlock.blockDown();
                cv.postInvalidate();
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBlock.blockLeft();
                cv.postInvalidate();

            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBlock.blockRight();
                cv.postInvalidate();
            }
        });

        //mainStage 동적 크기 조정 (block 정사각형 기준)
        if(mainStagelinear != null) {
            mainStagelinear.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    (block_size*12),block_size*21)
            );
            if(mainStage != null) {
                mainStage.setBackgroundColor(Color.BLACK);
            }
            Log.i("TEST", "setLayoutParams");
        }
        if(subStagre != null) {
            subStagre.setBackgroundColor(Color.GRAY);
        }
        cv = new CustomView(this);
        mainStage.addView(cv);
        stages = new Stages();
        setStage(stageLevel);
        setBlock = new SetBlock(cv);
        dropBlock = new DropBlock(cv);
        new Thread(dropBlock).start();

    } //onCreate



    public void setStage ( int stageLevel ){
        stages.setBlock(stageLevel);

    }

    class DropBlock implements Runnable {
        CustomView cv;
        Random ran;

        public DropBlock(CustomView cv) { //생성자
            this.cv = cv;
            ran = new Random();
            setBlock.blockSetting(ran.nextInt(7) + 1);
        }

        public void stageAdd() {
            int x, y;
            synchronized (setBlock.block) {
                for (int i = 0; i < 4; i++) {
                    x = setBlock.block[i][0];
                    y = setBlock.block[i][1];
                    cv.stageBlocks[x][y] = 1;
                    cv.setStageBlockColor(x, y);
                }
            }
        }
        public synchronized boolean hitCheck(){
           int x,y;
            synchronized (setBlock.block) {
                for (int i = 0; i < 4; i++) {
                    x = setBlock.block[i][0];
                    y = setBlock.block[i][1];
                    if (cv.stageBlocks[x][y + 1] == 1) {
                        stageAdd();
                        return true;
                    }
                }
            }
            return false;

        }

        @Override
        public void run() {
            while (true) {
                    if( !hit && !hitCheck() ) {

                        setBlock.blockDown();

                    }else{
                        rotation = 1;
                        setBlock.blockSetting(ran.nextInt(7) + 1);
                        hit = false;
                    }


                try {
                    Thread.sleep(levelSpeed);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cv.postInvalidate();
            }



        }
    } // class RainDrop


}
