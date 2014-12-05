package dataStructures;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by riley on 11/14/14.
 */
public class Hash<T extends Serializable>{
    private int[] randomTable = new int[256];

    public Hash(T dataType){
        initializeRandomTable();
    }

    private void initializeRandomTable(){
        final Random generator = new Random();
        for(int i=0; i<randomTable.length; i++) {
            randomTable[i] = generator.nextInt(Integer.MAX_VALUE);
        }
    }

}
