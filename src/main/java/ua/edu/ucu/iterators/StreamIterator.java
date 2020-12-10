package ua.edu.ucu.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private final ArrayList<Integer> values;
    private int counter;

    public StreamIterator(ArrayList<Integer> values) {
        this.values = values;
        this.counter = 0;
    }

    @Override
    public boolean hasNext() {
//        System.out.println(counter);
//        System.out.println(values);
//        System.out.println(this.counter < values.size());
        return this.counter < values.size();
    }

    @Override
    public Integer next() {
        return values.get(counter++);
    }
}
