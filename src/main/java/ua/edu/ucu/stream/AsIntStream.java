package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

import ua.edu.ucu.iterators.StreamIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    private final Iterator<Integer> stream;

    private AsIntStream(int... values) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value : values) {
            arrayList.add(value);
        }
        this.stream = new StreamIterator(arrayList);
    }

    private AsIntStream(Iterator<Integer> stream) {
        this.stream = stream;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        isEmpty();

        int totalSum = 0;
        int length = 0;

        while (this.stream.hasNext()) {
            totalSum += this.stream.next();
            length += 1;
        }

        return ((double) totalSum / length);
    }

    @Override
    public Integer max() {
        isEmpty();
        int max = Integer.MIN_VALUE;
        do {
            int next = stream.next();
            if (next > max) max = next;
        } while (stream.hasNext());
        return max;
    }

    @Override
    public Integer min() {
        isEmpty();
        int min = Integer.MAX_VALUE;
        do {
            int next = stream.next();
            if (next < min) min = next;
        } while (stream.hasNext());
        return min;
    }

    @Override
    public long count() {
        isEmpty();
        return reduce(0, (sum, val) -> sum += 1);
    }

    @Override
    public Integer sum() {
        isEmpty();
        return reduce(0, (sum, val) -> sum += val);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(stream, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (this.stream.hasNext()) {
            int element = this.stream.next();
            action.accept(element);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.stream, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.stream, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int tmp = identity;
        while (this.stream.hasNext()) {
            tmp = op.apply(tmp, this.stream.next());
        }
        return tmp;
    }

    @Override
    public int[] toArray() {
        isEmpty();

        ArrayList<Integer> resList = new ArrayList<>();
        do {
            resList.add(this.stream.next());
        } while (this.stream.hasNext());

        int[] finalResult = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            finalResult[i] = resList.get(i);
        }
        return finalResult;
    }

    public void isEmpty() throws IllegalArgumentException {
        if (!this.stream.hasNext()) {
            throw new IllegalArgumentException();
        }
    }
}