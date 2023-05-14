class Solution {
    public int[] findBall(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        int[] res = new int[m];
        
        for(int i=0; i<m; i++){
            res[i] = dfs(grid,0,i,n);
        }
        return res;
    }
    
    public int dfs(int[][] mat, int r, int c, int n){
        //self check 
        if(r == n) return c;
        //nbrs (only down)
        if(mat[r][c] == -1){ //left
            int R = r + 1;
            int C = c - 1;
            
            if(R <= n && C >= 0 && mat[r][c-1] != 1){
                return dfs(mat,R,C,n);
            }
        }else{ //right
            int R = r + 1;  
            int C = c + 1;
            
            if(R <= n && C < mat[0].length && mat[r][c+1] != -1){
                return dfs(mat,R,C,n);
            }
        }
        return -1;  //ball stuck, no path
    }
}


//agar right me bhej rha h to right(bagal) wale cell m blockage nhi honi chahiye, same for left one
//we do not need vis, mark coz we only go downward and do not do any changes in the array

// [[1,1,1,-1,-1],
//  [1,1,1,-1,-1],
//  [-1,-1,-1,1,1],
//  [1,1,1,1,-1],
//  [-1,-1,-1,-1,-1]]
 
// diag = 1 + 1 -1 + 1 -1 
// rdiag = -1 -1 -1 + 1 -1 
 
