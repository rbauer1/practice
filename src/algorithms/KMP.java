package algorithms;

import java.util.Arrays;

/**
 * Created by riley on 11/16/14.
 */
public class KMP {

    public static void main(String[] args){
        System.out.println(find("ABCDABD", "ABC ABCDAB ABCDABCDABDE"));
    }

    public static int find(String word, String string){
        if(word.isEmpty() || string.isEmpty()){
            return -1;
        }
        int[] table = buildTable(word);
        System.out.println(Arrays.toString(table));
        int stringPosition = 0;
        int wordPosition = 0;
        while(stringPosition + wordPosition < word.length()) {
            if (word.charAt(wordPosition) == string.charAt(wordPosition + stringPosition)) {
                if (wordPosition == word.length() - 1) {
                    return stringPosition;
                }
                wordPosition++;
            } else if (table[wordPosition] > -1) {
                stringPosition += wordPosition - table[wordPosition];
                wordPosition = table[wordPosition];
            } else {
                wordPosition = 0;
                stringPosition++;
            }
        }
        return string.length();
    }

    private static int[] buildTable(String word){
        final int[] table = new int[word.length()];
        int position =  2;
        int current =   0;
        table[0] = -1;
        while(position < word.length()){
            if(word.charAt(position-1) == word.charAt(current)){
                current++;
                table[position] = current;
                position++;
            }else if(current > 0){
                current = table[current];
            }else{
                table[position] = 0;
                position++;
            }
        }
        return table;
    }
}
