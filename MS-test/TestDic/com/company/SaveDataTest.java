package com.company;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



    class SaveDataTest {
        SaveData sl;

        @org.junit.jupiter.api.Test
        void generateNumber() {
            sl = new SaveData(9, 9, 10);

            System.out.print("\n");
            show(sl.data);

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



        @org.junit.jupiter.api.Test
        void find1Test() {

            int[][] intarraytest = {{0,0},{0,1},{1,0},{1,1},{3,3}};//Create test int array
            ArrayList<int[]> ListTest= new ArrayList<>();//Create list
            for(int i = 0; i<4;i++){
                ListTest.add(intarraytest[i]);// Add the first four int array to the list
            }
            Boolean Expectation;
            Boolean Actual;     // Declare the Expect result, and Actual result
            /*Test fail: the array {3,3} was not added,so if the method is correct, the return must be false  */
            Expectation = true;// if the method is correct, the test must fail
            //assertEquals(Expectation,Main.find1(ListTest,intarraytest[4]));

            /*Test pass: the array {0,0} was added at first,so if the method is correct, the return must be true  */
            assertEquals(Expectation,Main.find1(ListTest,intarraytest[0]));


        }
    }

