import java.util.Arrays;

/**
 * An ugly number is a number whose
 * prime factors are 2,3 and 5 only
 * By convention 1 is included
 */
public class UglyNumbers {
    public static final boolean DEBUG = true;

    public static int getPowerOf(int num, int div){
        int pow = 0;
        while(num%div==0){
            pow++;
            num = num/div;
        }

        return pow;
    }

    public static boolean isNumberUgly(int number){
        boolean ugly = false;

        int pow5 = getPowerOf(number,5);
        int pow3 = getPowerOf(number,3);
        int pow2 = getPowerOf(number,2);
        int newNum = (int)(Math.pow(2,pow2)*Math.pow(3,pow3)*Math.pow(5,pow5));
        ugly = newNum == number;
        if(DEBUG){
            System.out.println("Number : "+number+" 2^"+pow2+" * 3^"+pow3+" * 5^"+pow5+(ugly?" -> ugly":" * something -> not ugly"));
        }
        return ugly;
    }

    public static int nthUglyNumber(int n){
        int p = 0;
        int k = 1;
        while(n>0 && p!=n){
            if(isNumberUgly(k)){
                p++;
            }
            k++;
        }
        return k-1;
    }

    public static int nthUglyNumber_dp(int n){
        int [] ugly = new int[n];

        int twoIndex = 0;
        int threeIndex = 0;
        int fiveIndex = 0;

        ugly[0] = 1;
        int next_ugly = 0;

        for(int i = 1;i<n;i++){
            next_ugly = min(ugly[twoIndex]*2,ugly[threeIndex]*3,ugly[fiveIndex]*5);
            ugly[i] = next_ugly;
            if(next_ugly==ugly[twoIndex]*2){
                twoIndex++;
            }
            if(next_ugly==ugly[threeIndex]*3){
                threeIndex++;
            }
            if(next_ugly==ugly[fiveIndex]*5){
                fiveIndex++;
            }
            if(DEBUG){
                System.out.println("Index 2 : "+twoIndex+" Index 3 : "+threeIndex+" Index 5 : "+fiveIndex);
                System.out.println("Ugly series : "+ Arrays.asList(ugly));
            }
        }

        return ugly[n-1];
    }

    //private static Strin

    public static int min(int a , int b , int c){
        return a<b?(a<c?a:c):(b<c?b:c);
    }

    public static void main(String [] args){
        /*int n = 12;
        int num = nthUglyNumber(n);
        System.out.println(n+"th Ugly number : "+num);
        */
        for(int i = 14 ; i<=14 ; i++){
            System.out.println("Ugly number "+i+" thru normal "+nthUglyNumber(i)+" thru dp "+nthUglyNumber_dp(i));
        }
    }
}
