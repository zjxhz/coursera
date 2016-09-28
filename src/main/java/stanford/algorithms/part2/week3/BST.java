package stanford.algorithms.part2.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wayne on 9/15/16.
 */
public class BST {
    public static void main(String[] args) {
        Scanner in = new Scanner(
                ClassLoader.getSystemResourceAsStream("testdata/stanford/algorithms/part2/week3/bst_ps3.txt"));
        int N = in.nextInt();
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            Node node = new Node(i, in.nextInt());
            nodes.add(node);
        }

        int min = findMin(nodes);
        System.out.println(min);
    }

    private static List<Node> getSubTree(List<Node> nodes, int start, int end) {
        List<Node> subTree = new ArrayList<>();
        for (int i = start; i < end; i++) {
            subTree.add(nodes.get(i));
        }
        return subTree;
    }


    private static int findMin(List<Node> nodes) {
        if (nodes.isEmpty()) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nodes.size(); i++) {
            List<Node> leftTree = getSubTree(nodes, 0, i);
            List<Node> rightTree = getSubTree(nodes, i + 1, nodes.size());
            int minLeft = findMin(leftTree);
            int minRight = findMin(rightTree);

            if (minLeft + minRight < min) {
                min = minLeft + minRight;
            }
        }
        return min + probabilityToRoot(nodes);

    }

    private static int probabilityToRoot(List<Node> nodes) {
        int sum = 0;
        for (Node node : nodes) {
            sum += node.probability;
        }
        return sum;
    }


}

class Node {
    int key;
    int probability;

    public Node(int key, int probability) {
        this.key = key;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", probability=" + probability +
                '}';
    }
}