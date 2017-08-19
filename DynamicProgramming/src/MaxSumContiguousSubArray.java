public class MaxSumContiguousSubArray {
    private static boolean DEBUG = false;

    private static void print2DArray(int [][] array){
        System.out.println();
        for(int i = 0 ; i <array.length ; i++){
            for(int j = 0 ; j<array[i].length ; j++){
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
    }



    public static int max_sum_contigous_array_2D(int[] array){
        int max = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        int [][] sum_array = new int[array.length][array.length];
        for(int i = 0 ; i<array.length ; i++){
            for(int j = i ; j<array.length ; j++){
                if(i==j){
                    sum_array[i][j] = array[i];
                }else{
                    sum_array[i][j]= sum_array[i][j-1]+array[j];
                }
                if(max<sum_array[i][j]){
                    max = sum_array[i][j];
                    start = i;
                    end = j;
                }
                if(DEBUG){
                    print2DArray(sum_array);
                    System.out.print("\nMax : "+max+" start : "+start+" end : "+end);
                }
            }
        }
        return max;
    }

    private static int maximumOfTwo(int x,int y){
        return x>y?x:y;
    }

    public static final int max_sum_contiguous_array(int [] array){
        int max = array[0];
        int current_max = array[0];
        for(int i = 1 ; i<array.length ; i++){
            current_max = maximumOfTwo(current_max+array[i],array[i]);
            max = maximumOfTwo(max,current_max);
        }
        return max;
    }

    public static void main(String [] args){
        int [] array = {-2,-7,3,-11,2,0,-5,8};
        int max = max_sum_contigous_array_2D(array);
        System.out.println("Maximum sum in the contiguous subarray in O(n^2) : "+max);
        max = max_sum_contiguous_array(array);
        System.out.println("Maximum sum in the contiguous subarray in O(n) : "+max);

    }
}
