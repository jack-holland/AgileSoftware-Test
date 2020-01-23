public class Play{

	public void play(int i, int j, int m){

		//The judge method is to be implemented
        while(!judge()){

            if(!jump){
                System.out.print("input row£º");
                i = flag(1,sl.rows);
                System.out.print("input column£º");
                j = flag(1,sl.columns);
                if(game[i][j]<=0 && game[i][j]!=-2){
                    System.out.print("is mine or not£¨-1/0£©£º");
                    m = flag(-1,0);
                }else m = 0;
            }

            // Mark mines
            if(m==-1){
                game[i][j]=m;
                print(game);
                continue;
            }         

            if(game[i][j]==0 || game[i][j]==-1){ // unrevealed squares
                if(sl.data[i][j]==-1){  // is mine
                    print(sl.data);
                    System.out.println("you clicked the mine£¡");
                    break;
                }else if(sl.data[i][j]==-2){ // blank squares
                    dfs(i,j);
                    print(game);
                }else{  // number of mines
                    game[i][j] = sl.data[i][j];
                    print(game);                 
                }
            }else if(game[i][j]>0){ // click the number
                if(numberEqualsMines(i, j)){
                    for (int k = 0; k < 8; k++) {
                        if(dfs_range(i, j, move[k][0], move[k][1]) && game[i+move[k][0]][j+move[k][1]]==0){
                            play(i+move[k][0],j+move[k][1],0);
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
            System.out.println("Congratulations£¡");
            System.out.println("you have spent" + transMsToMin(endTime-startTime));
        }
    }
}
