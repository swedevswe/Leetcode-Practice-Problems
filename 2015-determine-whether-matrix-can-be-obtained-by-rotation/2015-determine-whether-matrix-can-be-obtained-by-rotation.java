class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        //rotating in-place:
        int n = mat.length;
        //at most 4 rotations
        for (int i = 0; i < 4; i++){
            // rotation operation
            for (int k = 0; k < n / 2; k++){
                for (int j = 0; j < (n + 1) / 2; j++){
                    int temp = mat[k][j];
                    mat[k][j] = mat[n - 1 - j][k];
                    mat[n - 1 - j][k] = mat[n - 1 - k][n - 1 - j];
                    mat[n - 1 - k][n - 1 - j] = mat[j][n - 1 - k];
                    mat[j][n - 1 - k] = temp;
                }
            }
            if (isEqual(mat, target)) {
                return true;
            }
        }
        return false;
        }
        private boolean isEqual(int[][] mat, int[][] target){
            int n = mat.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != target[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
    }
