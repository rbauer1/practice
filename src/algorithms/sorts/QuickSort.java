package algorithms.sorts;

import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> {
    public static void main(String[] args){
        Integer[] testAr1 = {6,2,1,5,9,3,4,7};
        Integer[] testAr2 = {1,2,3,8,4,5,6,7};
        QuickSort<Integer> qs = new QuickSort<Integer>();
        qs.quickSort(testAr1);
        qs.quickSort(testAr2);
        System.out.println(Arrays.toString(testAr1));
        System.out.println(Arrays.toString(testAr2));
    }
    public void quickSort(T[] array){
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int start, int end){
        if(start < end){
            final int pivot = partition(array, start, end);
            quickSort(array, start, pivot-1);
            quickSort(array, pivot+1, end);
        }
    }

    private int partition(T[] array, int start, int end){
        final int pivot = start;
        start++;
        while(start <= end){
            while(start <= end && array[start].compareTo(array[pivot]) < 1){
                start++;
            }
            while(end >= start && array[end].compareTo(array[pivot])  > -1){
                end--;
            }
            if(start <= end) {
                swap(array, start, end);
            }
        }
        swap(array, pivot, end);
        return end;
    }

    private void swap(T[] array, int p1, int p2){
        final T temp = array[p1];
        array[p1] = array[p2];
        array[p2] = temp;
    }
}

