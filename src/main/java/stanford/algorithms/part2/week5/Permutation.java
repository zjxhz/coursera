package stanford.algorithms.part2.week5;

import stanford.algorithms.part2.model.graph.Vertice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wayne on 10/6/16.
 */
public class Permutation<E> {
    public List<List<E>> generatePerm(List<E> original) {
        if (original.size() == 0) {
            List<List<E>> result = new ArrayList<List<E>>();
            result.add(new ArrayList<E>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<List<E>>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<E>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }


    public static void main(String[] args) {
        int [] ids = {1,2,3};
        List<Integer> list = Arrays.asList(1,2,3);
        list = new ArrayList<>(list);
        System.out.println(list);
        Permutation<Integer> p = new Permutation();
        System.out.println(p.generatePerm(list));
    }
}
