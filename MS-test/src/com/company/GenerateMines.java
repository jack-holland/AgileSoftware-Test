package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateMines {
    private String[][] Originalboard;
    private String[][] BoardWithMInes;
    private int MineNum;
    public List<int[]> cordlist;
    GenerateMines(String[][] Board,int MineNum){
        this.Originalboard = Board;
        this.MineNum =MineNum;
        this.Operation();

    }
   public void SetMines(){
        List<int[]> cordlist = new ArrayList<>();
        Random rnd = new Random();


       while (cordlist.size()!=this.MineNum){
           int[] cord = new int[]{rnd.nextInt(this.Originalboard.length),rnd.nextInt(this.Originalboard[0].length)};

           if (!cordlist.contains(cord)){
               cordlist.add(cord);
           }


       }
        this.cordlist = cordlist;
        this.BoardWithMInes =this.Originalboard;
        for(int i= 0;i<this.MineNum;i++){
            this.BoardWithMInes[cordlist.get(i)[0]][cordlist.get(i)[1]] = "*";
        }





    }
    void GenerateNumber(){



        for (int i=0;i<this.Originalboard.length;i++){
            for (int j=0;j<this.Originalboard[0].length;j++){
                int[] t = new int[]{i,j};
                int numbuffer = 0;
                if(find(new int[]{i, j})==false ){
                    if (i - 1 >= 0 && j - 1 >= 0 && find(new int[]{i-1,j-1})) {

                        numbuffer++;
                    }
                    // 上
                    if (i - 1 >= 0 && find(new int[]{i-1,j})) {
                        numbuffer++;
                    }
                    // 右上
                    if (i - 1 >= 0 && j + 1 < this.Originalboard[0].length && find(new int[]{i-1,j+1})) {
                        numbuffer++;
                    }
                    // 左
                    if (j - 1 >= 0 && find(new int[]{i,j-1})) {
                        numbuffer++;
                    }
                    // 右
                    if (j + 1 < this.Originalboard[0].length && find(new int[]{i,j+1})) {
                        numbuffer++;
                    }
                    // 左下
                    if (i + 1 < this.Originalboard.length && j - 1 >= 0 && find(new int[]{i+1,j-1})) {
                        numbuffer++;
                    }
                    // 下
                    if (i + 1 < this.Originalboard.length && find(new int[]{i+1,j})) {
                        numbuffer++;
                    }
                    // 右下
                    if (i + 1 < this.Originalboard.length && j + 1 < this.Originalboard[0].length && find(new int[]{i+1,j+1})) {
                        numbuffer++;
                    }
                    this.BoardWithMInes[i][j] = Integer.toString(numbuffer);

                }
            }
        }
    }

    void Operation(){
        this.SetMines();
        this.GenerateNumber();
    }

    String[][] GetBoardWitheMines(){
        return this.BoardWithMInes;

    }

    boolean find(int[] a){

        for (int i =0; i<this.cordlist.size();i++){
            if(this.cordlist.get(i)[0] == a[0]&&this.cordlist.get(i)[1]==a[1]){
                return true;
            }

        }

        return false;
    }
}