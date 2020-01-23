package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateBoardTest {
    public GenerateBoard Board =new GenerateBoard(9,9);

    @Test
    void testBoard(){
        GenerateBoard Borad = new GenerateBoard(9,9);
        String[][] a =Borad.getBoard();
        int expectsize[] = {9,9};
        showpad(a);
        assertEquals(expectsize[0],a.length);
        assertEquals(expectsize[1],a[0].length);
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
}