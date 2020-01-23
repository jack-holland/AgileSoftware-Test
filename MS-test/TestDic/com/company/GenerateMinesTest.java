package com.company;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenerateMinesTest {

    @Test
    void setMines() {
        GenerateBoard Borad = new GenerateBoard(9,9);
        String[][] a =Borad.getBoard();
        showpad(a);
        GenerateMines minestest =new GenerateMines(a,10);
        minestest.SetMines();
        a = minestest.GetBoardWitheMines();
       // showpad(a);
        minestest.GenerateNumber();
        for (int i = 0;i<minestest.cordlist.size();i++){
            print(Integer.toString(minestest.cordlist.get(i)[0]));
            print(Integer.toString(minestest.cordlist.get(i)[1]) + "\n");
        }



        a = minestest.GetBoardWitheMines();
        showpad(a);


    }

    @Test
    void generateNumber() {







    }








    static void print(String s) {
        System.out.print(s + "\t");
    }
    static void showpad(String[][] s) {
        int width = s.length;
        int len = s[0].length;
        String player[] = { "Player 1: X", "Player 2: O" };
        for (int i = 0; i < width; i++) {
            for (int n = 0; n < len; n++) {
                if (i == 0 && n == 0) {
                    print("\t" + s[i][n]);
                } else {
                    print(s[i][n]);
                    if (n == len - 1) {
                        if (i == 0 || i == 1) {
                            print("  " + player[i] + "\n");
                        } else {
                            print("\n");
                        }
                    }
                }
            }
        }
    }

    @Test
    void operation() {
        GenerateBoard Borad = new GenerateBoard(9,9);
        String[][] a =Borad.getBoard();

        List<int[]> cordlist = new ArrayList<>();
        Random rnd = new Random();


        while (cordlist.size()!=10){
            int[] cord = new int[]{rnd.nextInt(a.length),rnd.nextInt(a[0].length)};

            if (!cordlist.contains(cord)){
                cordlist.add(cord);
            }


        }


        for(int i= 0;i<10;i++){
            a[cordlist.get(i)[0]][cordlist.get(i)[1]] = "*";
        }
        showpad(a);
    }

    @Test
    void getBoardWitheMines() {
        ArrayList<int[]> a= new ArrayList<>();
        int [][]  b= new int[][]{{1,1},{2,3},{2,6},{3,6},{3,1},{6,3},{7,8},{5,5},{8,2},{3,9}};

        for(int i= 0;i<10;i++){
            a.add(b[i]);
        }
        print("( "+ a.get(0)[0] +" , "+a.get(0)[1] +" )");
        assertEquals(find(a,new int[]{1,1}),true);
        int t[] ={1,1};


    }
    boolean find(ArrayList<int[]> L,int[] a){

        for (int i =0; i<L.size();i++){
            if(L.get(i)[0] == a[0]&&L.get(i)[1]==a[1]){
                return true;
            }

        }

        return false;
    }
}