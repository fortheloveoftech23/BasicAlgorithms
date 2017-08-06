import java.util.Scanner;

/**
 * This class demonstrates how
 * to find the nth Fibonacci number (n>=0)
 * in the fibonacci series f(n) = f(n-1) + f(n-2)
 * f(0) = 1
 * f(1) = 1
 * To simplify
 * 1 1 2 3 5 8 13 21 34 55 89 and so on
 * Assumption(index from 0) -- Disclaimer!!
 * 0th fibo number is 1
 * 1th fibo number is 1
 * 2th fibo number is 2
 * .....
 * 6th fibo number is 13
 * etc.
 */
public class FibonacciDemo{

    private static final boolean DEBUG = false;

    public static int getNForFetchingFibonacci(){
        System.out.print("Enter n to view the nth Fibonacci number : ");
        Scanner scanner = new Scanner(System.in);
        int n = -1;
        try{
            n = scanner.nextInt();
            if(n<0){
                throw new ArithmeticException();
            }
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Next time a non negative integer please");
            System.exit(1);
        }
        return n;
    }

    public static int fibo_rec(int n){
        if(n==0 || n==1){
            return 1;
        }
        return fibo_rec(n-1)+fibo_rec(n-2);
    }

    public static int fib_memo(int n){
        int junkValue = -1;
        int [] fibMemoArray = new int[n+1];
        for(int i = 0 ; i<fibMemoArray.length ; i++){
            fibMemoArray[i] = junkValue;
        }
        fibMemoArray[0]=1;
        fibMemoArray[1]=1;
        return fib_memo_helper(n,fibMemoArray,junkValue);

    }

    private static int fib_memo_helper(int n,int [] fibMemoArray,int junkValue){
        if(n==0 || n==1){
            return fibMemoArray[n];
        }
        if(fibMemoArray[n]!=junkValue){
            return fibMemoArray[n];
        }
        if(DEBUG){
            System.out.println("Junk in "+n);
        }
        fibMemoArray[n-2] = fib_memo_helper(n-2,fibMemoArray,junkValue);
        fibMemoArray[n-1] = fib_memo_helper(n-1,fibMemoArray,junkValue);
        fibMemoArray[n] = fibMemoArray[n-1]+fibMemoArray[n-2];
        return fibMemoArray[n];

    }

    public static int fibo_dp(int n){
        if(n==0 || n==1){
            return 1;
        }
        int [] fibDpArray = new int[n+1];
        fibDpArray[0]=1;
        fibDpArray[1]=1;
        for(int i = 2 ; i<=n ; i++){
            fibDpArray[i] = fibDpArray[i-1]+fibDpArray[i-2];
        }
        return fibDpArray[n];
    }

    public static int fibo_dp_optimized(int n){
        if(n==0 || n==1){
            return 1;
        }
        int prev2 = 1;//fib[0]
        int prev1 = 1;//fib[1];
        int current = 0;
        for(int i = 2; i<=n; i++){
            current = prev2 + prev1;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void main(String [] args){
        System.out.println("Welcome to seeing Fibonacci in a new light");
        int valueOfn = getNForFetchingFibonacci();
        System.out.println("Got n as : "+valueOfn);
        int valueOfFibNRec  = fibo_rec(valueOfn);
        System.out.println("Value of "+valueOfn+"th Fibonacchi number found recursively is : "+valueOfFibNRec);
        int valueOfFibMem = fib_memo(valueOfn);
        System.out.println("Value of "+valueOfn+"th Fibonacchi number found by memoization is : "+valueOfFibMem);
        int valueOfFibDp = fibo_dp(valueOfn);
        System.out.println("Value of "+valueOfn+"th Fibonacchi number found by dp is : "+valueOfFibDp);
        int valueOfFibDpOpt = fibo_dp_optimized(valueOfn);
        System.out.println("Value of "+valueOfn+"th Fibonacchi number found by dp is : "+valueOfFibDpOpt);
    }



}
