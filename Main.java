import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Sweeper mine = new Sweeper();
    }
}

public class Sweeper 
{
    private int[][] game;
    private SaoLei sl;
    private Scanner sc;
    private int choose; // Game selection

    // 8 directions up, down, left, right, top left, bottom left, top right, bottom right
    public static final int[][] move = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};

    public Sweeper() 
    {
        sc = new Scanner(System.in);

        // Initialization starts
        start();
        game = new int[sl.rows+1][sl.columns+1];
        print(game);

        // Execute the game
        play(0,0,0);
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
        System.out.println("\t Number of mines£º" + count() + "/" + sl.count);
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
