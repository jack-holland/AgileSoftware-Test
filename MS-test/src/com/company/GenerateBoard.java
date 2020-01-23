package com.company;

class GenerateBoard{
    private int rows;
    private int col;
    private String[][] board;

    GenerateBoard(int rows,int col){
        this.col =col;
        this.rows=rows;
        this.board = new String[rows][col];

    }

      public String[][] getBoard(){
          for (int i = 0; i < this.col; i++) {
              for (int n = 0; n < this.rows; n++) {
                  this.board[n][i] = "-";


              }
          }
        return this.board;

    }



}