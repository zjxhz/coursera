package stanford.algorithms.part2.week6;

import java.util.*;

/**
 * Created by wayne on 10/11/16.
 */
public class TwoSetsBacktrack {
    public static void main(String[] args) {
        TwoSetCondition condition = new TwoSetCondition("testdata/stanford/algorithms/part2/week6/2sat2.txt");
        TwoSetsBacktrack backtrack = new TwoSetsBacktrack(condition);
        boolean satisfiable = backtrack.eval();
        System.out.println(satisfiable);
    }

    //    private ChoicePoint choicePoint = new ChoicePoint(1, true);//todo delete
    private Set<Integer> permFlags = new HashSet<>();
    private Set<Integer> tempFlags = new HashSet<>();

    private Stack<ChoicePoint> choicePoints = new Stack<>();
    private LinkedHashMap<ChoicePoint, Set<Integer>> choicePointMap = new LinkedHashMap<>();
    private List<Integer> tempIndexes = new LinkedList<>();
    private TwoSetCondition condition;
    private boolean[] setFlags;
    private boolean[] variables;

    public TwoSetsBacktrack(TwoSetCondition condition) {
        this.condition = condition;
        setFlags = new boolean[condition.size()];
        variables = new boolean[condition.size()];
    }

    public boolean eval() {
        int max = 0;
        for (int i = 0; i < condition.size(); i++) {
            TwoSet twoSet = condition.get(i);
            if (neitherIsSet(twoSet)) {
                createFirstChoicePoint(i, twoSet.one);
                setFirst(twoSet);
                setOrForceAnother(i, twoSet);
            } else if (bothAreSet(twoSet)) {
                tempFlags.add(getRealIndex(twoSet.one));
                tempFlags.add(getRealIndex(twoSet.two));
                if (!twoSet.met(variables)) {
                    int backTrackPosition = backTrack();
                    if(i > max)
                    {
                        max = i;
                        System.out.printf("backtracking to %s from %d\n", backTrackPosition, i);
                    }

                    if (backTrackPosition == -1) {
                        return false;
                    } else {
                        i = backTrackPosition - 1;
                    }
                }
            } else if (isEitherOneSet(twoSet)) {
                setOrForceAnother(i, twoSet);
            } else {
                assert false;
            }
        }
        return true;
    }

    private void setOrForceAnother(int i, TwoSet twoSet) {
        if (isAlwaysTrue(twoSet)) {
            createSecondChoicePoint(i, twoSet.two);
            setAnother(twoSet);
        } else {
            forceAnother(twoSet);
        }
    }

    private int backTrack() {
        if (choicePoints.isEmpty()) {
            return -1;
        }
        ChoicePoint choicePoint = choicePoints.pop();
        choicePointMap.remove(choicePoint);
        undo(choicePoint);
        flip(choicePoint);
        return choicePoint.index;
    }


    private void flip(ChoicePoint choicePoint) {
        int i = choicePoint.index;
        TwoSet twoSet = condition.get(i);
        if (choicePoint.first) {
            flip(twoSet.one);
        } else {
            flip(twoSet.two);
        }
    }

    private void flip(int i) {
        set(i, !val(i));
    }

    private boolean val(int i) {
        return variables[getRealIndex(i)];
    }

    private void undo(ChoicePoint choicePoint) {
//        permFlags.clear();
//        for (int i = 0; i <= choicePoint.index; i++) {
//            TwoSet twoSet = condition.get(i);
//            permFlags.add(getRealIndex(twoSet.one));
//            permFlags.add(getRealIndex(twoSet.two));
//            if (i == choicePoint.index) {
//                if (choicePoint.first) {
//                    permFlags.add(getRealIndex(twoSet.one));
//                }
//                if (!choicePoint.first) {
//                    permFlags.add(getRealIndex(twoSet.two));
//                }
//            }
//        }
//        if (!choicePoint.first) {
//            TwoSet twoSet = condition.get(choicePoint.index);
//            permFlags.add(getRealIndex(twoSet.one));
//        }
        for (int i : tempFlags) {
            if (isTempFlag(i)) {
                setFlags[i] = false;
            }
        }

    }

