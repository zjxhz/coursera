package stanford.algorithms.week6;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanze on 8/11/2016.
 */
public class Heap {

    List<Integer> data = new ArrayList<>();

    public void insert(int i) {
        data.add(i);
        bubbleUp(data.size() - 1);
    }

    private void bubbleUp(int index) {
        if(index == 0){ //root
            return;
        }
        int inserted  = data.get(index);
        int parentIndex = (index  - 1)/ 2;
        int parent = data.get(parentIndex);
        if(inserted < parent){
            swap(index, parentIndex);
        }
        bubbleUp(parentIndex);
    }

    private void swap(int index, int parentIndex) {
        int temp = data.get(index);
        data.set(index, data.get(parentIndex));
        data.set(parentIndex, temp);
    }

    public int size() {
        return data.size();
    }

    public int exactMin() {
        int min = data.get(0);
        int last = data.remove((data.size() - 1));
        if(data.size() > 0){
            data.set(0, last);
            bubbleDown(0);
        }
        return min;
    }

    private void bubbleDown(int index) {
        int val = data.get(index);
        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);
        if(leftChild < rightChild && val > leftChild){
            swap(index, getLeftChildIndex(index));
            bubbleDown(getLeftChildIndex(index));
        } else if(rightChild < leftChild && val > rightChild){
            swap(index, getRightChildIndex(index));
            bubbleDown(getRightChildIndex(index));
        }
    }

    private int getRightChild(int index) {
        if(hasRightChild(index)){
            return data.get(getRightChildIndex(index));
        }
        return Integer.MAX_VALUE;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < data.size();
    }

    private int getRightChildIndex(int index) {
        return 2 * (index + 1);
    }

    private int getLeftChild(int index) {
        if(hasLeftChild(index)){
            return data.get(getLeftChildIndex(index));
        }
        return Integer.MAX_VALUE;

    }

    private boolean hasLeftChild(int index) {
        return  getLeftChildIndex(index) < data.size();
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    public static void main(String[] args) {

    }
}
