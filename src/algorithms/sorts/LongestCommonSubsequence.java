package algorithms.sorts;

/**
 * Created by riley on 11/16/14.
 */
public class LongestCommonSubsequence {
    /**
     * [][][0] is lcs.length, [][][1] = 0 if match, 1 if left, 2 if up
     */
    private int[][][] table;
    private String s1;
    private String s2;
    private boolean alreadyRan;

    public static void main(String[] args){
        LongestCommonSubsequence lcss = new LongestCommonSubsequence("ABCDEFG", "XBXAXCXDEXXG");
        System.out.println(lcss.findLCSSLength());
        System.out.println(lcss.findLCSS());
    }

    public LongestCommonSubsequence(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
        alreadyRan = false;
        table = new int[s1.length()+1][s2.length()+1][2];
        for(int i=0; i< s1.length(); i++){
            table[i][0][0] = 0;
        }
        for(int i=0; i< s2.length(); i++){
            table[0][i][0] = 0;
        }
    }

    public String findLCSS(){
        if(! alreadyRan){
            findLCSSLength();
        }
        final int maxLength = table[s1.length()][s2.length()][0];
        final char[] lcs = new char[maxLength];
        int i=s1.length()-1;
        int j=s2.length()-1;
        int k = lcs.length-1;
        while(k >= 0){
            if(table[i+1][j+1][1]==0){
                lcs[k] = s1.charAt(i);
                i--;
                j--;
                k--;
            }else if(table[i+1][j+1][1]==1){
                j--;
            }else{
                i--;
            }
        }
        return new String(lcs);
    }

    public int findLCSSLength(){
        for(int i=1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    table[i][j][0] = table[i - 1][j - 1][0] + 1;
                } else {
                    if (table[i - 1][j][0] >= table[i][j - 1][0]) {
                        table[i][j][0] = table[i - 1][j][0];
                        table[i][j][1] = 2;
                    } else {
                        table[i][j][0] = table[i][j - 1][0];
                        table[i][j][1] = 1;
                    }
                }
            }
        }
        alreadyRan = true;
        return table[s1.length()][s2.length()][0];
    }
}
