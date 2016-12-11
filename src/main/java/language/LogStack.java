package language;

import java.util.Stack;

final class LogStack extends Stack<Integer> {

    public synchronized Integer popSeed() {
        Integer popped = super.pop();
        System.out.println("Pop seed = " + popped);
        return popped;
    }

    @Override
    public synchronized Integer pop() {
        Integer popped = super.pop();
        System.out.println("Pop = " + popped);
        return popped;
    }

    @Override
    public synchronized boolean isEmpty() {
        boolean isEmpty = super.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
        return isEmpty;
    }
}
