
import java.util.Scanner;
        

public class Sweeper 
{       
         private int[][] game;
         private Sweeper sl;
         private Scanner sc;
         public int rows;        // rows
         public int columns;     // columns
         public int count;       // number of mines
         
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
}
