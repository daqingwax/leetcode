//boundary conditions need to be considered
// if x wins, the difference has to be 1
// if o wins, the different has to be 0
class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCnt = 0;
        int oCnt = 0;
        int leftDiag = 0, rightDiag = 0;
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        int xWin = 0, oWin = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                char ch = board[i].charAt(j);
                if(ch == 'X'){
                    xCnt++;
                    rowSum[i]++;
                    colSum[j]++;
                    if(i == j) leftDiag++;
                    if(i + j == 2) rightDiag++;
                }
                else if(ch == 'O'){
                    oCnt++;
                    rowSum[i]--;
                    colSum[j]--;
                    if(i == j) leftDiag--;
                    if(i + j == 2) rightDiag--;
                }
                else if(ch != ' '){
                    return(false);
                }
                if(rowSum[i] == 3) xWin++;
                else if(rowSum[i] == -3) oWin++;
                if(colSum[j] == 3) xWin++;
                else if(colSum[j] == -3) oWin++;
                if(leftDiag == 3) xWin++;
                else if(leftDiag == -3) oWin++;
                if(rightDiag == 3) xWin++;
                else if(rightDiag == -3) oWin++;
            }
        }
        int cntDiff = xCnt - oCnt;
        if(!(cntDiff >= 0 && cntDiff <= 1)) return(false);
        //if(xWin > 1 || oWin > 1) return(false);
        if(xWin >= 1 && cntDiff != 1) return(false);
        if(oWin >= 1 && cntDiff != 0) return(false);
        if(xWin >= 1 && oWin >= 1) return(false);
        return(true);
        
           
    }
}
