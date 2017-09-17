public class MinCostPath {

   static  int sCost[][]= {{1, 2, 3},
                           {4, 8, 2},
                           {1, 5, 3}};

   private static int min(int a, int b, int c){
       return a<b?(a<c?a:c):(b<c?b:c);
   }

   private static int findMinCostPathRecursive(int [][] cost,int row , int col){
       if(row == -1 || col == -1){
           return Integer.MAX_VALUE;
       }
       if(row==0 && col==0){
           return cost[row][col];
       }
       return cost[row][col]+min(findMinCostPathRecursive(cost,row-1,col),
                                 findMinCostPathRecursive(cost,row,col-1),
                                 findMinCostPathRecursive(cost,row-1,col-1));
   }

   private static int findMinCostPathDP(int [][] cost,int row,int col){
       int [][] min = new int[row+1][col+1];
       for(int i = 0; i <=row ; i++){
           for(int j =0 ; j<=col ; j++){
               if(i==0 && j==0){
                    min[i][j]=cost[i][j];
               }else if(i==0){
                   min[i][j] = cost[i][j]+min[i][j-1];
               }else if(j==0){
                   min[i][j] = cost[i][j]+min[i-1][j];
               }else{
                   min[i][j] = cost[i][j] + min(min[i-1][j],min[i][j-1],min[i-1][j-1]);
               }
           }
       }
       return min[row][col];
   }


   public static void main(String [] args){
        int recursiveCost = findMinCostPathRecursive(sCost,sCost.length-1,sCost.length-1);//Assumed square matrix
        System.out.println("Min cost recursive : "+recursiveCost);
       int dpCost = findMinCostPathDP(sCost,sCost.length-1,sCost.length-1);//Assumed square matrix
       System.out.println("Min cost dp : "+dpCost);
   }
}
