package com.company;

public class Main {

    public static void main(String[] args) {
	GenerateBoard Board =new GenerateBoard(9,9);
	GenerateMines BoardwithMines = new GenerateMines(Board.getBoard(),10, new int[]{3,4});
	String[][] BoardWithoutBlock= BoardwithMines.GetBoardWitheMines();
	showpad(BoardWithoutBlock);
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
