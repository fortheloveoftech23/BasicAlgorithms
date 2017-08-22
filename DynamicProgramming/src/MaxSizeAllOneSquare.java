public class MaxSizeAllOneSquare {

    private static final boolean DEBUG = false;

    private static final int [][] main_array = {
        {0,1,1,1,1},
        {1,1,1,0,1},
        {0,0,1,1,1},
        {1,1,1,1,1},
        {1,1,1,1,0}
    };

    public static int findArrayNormally(int [][] array){
        int max = 0;
        for(int i = 1; i<=array.length ; i++){
            if(isExistAllOne(array,i)){
                max = i;
            }else{
                break;
            }
        }
        return max;
    }

    public static boolean isExistAllOne(int [][] array,int len){
        for(int i = 0 ; i<(array.length)-len+1;i++){
            for(int j = 0 ; j<(array.length)-len+1;j++){
                int rowStart = i;
                int colStart = j;
                boolean found = true;
                for(int k = i;k<i+len;k++){
                    for(int l = j;l<j+len;l++){
                        if(array[k][l]==0){
                            found = false;
                        }
                    }
                }
                if(found){
                    return true;
                }
            }
        }

        return false;
    }

    private static void print2DArray(boolean [][] array){
        System.out.println();
        for(int i = 0 ; i <array.length ; i++){
            for(int j = 0 ; j<array[i].length ; j++){
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static int findArrayUsingDP_moretimeandspace(int [][] array){
        int maxlen = array.length;
        int findLength = 0;
        boolean [][][] status = new boolean [maxlen][maxlen][maxlen];
        for(int i = 0 ; i<maxlen;i++){
            for(int j = 0; j <maxlen-i ; j++){
                for(int k = 0 ; k <maxlen-i ; k++){
                    if(i==0){
                        status[i][j][k]= array[j][k]==1;
                    }else{
                        status[i][j][k] = status[i-1][j][k]
                                && status[i-1][j][k+1] && status[i-1][j+1][k]
                                && status[i-1][j+1][k+1];
                    }

                    if(status[i][j][k]){
                        findLength = i+1;
                    }
                }
            }
            if(DEBUG){
                System.out.println("For i = "+i);
                print2DArray(status[i]);
            }
        }
        return findLength;
    }

    public static int min(int a , int b , int c){
        return a<b?(a<c?a:c):(b<c?b:c);
    }


    public static int findArrayUsingDp(int[][] array){
        int row = array.length;
        int col = array.length;
        //for simplicity we have considered a sqaure matrix
        int [][] status = new int[row][col];

        int max = 0;

        for(int i = 0 ; i <row ; i++){
            status[i][0] = array[i][0];
            if(status[i][0]>max){
                max = status[i][0];
            }
        }
        for(int i = 0 ; i <col ; i++){
            status[0][i] = array[0][i];
            if(status[0][i]>max){
                max = status[0][i];
            }
        }
        for(int i = 1 ; i<row ; i++){
            for(int j = 1; j<col ; j++ ){
                if(array[i][j]==1){
                     status[i][j] = 1+ min(status[i-1][j-1],status[i-1][j],status[i][j-1]);
                     if(status[i][j]>max){
                         max = status[i][j];
                     }
                }else{
                    status[i][j] = 0;
                }
            }
        }
        return max;
    }


    public static void main(String [] args){
        System.out.println("Length normally : "+findArrayNormally(main_array)+" length using dp extra: "
                +findArrayUsingDP_moretimeandspace(main_array)+ " length using optimized dp: "+findArrayUsingDp(main_array));
    }
}
