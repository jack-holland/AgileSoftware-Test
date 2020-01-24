
import java.util.Scanner;

public class Main
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
        System.out.println("\t Number of mines" + count() + "/" + sl.count);
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


//// Game difficulty selection
//    public void start()
//    {
//        System.out.println("Please select difficulty:");
//        System.out.println("1.Elementary");
//        System.out.println("2.Intermediate");
//        System.out.println("3.High");
//        System.out.println("4.Customize");
//
//        //choose = flag(1, 4);
//
//        // Create mines based on difficulty
//        switch (choose)
//        {
//            case 1:
//                sl = new SaveData(9, 9, 10);  // Elementary
//                break;
//            case 2:
//                sl = new SaveData(16, 16, 40); // Intermediate
//                break;
//            case 3:
//                sl = new SaveData(16, 30, 99); // High
//                break;
//            case 4:
//                int r,c,count,range; // Rows, columns, mines, mines range
//                System.out.print("Custom line (up to 24):");
//                r = flag(1,24);
//                System.out.print("Custom columns (up to 30):");
//                c = flag(1,30);
//                range = (int)(r*c*0.45);
//                System.out.print("Custom mines (max"+ range +");");
//                count = flag(1,range);
//                sl = new SaveData(r, c, count);
//                break;
//            default:
//                break;
//        }
//    }
//}

    class entity {
        public int[][] Originalboard;
        public int[][] BoardWithMInes;
        public int count;
        public List<int[]> cordlist;

        public int rows;
        public int columns;
        public int[][] data;
        entity(int rows, int columns, int count){

            this.count =count;
            this.rows = Math.min(rows,24);
            this.columns = Math.min(columns,30);

            //this.cord1stclick = cord1stclick;
            this.Operation();
        }
        public void SetMines(){
            ArrayList<int[]> cordlist = new ArrayList<>();
            Random rnd = new Random();
        /*for (int i = 0;i<this.columns;i++){
            this.BoardWithMInes[0][i+1] = i+1;
            this.BoardWithMInes[i+1][0] = i+1;
        }*/

            while (cordlist.size()!=this.count){
                int[] cord = new int[]{rnd.nextInt(this.Originalboard.length-1)+1,rnd.nextInt(this.Originalboard[0].length-1)+1};

                if (!find1(cordlist,cord)){
                    cordlist.add(cord);
                }


            }
            this.cordlist = cordlist;
            this.BoardWithMInes =this.Originalboard;
            for(int i= 0;i<this.count;i++){
                this.BoardWithMInes[cordlist.get(i)[0]][cordlist.get(i)[1]] = -1;
            }

        }
        void GenerateNumber(){
          for (int i=0;i<this.Originalboard.length;i++){
                for (int j=0;j<this.Originalboard[0].length;j++){
                    int[] t = new int[]{i,j};
                    int numbuffer = 0;
                    if(find(new int[]{i, j})==false ){
                        if (i - 1 >= 0 && j - 1 >= 0 && find(new int[]{i-1,j-1})) {

                            numbuffer++;
                        }
                        // top
                        if (i - 1 >= 0 && find(new int[]{i-1,j})) {
                            numbuffer++;
                        }
                        // top right
                        if (i - 1 >= 0 && j + 1 < this.Originalboard[0].length && find(new int[]{i-1,j+1})) {
                            numbuffer++;
                        }
                        // left
                        if (j - 1 >= 0 && find(new int[]{i,j-1})) {
                            numbuffer++;
                        }
                        // right
                        if (j + 1 < this.Originalboard[0].length && find(new int[]{i,j+1})) {
                            numbuffer++;
                        }
                        // left down
                        if (i + 1 < this.Originalboard.length && j - 1 >= 0 && find(new int[]{i+1,j-1})) {
                            numbuffer++;
                        }
                        // down
                        if (i + 1 < this.Originalboard.length && find(new int[]{i+1,j})) {
                            numbuffer++;
                        }
                        // right down
                        if (i + 1 < this.Originalboard.length && j + 1 < this.Originalboard[0].length && find(new int[]{i+1,j+1})) {
                            numbuffer++;
                        }
                        this.BoardWithMInes[i][j] = numbuffer;

                    }
                }
            }
            for (int i = 1; i < BoardWithMInes.length; i++) {
                for (int j = 1; j < BoardWithMInes[i].length; j++) {
                    BoardWithMInes[i][j] = BoardWithMInes[i][j]==0 ? -2 : BoardWithMInes[i][j];
                }
            }
        }

        void Operation(){
            this.Originalboard = new int[this.rows+1][this.columns+1];
            this.SetMines();
            this.GenerateNumber();
            this.data = this.BoardWithMInes;
        }

        int[][] GetBoardWitheMines(){
            return this.BoardWithMInes;

        }

        boolean find(int[] a){

            for (int i =0; i<this.cordlist.size();i++){
                if(this.cordlist.get(i)[0] == a[0]&&this.cordlist.get(i)[1]==a[1]){
                    return true;
                }

            }

            return false;
        }
        public boolean find1(ArrayList<int[]> b,int[] a){

            for (int i =0; i<b.size();i++){
                if(b.get(i)[0] == a[0]&&b.get(i)[1]==a[1]){
                    return true;
                }

            }

            return false;
        }

    }
