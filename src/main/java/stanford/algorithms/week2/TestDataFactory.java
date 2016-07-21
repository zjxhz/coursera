package stanford.algorithms.week2;

import java.util.Scanner;

/**
 * Created by huanze on 7/21/2016.
 */
public class TestDataFactory {
    public static int [] loadData(String path, int size){
        int[] data = new int[size]; //we have been told that there are 10,000 numbers
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(path));
        int i = 0;
        while (in.hasNextInt()) {
            data[i++] = in.nextInt();
        }
        return data;
    }
}
