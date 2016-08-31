package stanford.algorithms.part2.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wayne on 8/31/16.
 */
public class JobsReader {
    public static List<Job> read(String resource){
        List<Job> jobs = new ArrayList<>();
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        int N = in.nextInt();
        for(int i = 0; i < N; i++){
            Job job = new Job();
            job.weight = in.nextInt();
            job.length = in.nextInt();
            jobs.add(job);
        }
        return jobs;
    }
}
