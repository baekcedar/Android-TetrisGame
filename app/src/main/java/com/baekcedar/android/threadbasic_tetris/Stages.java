package com.baekcedar.android.threadbasic_tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by HM on 2016-10-17.
 */
class Stages {

    public static ArrayList<int[]> stageBlock;

    public Stages(){

        stageBlock = new ArrayList<>();
    }
    public void setBlock(int level){
        int cnt =0;
        // 랜덤값 한줄 4~7, 랜덤한 위치
        Random random = new Random();
        for(int i =1; i <= level; i++ ){ // 레벨 만큼 라인 추가
            int[] blockLine = new int[10]; // 라인 생성
            int blockCount = random.nextInt(3)+4; // 블럭 수

            List<Integer> tmpCount = new ArrayList<>();

            for (int start = 0 ; start < blockCount ; start++){
                tmpCount.add(1);
            }

            ramdomNumber(tmpCount);
            Collections.sort(tmpCount);


            for(int j = 1; j <= blockLine.length; j ++){

                if(tmpCount.get(cnt) == j){
                    blockLine[j-1] = 1;
                    if( cnt != tmpCount.size()-1) {
                        cnt++;
                    }
                }
            }
            cnt =0;

            stageBlock.add(blockLine); // 라인 추가
        }

    }
    public void ramdomNumber(List<Integer>  list){ // 랜덤값 부여 , 중복검사

        int length = list.size();

        // 중복안되는 랜덤숫자
        for(int i=0;i<length;i++){

            list.set(i, (int) (Math.ceil(Math.random()*10)));
            for(int j = 0; j<i; j++){ // 중복검사
                if ( list.get(j) == list.get(i) ){
                    i--;
                    break;
                }
            }
        }

    }


}
