import java.util.Scanner;

/**
 * This class demonstrates how to find
 * binomial coefficient nCr
 * using recursion, memoization and
 * dynamic programming
 */
public class BinomialCoefficientDemo {
    /**
     * Turn the DEBUG flag on to see some
     * extra console output
     */
    public static final boolean DEBUG = false;

    private static int getNonNegativeNumber(String argument){
        System.out.print("Enter "+argument+ " : ");
        Scanner scanner = new Scanner(System.in);
        int num = -1;
        try{
            num = scanner.nextInt();
            if(num<0){
                throw new ArithmeticException();
            }
        }catch (Exception e){
            if(DEBUG) {
                e.printStackTrace();
            }
            System.out.println("Next time a non negative integer please");
            System.exit(1);
        }
        return num;
    }

    private static int getN(){
        return getNonNegativeNumber("n");
    }

    private static int getR(){
        return getNonNegativeNumber("r");
    }


    public static int bino_rec(int n, int r){
        if(DEBUG){
            System.out.println("Evaluating : "+n+"C"+r);
        }
        if(r==0 || r==n){
            return 1;
        }
        return bino_rec(n-1,r)+bino_rec(n-1,r-1);
    }

    public static int bino_memo(int n, int r){
        int [][] bino_array = new int[n+1][r+1];
        for(int i = 0;i < bino_array.length ; i++){
            bino_array[i][0] = 1;
            if(i<=r){
                bino_array[i][i] = 1;
            }
        }
        return bino_memo_helper(n,r,bino_array);
    }

    private static int bino_memo_helper(int n, int r, int[][] bino_array) {
        if(bino_array[n][r]!=0){
            if(DEBUG){
                System.out.println("Recomputation avoided at : ["+n+"]["+r+"]");
            }
            return bino_array[n][r];
        }
        bino_array[n][r] = bino_memo_helper(n-1,r,bino_array)+bino_memo_helper(n-1,r-1,bino_array);
        return bino_array[n][r];
    }

    public static int bino_dp(int n , int r){
        int [][] bino_array = new int[n+1][r+1];
        for(int i = 0;i < bino_array.length ; i++){
            bino_array[i][0] = 1;
            if(i<=r){
                bino_array[i][i] = 1;
            }
        }
        for(int i = 1; i <=n ; i++){
            for(int j = 1; j <=r ; j++){
                bino_array[i][j] = bino_array[i-1][j] + bino_array[i-1][j-1];
            }
        }
        return bino_array[n][r];
    }

    public static void main(String [] args){
        System.out.println("This program helps find Binomial Coefficient (nCr) using different techniques");
        int n = getN();
        int r = getR();
        if(n<r){
            System.out.println("Good try...bye bye");
            System.exit(1);
        }
        System.out.println("Proceeding to find "+n+"C"+r+" in different ways.....");
        System.out.println("By recursion : "+bino_rec(n,r));
        System.out.println("By memoization : "+bino_memo(n,r));
        System.out.println("By dp : "+bino_dp(n,r));
        //To do
        //bino optimized with 1D array
    }
}
