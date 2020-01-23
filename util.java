public class util{

	public void dfs(int i, int j){

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

	public boolean dfs_range(int i, int j, int dir_i, int dir_j){

        if(i+dir_i>=1 && i+dir_i<=sl.rows && j+dir_j>=1 && j+dir_j<=sl.columns){
            return true;
		}
        return false;
    }
}
