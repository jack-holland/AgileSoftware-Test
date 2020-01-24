
import java.util.Scanner;

public class Mimi
{
    public static void main(String[] args)
    {
        Sweeper mine = new Sweeper();
    }
}

class Sweeper
{
    private int[][] game;
    private entity sl;
    private Scanner sc;
    private int choose; // Game selection
    private boolean flag = false; // Incomplete results sign

    // 8 directions up, down, left, right, top left, bottom left, top right, bottom right
    public static final int[][] move = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};

    public Sweeper()
    {
        sc = new Scanner(System.in);

        // Initialization starts
       // start();
        sl = new entity(9, 9, 10);
        game = new int[sl.rows+1][sl.columns+1];
        print(game);

        // Execute the game
        //play(0,0,0);
    }

    // Convert _ms time to _m_s form
    public String transMsToMin(long time)
    {
        String res = "";

        int second = (int) (time/1000), minute = 0;

        while(second >= 60)
        {
            second -= 60;
            minute++;
        }
        res += (minute>0 ? minute+"Minute" : "") + second + "Second";
        return res;
    }

    // Calculate the current number of mines
    public int count()
    {
        int num = 0;
        for (int i = 1; i < game.length; i++)
        {
            for (int j = 1; j < game[i].length; j++)
            {
                if(game[i][j] == -1) num++;
            }
        }
        return num;
    }

    // Print interface
    public void print(int arr[][])
    {
        System.out.println();
        System.out.println("\t Number of mines 0" + count() + "/" + sl.count);
        if(sl.columns>=10)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < arr[0].length; j++)
                {
                    if(i==0)
                    {
                        if(j==0)System.out.print("  |");
                        else System.out.print(j/10+"|");
                    }
                    else
                    {
                        if(j==0)System.out.print("  |");
                        else System.out.print(j%10+"|");
                    }
                }
                System.out.println();
            }
        }
        else
        {
            for (int i = 0; i < arr[0].length; i++)
            {
                if(i==0)System.out.print("   ");
                else System.out.print(i+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                if(i==0)
                {
                    if(j==0)System.out.print("  ");
                    else System.out.print("_");
                    System.out.print(" ");
                }
                else
                {
                    if(j==0)
                    {
                        System.out.print(i);
                        if(i<10) System.out.print(" ");
                    }
                    else if(arr[i][j]==0) System.out.print("_");
                    else if(arr[i][j]==-1) System.out.print("*");
                    else if(arr[i][j]==-2) System.out.print("~");
                    else System.out.print(arr[i][j]);
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

     //Game difficulty selection
    public void start()
    {
        System.out.println("Please select difficulty:");
        System.out.println("1.Elementary");
        System.out.println("2.Intermediate");
        System.out.println("3.High");
        System.out.println("4.Customize");

        choose = flag(1, 4);

        // Create mines based on difficulty
        switch (choose)
        {
            case 1:
                sl = new entity(9, 9, 10);  // Elementary
                break;
            case 2:
                sl = new entity(16, 16, 40); // Intermediate
                break;
            case 3:
                sl = new entity(16, 30, 99); // High
                break;
            case 4:
                int r,c,count,range; // Rows, columns, mines, mines range
                System.out.print("Custom line (up to 24):");
                r = flag(1,24);
                System.out.print("Custom columns (up to 30):");
                c = flag(1,30);
                range = (int)(r*c*0.45);
                System.out.print("Custom mines (max"+ range +");");
                count = flag(1,range);
                sl = new entity(r, c, count);
                break;
            default:
                break;
        }
    }

    private int flag(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class entity {
    public int rows;        // Row
    public int columns;     // Column
    public int count;       // Landmines
    public int[][] data;    // Store data information

    public entity(int rows, int columns, int count) {
        this.rows = rows>24 ? 24 : rows;            // 24 rows max 
        this.columns = columns>30 ? 30 : columns;   // 30 columns max
        this.count = count;

        // Mine laying
//        run();

        // Output
//      printRes();
    }
}