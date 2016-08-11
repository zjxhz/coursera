package stanford.algorithms.week6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huanze on 8/11/2016.
 */
public class Heap {

    private Heap(Comparator<Integer> comparator){
        this.comparator = comparator;
    }

    private Heap(){
    }

    List<Integer> data = new ArrayList<>();
    private Comparator<Integer> comparator = (o1, o2) -> o1 - o2;

    public int poll(){
        return data.get(0);
    }
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
        int root = data.get(0);
        int last = data.remove((data.size() - 1));
        if(data.size() > 0){
            data.set(0, last);
            bubbleDown(0);
        }
        return root;
    }

    private void bubbleDown(int index) {
        int val = data.get(index);
        if(isLeaf(index)){
            //done
        } else if(hasOnlyLeftChild(index)){
            if(comparator.compare(val, getLeftChild(index)) > 0){
                swap(index, getLeftChildIndex(index));
            }
        } else if(hasRightChild(index)){ //meaning it has left child too
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
    }

    private boolean hasOnlyLeftChild(int index) {
        return hasLeftChild(index) && !hasRightChild(index);
    }

    private boolean isLeaf(int index) {
        return !hasLeftChild(index);
    }

    private int getRightChild(int index) {
        return data.get(getRightChildIndex(index));
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < data.size();
    }

    private int getRightChildIndex(int index) {
        return 2 * (index + 1);
    }

    private int getLeftChild(int index) {
        return data.get(getLeftChildIndex(index));

    }

    private boolean hasLeftChild(int index) {
        return  getLeftChildIndex(index) < data.size();
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    public static void main(String[] args) {

    }

    public static Heap createMinHeap() {
        return new Heap();
    }

    public static Heap createMaxHeap() {
        return new Heap((o1,o2) -> o2 - o1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
