public class RodCutting {

    private static int cutRodRec(int n,int [] costMatrix){
        if(n<0){
            return 0;
        }
        int max = 0;
        for(int i = 0 ; i<n ; i++){
            int cost = costMatrix[i] + cutRodRec(n-i-1,costMatrix);
            if(cost>max){
                max = cost;
            }
        }
        return max;
    }

    private static int cutRodDP(int [] costMatrix){
        int n = costMatrix.length;
        int[] maxPrice = new int[n+1];//maxPrice[0]=0
        for(int i = 1 ; i<=n ;i++){
            for(int j = 0 ; j<i ;j++){
                if(costMatrix[j]+maxPrice[i-j-1]>maxPrice[i]){
                    maxPrice[i] = costMatrix[j]+maxPrice[i-j-1];
                }
            }
        }
        return maxPrice[n];
    }

    private static String getArrayString(int [] arr){
        String s = "";
        for(int x : arr){
            s=s+x+" ";
        }
        return s;
    }

    public static void main(String [] args){
        int cost[] = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println("Maximum Obtainable Value is "+ cutRodRec(cost.length, cost));
        System.out.println("Maximum Obtainable Value is "+ cutRodDP(cost));
    }
}
