package com.company;
//final
import com.sun.tools.javac.Main;

import static org.junit.Assert.*;

public class SweeperTest
{

    @org.junit.Before
    public void setUp() throws Exception {
        sweeper = new Sweeper();
        sweeper.game = new int[10][10];
    }


    @Test
    public String transMsToMin(long second) {
        long time = 60*1000L;
        String result = transMsToMin(time);
        String expect = "1Minute0Second";
        assertSame(result,expect);
    }

    @Test
    public void count() {

    }

    @Test
    public void print() {

        sweeper.print(sweeper.game);
    }
}