    private boolean isTempFlag(int index) {
        for(Set<Integer> permSet : choicePointMap.values()){
            if(permSet.contains(index)){
                return false;
            }
        }
        return true;
    }

    private void createFirstChoicePoint(int clauseIndex, int variableIndex) {
        ChoicePoint choicePoint = new ChoicePoint(clauseIndex, true);
        choicePoints.add(choicePoint);
        choicePointMap.put(choicePoint, new HashSet<>(tempFlags));
//        choicePoint.index = i;
//        choicePoint.first = true;
//        choicePoint.tracked = false;
//        tempIndexes.clear();
//        permFlags.add(getRealIndex(variableIndex));
//        permFlags.addAll(tempFlags);
        tempFlags.clear();
    }

    private boolean bothAreSet(TwoSet twoSet) {
        return isOneSet(twoSet) && isTwoSet(twoSet);
    }

    private void forceAnother(TwoSet twoSet) {
        if (isOneSet(twoSet)) {
            forceTwo(twoSet);
        } else {
            forceOne(twoSet);
        }
    }

    private void forceOne(TwoSet twoSet) {
        force(twoSet.one);
    }

    private void force(int i) {
        int index = getRealIndex(i);
        if (i < 0) {
            variables[index] = false;
        } else {
            variables[index] = true;
        }
        tempFlags.add(index);
        setFlags[index] = true;
    }


    private void forceTwo(TwoSet twoSet) {
        force(twoSet.two);
    }

    private void setAnother(TwoSet twoSet) {
        if (isOneSet(twoSet)) {
            set(twoSet.two);
        } else {
            set(twoSet.one);
        }
    }

    private boolean isAlwaysTrue(TwoSet twoSet) {
        if (isOneSet(twoSet)) {
            if (twoSet.isOneMet(variables)) {
                return true;
            }
        }
        if (isTwoSet(twoSet)) {
            if (twoSet.isTwoMet(variables)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOneSet(TwoSet twoSet) {
        return isSet(twoSet.one);
    }

    private boolean isTwoSet(TwoSet twoSet) {
        return isSet(twoSet.two);
    }

    private boolean isSet(int index) {
        return setFlags[getRealIndex(index)];
    }

    private int getRealIndex(int index) {
        return Math.abs(index) - 1;
    }

    private boolean isEitherOneSet(TwoSet twoSet) {
        return isOneSet(twoSet) || isTwoSet(twoSet);
    }

    private void setFirst(TwoSet twoSet) {
        set(twoSet.one);
    }

    private void setEither(TwoSet twoSet) {
        int i = twoSet.one;
        if (Math.random() > 0.5) {
            i = twoSet.two;
        }
        set(i);
    }

    /**
     * Sets variable at i with a random value and set the flag.
     */
    private void set(int i) {
        set(i, randomBoolean());
    }

    private void set(int i, boolean b) {
        int index = getRealIndex(i);
        variables[index] = b;
        setFlags[index] = true;
        tempFlags.add(index);
    }

    private boolean randomBoolean() {
        return Math.random() > 0.5 ? true : false;
    }

    private void createSecondChoicePoint(int clauseIndex, int variableIndex) {
        ChoicePoint choicePoint = new ChoicePoint(clauseIndex, false);
        choicePoints.add(choicePoint);
        choicePointMap.put(choicePoint, new HashSet<>(tempFlags));
//        choicePoint.index = i;
//        choicePoint.first = false;
//        choicePoint.tracked = false;
//        tempIndexes.clear();
//        permFlags.add(getRealIndex(variableIndex));
//        permFlags.addAll(tempFlags);
        tempFlags.clear();
    }

    private boolean neitherIsSet(TwoSet twoSet) {
        return !isOneSet(twoSet) && !isTwoSet(twoSet);

    }
}

class ChoicePoint {
    int index;
    boolean first = true;

    public ChoicePoint(int i, boolean first) {
        index = i;
        this.first = first;
    }

    @Override
    public String toString() {
        return "ChoicePoint{" +
                "index=" + index +
                (first ? ", first" : ", second") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChoicePoint that = (ChoicePoint) o;

        if (index != that.index) return false;
        return first == that.first;

    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + (first ? 1 : 0);
        return result;
    }
}
