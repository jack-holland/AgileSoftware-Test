
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Sweeper mine = new Sweeper();
    }
}

class Sweeper {
    private int[][] game;
    private entity sl;
    private Scanner sc;
    private int choose; // Game selection
    private boolean flag = false; // Incomplete results sign
    private boolean jump = false; // skip the input
    private boolean start = true;
    private boolean pass = false;
    private long startTime;
    private long endTime;

    // 8 directions up, down, left, right, top left, bottom left, top right, bottom right
    public static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

    public Sweeper() {
        sc = new Scanner(System.in);

        // Initialization starts
        start();
//        sl = new entity(9, 9, 10);
        game = new int[sl.rows + 1][sl.columns + 1];
        print(game);

        // Execute the game
        play(0,0,0);
    }

    // Recursion shows gray areas
    private void dfs(int i, int j){
        game[i][j] = -2;

        for (int k = 0; k < 8; k++) {
            if(dfs_range(i, j, move[k][0], move[k][1])){
                if(sl.data[i+move[k][0]][j+move[k][1]]==-2 && game[i+move[k][0]][j+move[k][1]]==0){
                    game[i+move[k][0]][j+move[k][1]] = -2;
                    dfs(i+move[k][0], j+move[k][1]);
                }else{
                    game[i+move[k][0]][j+move[k][1]] = sl.data[i+move[k][0]][j+move[k][1]];
                }
            }
        }
    }

    // Judge whether the boundary is crossed by the direction
    private boolean dfs_range(int i, int j, int dir_i, int dir_j){
        if(i+dir_i>=1 && i+dir_i<=sl.rows && j+dir_j>=1 && j+dir_j<=sl.columns)
            return true;
        return false;
    }

    // Judge if the game has been won
    public boolean judge(){
        // Loop through the columns. Loop size is determined by game array length.
        for (int column = 1; column < game.length; column++)
        {
            // Loop through the rows based on the column selected.
            for (int row = 1; row < game[column].length; row++)
            {
                // If the data inside the array selected is a mine
                if(sl.data[column][row] == -1)
                {
                    // Continue to the next if statement
                    continue;
                }
                // If the system data array is different to the game array
                // then it is a flag so the array should return false
                if(game[column][row] != sl.data[column][row])
                {
                    // Function return false
                    return false;
                }
            }
        }

        // If the code above is completed, set the pass variable to true
        pass = true;

        // If pass is true
        if(pass)
        {
            // Loop through the columns and rows again
            for (int column = 1; column < game.length; column++)
            {
                for (int row = 1; row < game[column].length; row++)
                {
                    // If game board is 0 it is unrevealed
                    if(game[column][row] == 0)
                    {
                        // Set the array selected to -1 which is bomb
                        game[column][row] = -1;

                        // Set the bomb to a flag
                        flag = true;
                    }
                }
            }
        }
        // Function returned as true
        return true;
    }


    public void play(int i, int j, int m){
        while(!judge()){

            if(!jump){
                System.out.print("input row：");
                i = flag(1,sl.rows);
                System.out.print("input column：");
                j = flag(1,sl.columns);
                if(game[i][j]<=0 && game[i][j]!=-2){
                    System.out.print("is or not mine（-1/0）：");
                    m = flag(-1,0);
                }else m = 0;
            }

            // mark the mine
            if(m==-1){
                game[i][j]=m;
                print(game);
                continue;
            }

            // When the first step hits the mine, reposition
            if(start && m==0){
                while(sl.data[i][j]==-1){
                    sl.Operation();
                }
                start = false;

                // start
                startTime = System.currentTimeMillis();
            }

            if(game[i][j]==0 || game[i][j]==-1){ // unknown squares
                if(sl.data[i][j]==-1){  // mine
                    print(sl.data);
                    System.out.println("you hit the mine！");
                    break;
                }else if(sl.data[i][j]==-2){ // blank
                    dfs(i,j);
                    if(!jump)
                        print(game);
                    else break;
                }else{  // Tip number
                    game[i][j] = sl.data[i][j];
                    if(!jump)
                        print(game);
                    else break;
                }
            }else if(game[i][j]>0){ // click the number
                if(numberEqualsMines(i, j)){
                    for (int k = 0; k < 8; k++) {
                        if(dfs_range(i, j, move[k][0], move[k][1]) && game[i+move[k][0]][j+move[k][1]]==0){
                            jump = true;
                            play(i+move[k][0],j+move[k][1],0);
                            jump = false;
                        }
                    }
                    print(game);
                }
            }
        }

        endTime = System.currentTimeMillis();
        // success
        if(pass){
            if(flag) print(game);
            System.out.println("Congratulations!");
            System.out.println("you have spent: " + transMsToMin(endTime-startTime));
        }
    }

    // The calculation indicates whether the number is equal to the surrounding landmine markers
    public boolean numberEqualsMines(int i, int j){
        int count = 0;
        for (int k = 0; k < 8; k++) {
            if(dfs_range(i, j, move[k][0], move[k][1])){
                if(game[i+move[k][0]][j+move[k][1]]==-1)
                    count++;
            }
        }

        return count==game[i][j] ? true : false;
    }


    private int flag(int low, int high){
        int num;

        do{
            num = sc.nextInt();
            if(num<low || num>high)System.out.println("Please input again：");
            else break;
        }while(true);

        return num;
    }


