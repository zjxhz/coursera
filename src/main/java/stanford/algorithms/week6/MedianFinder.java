package stanford.algorithms.week6;

import java.util.Scanner;

/**
 * Created by wayne on 8/11/16.
 */
public class MedianFinder {
    private Heap minHeap = Heap.createMinHeap();
    private Heap maxHeap = Heap.createMaxHeap();

    public int offer(int i) {
        if(minHeap.isEmpty()){
            minHeap.insert(i);
            return minHeap.poll();
        }
        if(maxHeap.isEmpty()){
            int min = minHeap.poll();
            if(i > min){
                minHeap.insert(i);
                maxHeap.insert(minHeap.exact());
            } else {
                maxHeap.insert(i);
            }
            return maxHeap.poll();
        }

        int max = maxHeap.poll();
        if(i < max){
            maxHeap.insert(i);
            if(maxHeap.size() > minHeap.size()){
                minHeap.insert(maxHeap.exact());
            }
        } else {
            minHeap.insert(i);
            if(minHeap.size() > maxHeap.size() + 1){
                maxHeap.insert(minHeap.exact());
            }
        }

        if(minHeap.size() == maxHeap.size()){
            return maxHeap.poll();
        }
        return minHeap.poll();

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream("testdata/week6/Median.txt"));
        int sum = 0;
        MedianFinder mf = new MedianFinder();
        while(in.hasNext()){
            int m = mf.offer(in.nextInt());
//            System.out.println(m);
            sum += m;
        }
        System.out.println(sum % 10000);
    }
}
