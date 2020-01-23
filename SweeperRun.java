
public class SweeperRun {
    public int rows;        // rows
    public int columns;     // columns
    public int count;       // number of mines

    public SweeperRun(int rows, int columns, int count) {
        this.rows = rows>24 ? 24 : rows;            // 24 rows max
        this.columns = columns>30 ? 30 : columns;   // 30 columns max
        this.count = count;

        // Mine laying
//        run();

        // Output
//      printRes();
    }
}
