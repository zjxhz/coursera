package stanford.algorithms.part2.week6;

/**
 * Created by wayne on 10/11/16.
 */
public class TwoSetsLocalSearch {
    public static void main(String[] args) {
//        TwoSetCondition condition = new TwoSetCondition("testdata/stanford/algorithms/part2/week6/2sat1.txt");
        TwoSetCondition condition = TwoSetCondition.createReducedConditions("testdata/stanford/algorithms/part2/week6/2sat6.txt");
        for(int k = 0; k < outerCounter(condition.size()); k++){
            TwoSetsLocalSearch localSearch = new TwoSetsLocalSearch(condition);
            if(localSearch.eval()){
                System.out.printf("Outer loop = " + k);
                return;
            }
        }
        System.out.printf("Not satisfied.");
    }

    private TwoSetCondition condition;
    public TwoSetsLocalSearch(TwoSetCondition condition){
        this.condition = condition;

    }

    private boolean [] variables;
    public boolean eval() {
        for(long k = 0; k < 2 * Math.pow(condition.size(), 2); k++){
            if(k % 100_000 == 0){
                System.out.println("round: " + k);
            }
            if(condition.met(variables)){
                System.out.println("Satisfied in round: " + k);
                return true;
            } else {
                condition.flip(variables);
            }
        }
        return false;
    }


    private static int outerCounter(int n) {
        return 1;
//        return (int)(Math.log(n) / Math.log(2));
    }
}
