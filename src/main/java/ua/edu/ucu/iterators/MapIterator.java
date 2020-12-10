package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private final Iterator<Integer> stream;
    private final IntUnaryOperator operator;

    public MapIterator(Iterator<Integer> iter, IntUnaryOperator operator) {
        this.operator = operator;
        this.stream = iter;
    }

    @Override
    public boolean hasNext() {
        return stream.hasNext();
    }

    @Override
    public Integer next() {
        return operator.apply(stream.next());
    }
}
