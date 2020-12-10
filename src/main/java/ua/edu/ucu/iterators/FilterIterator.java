package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private final Iterator<Integer> stream;
    private final IntPredicate predicate;
    private Integer nextElem;

    public FilterIterator(Iterator<Integer> stream, IntPredicate predicate) {
        this.stream = stream;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (stream.hasNext()) {
            nextElem = stream.next();
            if (predicate.test(nextElem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return nextElem;
    }
}