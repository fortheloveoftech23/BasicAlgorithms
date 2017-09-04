public class LongestIncreasingSubsequence {

    public static final boolean DEBUG = true;
    private static int [] sSequence = {7,9,3,4,1,3,8,5,6};

    public static final int getLongestSequenceLength(int [] sequence){
        if(sequence == null || sequence.length==0){
            return 0;
        }
        int [] countKeeper = new int[sequence.length];
        countKeeper[0] = 1;
        int max = 1;

        for(int i = 1 ; i < sequence.length ; i++){
            for(int j = 0 ; j<i ; j ++){
                if(sequence[j]<sequence[i] && countKeeper[j]>=countKeeper[i]){
                    countKeeper[i] = countKeeper[j]+1;
                }
            }
            if(countKeeper[i] == 0){
                countKeeper[i] = 1;
            }
            if(max<countKeeper[i]){
                max = countKeeper[i];
            }
            if(DEBUG){
                for(int k = 0;k<countKeeper.length;k++){
                    System.out.print(countKeeper[k]+" ");
                }
                System.out.println();
            }
        }

        return max;
    }

    public static void main(String [] args){
        System.out.println("Longest Subsequence length : "+getLongestSequenceLength(sSequence));
    }

}
