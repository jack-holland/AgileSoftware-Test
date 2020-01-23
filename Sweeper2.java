import java.util.Scanner;

public class Sweeper2 
{
         private int[][] game;
         private Sweeper sl;
         
    // 8 directions up, down, left, right, top left, bottom left, top right, bottom right
    public static final int[][] move = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};
    
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

}
    