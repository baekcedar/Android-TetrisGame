package com.baekcedar.android.threadbasic_tetris;


public class SetBlock {
    static int[][]block=
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}
    };
    static int tmp1,tmp2;
    boolean flag = true;
    int blockType;
    int[][] blockTmp;


    CustomView cv;

    public SetBlock(CustomView cv){ // 생성자
        this.cv = cv;
        blockTmp = new int[4][2];


    }
    public void blockDown(){
        synchronized (block) {
            block[0][1] += 1;
            block[1][1] += 1;
            block[2][1] += 1;
            block[3][1] += 1;
        }
    }
    public  void blockLeft(){
        synchronized (block) {
            if ((block[0][0] != 0) && (block[1][0] != 0) && (block[2][0] != 0) && (block[3][0] != 0)) {
                block[0][0] -= 1;
                block[1][0] -= 1;
                block[2][0] -= 1;
                block[3][0] -= 1;
            }
        }
    }
    public void blockRight(){
        synchronized (block) {
            if ((block[0][0] < 11) && (block[1][0] < 11) && (block[2][0] < 11) && (block[3][0] < 11)) {
                block[0][0] += 1;
                block[1][0] += 1;
                block[2][0] += 1;
                block[3][0] += 1;
            }
        }
    }
    public boolean rotationCheck() {

        for(int i=0; i < 8; i ++) {
            if(cv.stageBlocks[blockTmp[i][0]][blockTmp[i][1]] != 0){
                return false;
            }
        }

        return true;
    }

    public boolean blockchange(int rotation){
        synchronized (block) {

            // 아웃라인 체크
            for(int i =0 ; i < 4; i ++){
                if(block[i][0] != 0 || block[i][0] < 11 ){

                }else{

                }
            }
            switch (blockType) {
                case 1: // ㅡ 자
                    if (rotation == 1 || rotation == 3) {
                        blockTmp[0][0] = block[0][0];
                        blockTmp[0][1] = block[0][1];
                        blockTmp[1][0] = block[1][0] - 1;
                        blockTmp[1][1] = block[1][1] - 1;
                        blockTmp[2][0] = block[2][0] - 2;
                        blockTmp[2][1] = block[2][1] - 2;
                        blockTmp[3][0] = block[3][0] - 1;
                        blockTmp[3][1] = block[3][1] - 1;

                        if(rotationCheck()) {
                            block[1][0] -= 1;
                            block[1][1] -= 1;
                            block[2][0] -= 2;
                            block[2][1] -= 2;
                            block[3][0] -= 1;
                            block[3][1] -= 1;
                        }
                    }
                    else if (rotation == 2 || rotation == 4) {
                        blockTmp[0][0] = block[0][0];
                        blockTmp[0][1] = block[0][1];
                        blockTmp[1][0] = block[1][0] + 1;
                        blockTmp[1][1] = block[1][1] + 1;
                        blockTmp[2][0] = block[2][0] + 2;
                        blockTmp[2][1] = block[2][1] + 2;
                        blockTmp[3][0] = block[3][0] + 3;
                        blockTmp[3][1] = block[3][1] + 3;

                        if(rotationCheck()) {
                            block[1][0] += 1;
                            block[1][1] += 1;
                            block[2][0] += 2;
                            block[2][1] += 2;
                            block[3][0] += 3;
                            block[3][1] += 3;
                        }


                    }
                    break;
                case 2: // ㅁ 자
                    break;
                case 3: // L 자
                    if (rotation == 1) {
                        blockTmp[0][0] = block[0][0];
                        blockTmp[0][1] = block[0][1] +1;
                        blockTmp[1][0] = block[1][0];
                        blockTmp[1][1] = block[1][1] -1;
                        blockTmp[2][0] = block[2][0] +1;
                        blockTmp[2][1] = block[2][1] -1;
                        blockTmp[3][0] = block[3][0] +1;
                        blockTmp[3][1] = block[3][1] -2;

                        if(rotationCheck()) {
                            block[0][1] += 1;    // 1. Y
                            block[1][1] -= 1;    // 2. Y
                            block[2][0] += 1;    // 3. X
                            block[2][1] -= 2;    // 3. Y
                            block[3][0] += 1;    // 4. X
                            block[3][1] -= 2;    // 4. Y
                        }

                    } else if (rotation == 2) { //┏ --> ┒
                        blockTmp[0][0] = block[0][0] +1;
                        blockTmp[0][1] = block[0][1] -1;
                        blockTmp[1][0] = block[1][0] +2;
                        blockTmp[1][1] = block[1][1];
                        blockTmp[2][0] = block[2][0] +1;
                        blockTmp[2][1] = block[2][1] +1;
                        blockTmp[3][0] = block[3][0];
                        blockTmp[3][1] = block[3][1]+2;

                        if(rotationCheck()) {
                            block[0][0] += 1;    // 1. X
                            block[0][1] -= 1;    // 1. Y
                            block[1][0] += 2;    // 2. X
                            //  block[1][1] += 0;    // 2. Y
                            block[2][0] += 1;    // 3. X
                            block[2][1] += 1;    // 3. Y
                            //                    block[3][0] += 0;    // 4. X
                            block[3][1] += 2;    // 4. Y
                        }



                    } else if (rotation == 3) {
                        blockTmp[0][0] = block[0][0] -1;
                        blockTmp[0][1] = block[0][1] +2;
                        blockTmp[1][0] = block[1][0] -1;
                        blockTmp[1][1] = block[1][1] +2;
                        blockTmp[2][0] = block[2][0];
                        blockTmp[2][1] = block[2][1] +1;
                        blockTmp[3][0] = block[3][0];
                        blockTmp[3][1] = block[3][1] -1;

                        if(rotationCheck()) {
                            block[0][0] -= 1;    // 1. X
                            block[0][1] += 2;    // 1. Y

                            block[1][0] -= 1;    // 2. X
                            block[1][1] += 2;    // 2. Y
                            //                    block[2][0] += 0;    // 3. X
                            block[2][1] += 1;    // 3. Y
                            //                    block[3][0] += 0;    // 4. X
                            block[3][1] -= 1;    // 4. Y
                        }

                    } else if (rotation == 4) { //처음상태
                        blockTmp[0][0] = block[0][0] ;
                        blockTmp[0][1] = block[0][1] -2;
                        blockTmp[1][0] = block[1][0] -1;
                        blockTmp[1][1] = block[1][1] -1;
                        blockTmp[2][0] = block[2][0] -2;
                        blockTmp[2][1] = block[2][1];
                        blockTmp[3][0] = block[3][0] -1;
                        blockTmp[3][1] = block[3][1] +1;

                        if(rotationCheck()) {
                            //                    block[0][0] += 0;    // 1. X
                            block[0][1] -= 2;    // 1. Y
                            block[1][0] -= 1;    // 2. X
                            block[1][1] -= 1;    // 2. Y
                            block[2][0] -= 2;    // 3. X
                            //                    block[2][1] += 0;    // 3. Y
                            block[3][0] -= 1;    // 4. X
                            block[3][1] += 1;    // 4. Y
                        }

                    }
                    break;
                case 4: // ┓
                    if (rotation == 1) {
                        blockTmp[0][0] = block[0][0] +2 ;
                        blockTmp[0][1] = block[0][1] ;
                        blockTmp[1][0] = block[1][0] +1;
                        blockTmp[1][1] = block[1][1] +1;
                        blockTmp[2][0] = block[2][0] ;
                        blockTmp[2][1] = block[2][1] +2;
                        blockTmp[3][0] = block[3][0] -1;
                        blockTmp[3][1] = block[3][1] +1;

                        if(rotationCheck()) {
                            block[0][0] += 2;    // 1. X
                            //                    block[0][1] += 0;    // 1. Y
                            block[1][0] += 1;    // 2. X
                            block[1][1] += 1;    // 2. Y
                            //                    block[2][0] -= 0;    // 3. X
                            block[2][1] += 2;    // 3. Y
                            block[3][0] -= 1;    // 4. X
                            block[3][1] += 1;    // 4. Y
                        }

                    } else if (rotation == 2) {
                        blockTmp[0][0] = block[0][0] -2 ;
                        blockTmp[0][1] = block[0][1] +1;
                        blockTmp[1][0] = block[1][0] -2;
                        blockTmp[1][1] = block[1][1] +1;
                        blockTmp[2][0] = block[2][0] -1;
                        blockTmp[2][1] = block[2][1] ;
                        blockTmp[3][0] = block[3][0] +1;
                        blockTmp[3][1] = block[3][1] ;

                        if(rotationCheck()) {
                            block[0][0] -= 2;    // 1. X
                            block[0][1] += 1;    // 1. Y

                            block[1][0] -= 2;    // 2. X
                            block[1][1] += 1;    // 2. Y

                            block[2][0] -= 1;    // 3. X
                            //                    block[2][1] += 0;    // 3. Y

                            block[3][0] += 1;    // 4. X
                        }

                        //                    block[3][1] += 0;    // 4. Y
                    } else if (rotation == 3) {
                        blockTmp[0][0] = block[0][0]  ;
                        blockTmp[0][1] = block[0][1] +1;
                        blockTmp[1][0] = block[1][0] ;
                        blockTmp[1][1] = block[1][1] -1;
                        blockTmp[2][0] = block[2][0] -1;
                        blockTmp[2][1] = block[2][1] -2;
                        blockTmp[3][0] = block[3][0] -1;
                        blockTmp[3][1] = block[3][1] -2;

                        if(rotationCheck()) {
                            //                    block[0][0] += 0;    // 1. X
                            block[0][1] += 1;    // 1. Y
                            //                    block[1][0] -= 0;    // 2. X
                            block[1][1] -= 1;    // 2. Y
                            block[2][0] -= 1;    // 3. X
                            block[2][1] -= 2;    // 3. Y
                            block[3][0] -= 1;    // 4. X
                            block[3][1] -= 2;    // 4. Y
                        }

                    } else if (rotation == 4) {
                        blockTmp[0][0] = block[0][0]  ;
                        blockTmp[0][1] = block[0][1] -2;
                        blockTmp[1][0] = block[1][0] +1;
                        blockTmp[1][1] = block[1][1] -1;
                        blockTmp[2][0] = block[2][0] +2;
                        blockTmp[2][1] = block[2][1] ;
                        blockTmp[3][0] = block[3][0] +1;
                        blockTmp[3][1] = block[3][1] +1;

                        if(rotationCheck()) {
                            //                    block[0][0] += 0;    // 1. X
                            block[0][1] -= 2;    // 1. Y
                            block[1][0] += 1;    // 2. X
                            block[1][1] -= 1;    // 2. Y
                            block[2][0] += 2;    // 3. X
                            //                    block[2][1] += 0;    // 3. Y
                            block[3][0] += 1;    // 4. X
                            block[3][1] += 1;    // 4. Y
                        }

                    }
                    break;
                    case 5: // z 자
                        if (rotation == 1 || rotation == 3) {
                            blockTmp[0][0] = block[0][0] +2;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] +1;
                            blockTmp[1][1] = block[1][1] +1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] -1;
                            blockTmp[3][1] = block[3][1] +1;

                            if(rotationCheck()) {
                                block[0][0] += 2;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                block[1][0] += 1;    // 2. X
                                block[1][1] += 1;    // 2. Y
                                //                    block[2][0] +=0;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] -= 1;    // 4. X
                                block[3][1] += 1;    // 4. Y
                            }

                        } else if (rotation == 2 || rotation == 4) {
                            blockTmp[0][0] = block[0][0] -2;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] -1;
                            blockTmp[1][1] = block[1][1] -1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] +1;
                            blockTmp[3][1] = block[3][1] -1;

                            if(rotationCheck()) {
                                block[0][0] -= 2;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                block[1][0] -= 1;    // 2. X
                                block[1][1] -= 1;    // 2. Y
                                //                    block[2][0] +=0;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] += 1;    // 4. X
                                block[3][1] -= 1;    // 4. Y
                            }

                        }
                        break;

                    case 6: // S 자
                        if (rotation == 1 || rotation == 3) {
                            blockTmp[0][0] = block[0][0] -1;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] ;
                            blockTmp[1][1] = block[1][1] +1;
                            blockTmp[2][0] = block[2][0] +1;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] +2;
                            blockTmp[3][1] = block[3][1] +1;

                            if(rotationCheck()) {
                                block[0][0] -= 1;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                //                    block[1][0] +=0;    // 2. X
                                block[1][1] += 1;    // 2. Y
                                block[2][0] += 1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] += 2;    // 4. X
                                block[3][1] += 1;    // 4. Y
                            }
                        } else if (rotation == 2 || rotation == 4) {
                            blockTmp[0][0] = block[0][0] +1;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] ;
                            blockTmp[1][1] = block[1][1] -1;
                            blockTmp[2][0] = block[2][0] -1;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] -2;
                            blockTmp[3][1] = block[3][1] -1;

                            if(rotationCheck()) {
                                block[0][0] += 1;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                //                    block[1][0] -=0;    // 2. X
                                block[1][1] -= 1;    // 2. Y
                                block[2][0] -= 1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] -= 2;    // 4. X
                                block[3][1] -= 1;    // 4. Y
                            }
                        }
                        break;

                    case 7: // ㅗ 자
                        if (rotation == 1) {
                            blockTmp[0][0] = block[0][0] ;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] +1;
                            blockTmp[1][1] = block[1][1] +1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] ;
                            blockTmp[3][1] = block[3][1] ;

                            if(rotationCheck()) {
                                //                    block[0][0] +=0;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                block[1][0] += 1;    // 2. X
                                block[1][1] += 1;    // 2. Y
                                //                    block[2][0] -=1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                //                    block[3][0] -=2;    // 4. X
                                //                    block[3][1] -=1;    // 4. Y
                            }
                        } else if (rotation == 2) {
                            blockTmp[0][0] = block[0][0] -1;
                            blockTmp[0][1] = block[0][1] +1;
                            blockTmp[1][0] = block[1][0] +1;
                            blockTmp[1][1] = block[1][1] -1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] -1;
                            blockTmp[3][1] = block[3][1] +1;

                            if(rotationCheck()) {
                                block[0][0] -= 1;    // 1. X
                                block[0][1] += 1;    // 1. Y
                                block[1][0] += 1;    // 2. X
                                block[1][1] -= 1;    // 2. Y
                                //                    block[2][0] -=1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] -= 1;    // 4. X
                                block[3][1] += 1;    // 4. Y
                            }
                        } else if (rotation == 3) {
                            blockTmp[0][0] = block[0][0] +1;
                            blockTmp[0][1] = block[0][1] -1;
                            blockTmp[1][0] = block[1][0] -1;
                            blockTmp[1][1] = block[1][1] +1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] +1;
                            blockTmp[3][1] = block[3][1] +1;

                            if(rotationCheck()) {
                                block[0][0] += 1;    // 1. X
                                block[0][1] -= 1;    // 1. Y
                                block[1][0] -= 1;    // 2. X
                                block[1][1] += 1;    // 2. Y
                                //                    block[2][0] -=1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] += 1;    // 4. X
                                block[3][1] += 1;    // 4. Y
                            }
                        } else if (rotation == 4) {
                            blockTmp[0][0] = block[0][0] ;
                            blockTmp[0][1] = block[0][1] ;
                            blockTmp[1][0] = block[1][0] -1;
                            blockTmp[1][1] = block[1][1] -1;
                            blockTmp[2][0] = block[2][0] ;
                            blockTmp[2][1] = block[2][1] ;
                            blockTmp[3][0] = block[3][0] -2;
                            blockTmp[3][1] = block[3][1] ;

                            if(rotationCheck()) {
                                //                    block[0][0] +=1;    // 1. X
                                //                    block[0][1] +=0;    // 1. Y
                                block[1][0] -= 1;    // 2. X
                                block[1][1] -= 1;    // 2. Y
                                //                    block[2][0] -=1;    // 3. X
                                //                    block[2][1] +=0;    // 3. Y
                                block[3][0] -= 2;    // 4. X
                                //                    block[3][1] -=1;    // 4. Y
                            }
                        }
                        break;



            }
            return true;
        }


    }
    public void blockSetting(int blockType){
        this.blockType = blockType;
        synchronized (block) {
            switch (blockType) {
                case 1:
                    cv.setBlockColor(255, 0, 0);//FF0000
                    break;
                case 2:
                    cv.setBlockColor(255, 140, 0);//FF8C00
                    break;
                case 3:
                    cv.setBlockColor(255, 255, 0);//FFFF00
                    break;
                case 4:
                    cv.setBlockColor(0, 128, 0);//008000
                    break;
                case 5:
                    cv.setBlockColor(0, 0, 255);//0000FF
                    break;
                case 6:
                    cv.setBlockColor(75, 0, 130);//4B0082
                    break;
                case 7:
                    cv.setBlockColor(128, 0, 128);//800080
                    break;
            }

            // block[번째불럭][x,y]
            switch (blockType) {
                case 1: // ㅡ 자
                    block[0][0] = 4;
                    block[0][1] = 1;
                    block[1][0] = 5;
                    block[1][1] = 1;
                    block[2][0] = 6;
                    block[2][1] = 1;
                    block[3][0] = 7;
                    block[3][1] = 1;

                    break;
                case 2: // ㅁ 자
                    block[0][0] = 4;
                    block[0][1] = 1;
                    block[1][0] = 5;
                    block[1][1] = 1;
                    block[2][0] = 4;
                    block[2][1] = 2;
                    block[3][0] = 5;
                    block[3][1] = 2;
                    break;
                case 3: // L 자
                    block[0][0] = 4;
                    block[0][1] = 1;
                    block[1][0] = 4;
                    block[1][1] = 2;
                    block[2][0] = 4;
                    block[2][1] = 3;
                    block[3][0] = 5;
                    block[3][1] = 3;
                    break;
                case 4: // ┓
                    block[0][0] = 4;
                    block[0][1] = 1;
                    block[1][0] = 5;
                    block[1][1] = 1;
                    block[2][0] = 6;
                    block[2][1] = 1;
                    block[3][0] = 6;
                    block[3][1] = 2;
                    break;
                case 5: // z 자
                    block[0][0] = 4;
                    block[0][1] = 1;
                    block[1][0] = 5;
                    block[1][1] = 1;
                    block[2][0] = 5;
                    block[2][1] = 2;
                    block[3][0] = 6;
                    block[3][1] = 2;
                    break;
                case 6: // S 자
                    block[0][0] = 6;
                    block[0][1] = 1;
                    block[1][0] = 5;
                    block[1][1] = 1;
                    block[2][0] = 5;
                    block[2][1] = 2;
                    block[3][0] = 4;
                    block[3][1] = 2;
                    break;
                case 7: // ㅗ 자
                    block[0][0] = 5;
                    block[0][1] = 1;
                    block[1][0] = 4;
                    block[1][1] = 2;
                    block[2][0] = 5;
                    block[2][1] = 2;
                    block[3][0] = 6;
                    block[3][1] = 2;
                    break;


            }
        }
    }
}
