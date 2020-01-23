package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SaveData {
    public int[][] Originalboard;
    public int[][] BoardWithMInes;
    public int count;
    public List<int[]> cordlist;

    public int rows;
    public int columns;
    public int[][] data;
    SaveData(int rows, int columns, int count){

        this.count =count;
        this.rows = Math.min(rows,24);
        this.columns = Math.min(columns,30);

        //this.cord1stclick = cord1stclick;
        this.Operation();
    }
    public void SetMines(){
        ArrayList<int[]> cordlist = new ArrayList<>();
        Random rnd = new Random();
        /*for (int i = 0;i<this.columns;i++){
            this.BoardWithMInes[0][i+1] = i+1;
            this.BoardWithMInes[i+1][0] = i+1;
        }*/

        while (cordlist.size()!=this.count){
            int[] cord = new int[]{rnd.nextInt(this.Originalboard.length-1)+1,rnd.nextInt(this.Originalboard[0].length-1)+1};

            if (!find1(cordlist,cord)){
                cordlist.add(cord);
            }


        }
        this.cordlist = cordlist;
        this.BoardWithMInes =this.Originalboard;
        for(int i= 0;i<this.count;i++){
            this.BoardWithMInes[cordlist.get(i)[0]][cordlist.get(i)[1]] = -1;
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
                    this.BoardWithMInes[i][j] = numbuffer;

                }
            }
        }
        for (int i = 1; i < BoardWithMInes.length; i++) {
            for (int j = 1; j < BoardWithMInes[i].length; j++) {
                BoardWithMInes[i][j] = BoardWithMInes[i][j]==0 ? -2 : BoardWithMInes[i][j];
            }
        }
    }

    void Operation(){
        this.Originalboard = new int[this.rows+1][this.columns+1];
        this.SetMines();
        this.GenerateNumber();
        this.data = this.BoardWithMInes;
    }

    int[][] GetBoardWitheMines(){
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
    boolean find1(ArrayList<int[]> b,int[] a){

        for (int i =0; i<b.size();i++){
            if(b.get(i)[0] == a[0]&&b.get(i)[1]==a[1]){
                return true;
            }

        }

        return false;
    }

}