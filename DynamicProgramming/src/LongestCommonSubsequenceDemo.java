import java.util.Scanner;

public class LongestCommonSubsequenceDemo {

    /**
     * Turn the DEBUG flag on to see some
     * extra console output
     */
    public static final boolean DEBUG = false;
    public static String getString(String argument){
        System.out.print("Enter "+argument+" : ");
        Scanner scanner = new Scanner(System.in);
        String str = "";
        try{
            str = scanner.nextLine();
            if(str==null || str.length()==0){
                throw new NullPointerException();
            }
        }catch (Exception e){
            if(DEBUG) {
                e.printStackTrace();
            }
            System.out.println("Don't enter something stupid, daddy's watching");
            System.exit(1);
        }
        return str;

    }

    private static int max(int x , int y){
        return x>y?x:y;
    }

    public static int lcs_rec(String str1,String str2){
        if(str1.length()==0 || str2.length()==0){
            return 0;
        }
        char x = str1.charAt(str1.length()-1);
        char y = str2.charAt(str2.length()-1);
        if(x==y){
            return 1+lcs_rec(str1.substring(0,str1.length()-1),str2.substring(0,str2.length()-1));
        }else{
            return(max(lcs_rec(str1.substring(0,str1.length()-1),str2),lcs_rec(str1,str2.substring(0,str2.length()-1))));
        }
    }

    public static int lcs_memo(String str1,String str2){
        int[][] lcs_array = new int[str1.length()+1][str2.length()+1];
        for(int i = 0; i <=str1.length() ; i++){
            for(int j = 0 ; j<=str2.length() ; j++){
                if(i==0 || j==0){
                    lcs_array[i][j] = 0;
                }else{
                    lcs_array[i][j] = -1;
                }
            }
        }
        return lcs_memo_helper(str1,str2,lcs_array);
    }

    private static int lcs_memo_helper(String str1,String str2,int[][] lcs_array){
        if(lcs_array[str1.length()][str2.length()]!=-1){
            return lcs_array[str1.length()][str2.length()];
        }
        if(str1.charAt(str1.length()-1)==str2.charAt(str2.length()-1)){
            return 1+lcs_memo_helper(str1.substring(0, str1.length()-1),str2.substring(0,str2.length()-1),lcs_array);
        }
        lcs_array[str1.length()][str2.length()] = max(lcs_memo_helper(str1.substring(0, str1.length()-1),str2,lcs_array),
                lcs_memo_helper(str1,str2.substring(0,str2.length()-1),lcs_array));
        return lcs_array[str1.length()][str2.length()];
    }

    public static int lcs_dp(String str1,String str2){
        int[][] lcs_array = new int[str1.length()+1][str2.length()+1];
        for(int i = 0; i <=str1.length() ; i++){
            for(int j = 0 ; j<=str2.length() ; j++){
                if(i==0 || j==0){
                    lcs_array[i][j] = 0;
                }
            }
        }
        for(int i = 1; i <=str1.length() ; i++){
            for(int j = 1 ; j<=str2.length() ; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    lcs_array[i][j] = 1+lcs_array[i-1][j-1];
                }else{
                    lcs_array[i][j] = max(lcs_array[i-1][j],lcs_array[i][j-1]);
                }
            }
        }
        return lcs_array[str1.length()][str2.length()];
    }

    public static void main(String [] args){
        String str1 = getString("First one");
        String str2 = getString("Second one");
        System.out.println("Received\n1: "+str1+"\n2: "+str2);
        int lcsRec = lcs_rec(str1,str2);
        System.out.println("LCS using recursion is : "+lcsRec);
        int lcsMemo = lcs_memo(str1,str2);
        System.out.println("LCS using memoization is : "+lcsMemo);
        int lcsDp = lcs_dp(str1,str2);
        System.out.println("LCS using dp is : "+lcsDp);
    }
}
