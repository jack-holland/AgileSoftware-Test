package com.company;

class SaveDataTest {
    SaveData sl;

    @org.junit.jupiter.api.Test
    void generateNumber() {
        sl = new SaveData(9, 9, 10);

        System.out.print("\n");
        show(sl.data);

    }

    @org.junit.jupiter.api.Test
    void operation() {
    }

    @org.junit.jupiter.api.Test
    void getBoardWitheMines() {
    }
    void show(int[][] s){
        for (int i =1;i<s.length;i++){
            for (int n =1;n<s[0].length;n++){
                System.out.print("\t" + s[i][n]);
                if (n == s[0].length-1){
                    System.out.print("\n");
                }
            }
        }
    }
}