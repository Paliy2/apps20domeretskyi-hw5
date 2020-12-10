package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.ArrayList;
import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {

    private final Iterator<Integer> stream;
    private final IntToIntStreamFunction operator;
    private StreamIterator tmpIterator = null;

    public FlatMapIterator(Iterator<Integer> stream, IntToIntStreamFunction operator) {
        this.stream = stream;
        this.operator = operator;
    }

    @Override
    public boolean hasNext() {
        if (tmpIterator != null && tmpIterator.hasNext()) {
            return true;
        }
        if (!stream.hasNext()) {
            return false;
        }

        AsIntStream newStream = (AsIntStream)
                operator.applyAsIntStream(stream.next());
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value : newStream.toArray()) {
            arrayList.add(value);
        }
        tmpIterator = new StreamIterator(arrayList);
        return tmpIterator.hasNext();
    }

    @Override
    public Integer next() {
        return tmpIterator.next();
    }

}