package stanford.algorithms.week6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huanze on 8/11/2016.
 */
public class Heap {

    public Heap(Comparator<Integer> comparator){
        this.comparator = comparator;
    }

    public Heap(){
    }

    List<Integer> data = new ArrayList<>();
    private Comparator<Integer> comparator = (o1, o2) -> o1 - o2;

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
        if(comparator.compare(inserted, parent) < 0){
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

    public int exact() {
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
        if(comparator.compare(leftChild, rightChild) < 0 && comparator.compare(val, leftChild) > 0){
            swap(index, getLeftChildIndex(index));
            bubbleDown(getLeftChildIndex(index));
        } else if(comparator.compare(rightChild, leftChild) < 0 && comparator.compare(val, rightChild) > 0 ){
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
