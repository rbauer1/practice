package dataStructures;

import java.util.ArrayList;

/**
 * Implementation of heap as per wikipedia's Heapsort page
 */
public class Heap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public Heap(){
        heap = new ArrayList<T>();
    }

    public Heap(T[] data){
        heap = new ArrayList<T>();
        for(T datum : data){
            heap.add(datum);
        }
        heapify(heap, heap.size());
    }

    public Heap(ArrayList<T> data){
        heap = new ArrayList<T>(data);
        heapify(heap, heap.size());
    }

    public void heapSort(){
        heapSort(heap, heap.size());
    }

    public void heapSort(ArrayList<T> heap){
        heapSort(heap, heap.size());
    }

    public void push(T data){
        heap.add(data);
        siftUp(heap, 0, heap.size()-1);
    }

    public T poll(){
        final T min = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        siftDown(heap, 0, heap.size()-1);
        return min;
    }

    public T peek(){
        return heap.get(0);
    }

    public boolean isEmpty(){
        return heap.size() == 0;
    }

    private void heapSort(ArrayList<T> heap, int end){
        heapify(heap, end);
        end--;
        while(end > 0){
            swap(heap, end, 0);
            end--;
            siftDown(heap, 0, end);
        }
        final int length = heap.size();
        for(int i=0; i< length/2; i++){
            swap(heap, i, length-i-1);
        }
    }

    private void heapify(ArrayList<T> heap, final int length){
        int start = (length-2)/2;
        while(start >= 0){
            siftDown(heap, start, length-1);
            start--;
        }
    }


    private void siftDown(ArrayList<T> heap, final int start, final int end){
        int root = start;
        while(root * 2 + 1 <= end){ //while root has at < 0 children
            final int child = root * 2 + 1;
            int swapChild = root;
            if(heap.get(swapChild).compareTo(heap.get(child)) > 0){
                swapChild = child;
            }
            if(child + 1 <= end && heap.get(swapChild).compareTo(heap.get(child+1)) > 0){
                swapChild = child + 1;
            }
            if(swapChild == root){
                return;
            }
            swap(heap, root, swapChild);
            root = swapChild;
        }
    }

    private void siftUp(ArrayList<T> heap, final int start, final int end){
        int child = end;
        while(child > start){
            final int parent = (child-1)/2;
            if(heap.get(parent).compareTo(heap.get(child)) > 0){
                swap(heap, parent, child);
                child = parent;
            }else{
                return;
            }
        }
    }

    private void swap(ArrayList<T> heap, final int p1, final int p2) {
        final T temp = heap.get(p1);
        heap.set(p1, heap.get(p2));
        heap.set(p2, temp);
    }

    public String toString(){
        return heap.toString();
    }
}
