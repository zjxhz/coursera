package stanford.algorithms.part2.week6;

/**
 * Created by wayne on 10/11/16.
 */
public class TwoSet {
    int one;
    int two;

    public TwoSet(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public boolean met(boolean[] variables){
        return isOneMet(variables) || isTwoMet(variables);
    }

    private boolean check(boolean[] variables, int index){
        return index > 0 ? variables[index - 1] : !variables[Math.abs(index) - 1];
    }

    public int flip(boolean[] variables) {
        int index = one;
        if(Math.random() > 0.5){
            index = two;
        }
        index = Math.abs(index) - 1;//off by one
        variables[index] = !variables[index];
        return index;
    }

    @Override
    public String toString() {
        return "TwoSet{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }

    public boolean isOneMet(boolean[] variables) {
        return check(variables, one);
    }

    public boolean isTwoMet(boolean[] variables) {
        return check(variables, two);
    }
}