    // Convert _ms time to _m_s form
    public String transMsToMin(long time) {
        String res = "";

        int second = (int) (time / 1000), minute = 0;

        while (second >= 60) {
            second -= 60;
            minute++;
        }
        res += (minute > 0 ? minute + "Minute" : "") + second + "Second";
        return res;
    }

    // Calculate the current number of mines
    public int count() {
        int num = 0;
        for (int i = 1; i < game.length; i++) {
            for (int j = 1; j < game[i].length; j++) {
                if (game[i][j] == -1) num++;
            }
        }
        return num;
    }

    // Print interface
    public void print(int arr[][]) {
        System.out.println();
        System.out.println("\t Number of mines" + count() + "/" + sl.count);
        if (sl.columns >= 10) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (i == 0) {
                        if (j == 0) System.out.print("  |");
                        else System.out.print(j / 10 + "|");
                    } else {
                        if (j == 0) System.out.print("  |");
                        else System.out.print(j % 10 + "|");
                    }
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < arr[0].length; i++) {
                if (i == 0) System.out.print("   ");
                else System.out.print(i + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 0) {
                    if (j == 0) System.out.print("  ");
                    else System.out.print("_");
                    System.out.print(" ");
                } else {
                    if (j == 0) {
                        System.out.print(i);
                        if (i < 10) System.out.print(" ");
                    } else if (arr[i][j] == 0) System.out.print("_");
                    else if (arr[i][j] == -1) System.out.print("*");
                    else if (arr[i][j] == -2) System.out.print("~");
                    else System.out.print(arr[i][j]);
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }


    // Game difficulty selection
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
}

class entity {
    public int[][] Originalboard;
    public int[][] BoardWithMInes;
    public int count;
    public List<int[]> cordlist;

    public int rows;
    public int columns;
    public int[][] data;

    entity(int rows, int columns, int count) {

        this.count = count;
        this.rows = Math.min(rows, 24);
        this.columns = Math.min(columns, 30);

        //this.cord1stclick = cord1stclick;
        this.Operation();
    }

    public void SetMines() {
        ArrayList<int[]> cordlist = new ArrayList<>();
        Random rnd = new Random();
        /*for (int i = 0;i<this.columns;i++){
            this.BoardWithMInes[0][i+1] = i+1;
            this.BoardWithMInes[i+1][0] = i+1;
        }*/

        while (cordlist.size() != this.count) {
            int[] cord = new int[]{rnd.nextInt(this.Originalboard.length - 1) + 1, rnd.nextInt(this.Originalboard[0].length - 1) + 1};

            if (!find1(cordlist, cord)) {
                cordlist.add(cord);
            }


        }
        this.cordlist = cordlist;
        this.BoardWithMInes = this.Originalboard;
        for (int i = 0; i < this.count; i++) {
            this.BoardWithMInes[cordlist.get(i)[0]][cordlist.get(i)[1]] = -1;
        }

    }

    void GenerateNumber() {
        for (int i = 0; i < this.Originalboard.length; i++) {
            for (int j = 0; j < this.Originalboard[0].length; j++) {
                int[] t = new int[]{i, j};
                int numbuffer = 0;
                if (find(new int[]{i, j}) == false) {
                    if (i - 1 >= 0 && j - 1 >= 0 && find(new int[]{i - 1, j - 1})) {

                        numbuffer++;
                    }
                    // top
                    if (i - 1 >= 0 && find(new int[]{i - 1, j})) {
                        numbuffer++;
                    }
                    // top right
                    if (i - 1 >= 0 && j + 1 < this.Originalboard[0].length && find(new int[]{i - 1, j + 1})) {
                        numbuffer++;
                    }
                    // left
                    if (j - 1 >= 0 && find(new int[]{i, j - 1})) {
                        numbuffer++;
                    }
                    // right
                    if (j + 1 < this.Originalboard[0].length && find(new int[]{i, j + 1})) {
                        numbuffer++;
                    }
                    // left down
                    if (i + 1 < this.Originalboard.length && j - 1 >= 0 && find(new int[]{i + 1, j - 1})) {
                        numbuffer++;
                    }
                    // down
                    if (i + 1 < this.Originalboard.length && find(new int[]{i + 1, j})) {
                        numbuffer++;
                    }
                    // right down
                    if (i + 1 < this.Originalboard.length && j + 1 < this.Originalboard[0].length && find(new int[]{i + 1, j + 1})) {
                        numbuffer++;
                    }
                    this.BoardWithMInes[i][j] = numbuffer;

                }
            }
        }
        for (int i = 1; i < BoardWithMInes.length; i++) {
            for (int j = 1; j < BoardWithMInes[i].length; j++) {
                BoardWithMInes[i][j] = BoardWithMInes[i][j] == 0 ? -2 : BoardWithMInes[i][j];
            }
        }
    }

    void Operation() {
        this.Originalboard = new int[this.rows + 1][this.columns + 1];
        this.SetMines();
        this.GenerateNumber();
        this.data = this.BoardWithMInes;
    }

    int[][] GetBoardWitheMines() {
        return this.BoardWithMInes;

    }

    boolean find(int[] a) {

        for (int i = 0; i < this.cordlist.size(); i++) {
            if (this.cordlist.get(i)[0] == a[0] && this.cordlist.get(i)[1] == a[1]) {
                return true;
            }

        }

        return false;
    }

    public boolean find1(ArrayList<int[]> b, int[] a) {

        for (int i = 0; i < b.size(); i++) {
            if (b.get(i)[0] == a[0] && b.get(i)[1] == a[1]) {
                return true;
            }

        }

        return false;
    }
}

