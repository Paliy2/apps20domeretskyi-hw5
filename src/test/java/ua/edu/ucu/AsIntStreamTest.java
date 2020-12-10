package ua.edu.ucu;

import org.junit .Test;
import org.junit.Before;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private IntStream stream;
    private int[] testIntArr;

    @Before
    public void setUp() {
        testIntArr = new int[]{1, -5, 10, 5, -1, 0, 0, -4, 7};
    }

    @Test
    public void testOf() {
        stream = AsIntStream.of(testIntArr);
        assertArrayEquals(testIntArr, stream.toArray());

        stream = AsIntStream.of(1420);
        assertArrayEquals(new int[]{1420}, stream.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorOf() {
        stream = AsIntStream.of();
        stream.toArray();
    }

    @Test
    public void testAverage() {
        stream = AsIntStream.of(testIntArr);
        double result = stream.average();
        assertEquals(1.44, result, 0.01);
    }

    @Test
    public void testMin() {
        stream = AsIntStream.of(testIntArr);
        int result = stream.min();
        assertEquals(-5, result);
    }

    @Test
    public void testMax() {
        stream = AsIntStream.of(testIntArr);
        int expResult = 10;
        int result = stream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        stream = AsIntStream.of(testIntArr);
        assertEquals(9, stream.count());
    }

    @Test
    public void testSum() {
        stream = AsIntStream.of(testIntArr);
        int result = stream.sum();
        assertEquals(13, result);
    }

    @Test
    public void testFilter() {
        stream = AsIntStream.of(testIntArr);
        stream = AsIntStream.of(testIntArr).filter(x -> x < 0);

        int result = stream.min();
        assertEquals(-5, result);

        stream = AsIntStream.of(testIntArr).filter(x -> x >= -4);
        result = stream.max();
        assertEquals(10, result);
    }

    @Test
    public void forEach() {
        StringBuilder str = new StringBuilder();
        stream = AsIntStream.of(testIntArr);
        stream.forEach(str::append);
        assertEquals("1-5105-100-47", str.toString());
    }

    @Test
    public void map() {
        stream = AsIntStream.of(testIntArr);
        stream = stream.map(x -> ++x);
        int newMin = stream.min();
        assertEquals(newMin, -4);
//        assertEquals(newMax, 11);
    }

    @Test
    public void toArray() {
        stream = AsIntStream.of(testIntArr);
        int[] res = stream.filter(x -> x > 0).toArray();
        int[] exp = {1, 10, 5, 7};
//        System.out.println(Arrays.toString(res));
        assertArrayEquals(exp, res);
    }

    @Test
    public void flatMap() {
        stream = AsIntStream.of(testIntArr);
        stream = stream.flatMap(x -> AsIntStream.of(x - 1, x + 1));
        int[] expected = {0, 2, -6, -4, 9, 11, 4, 6, -2, 0, -1, 1, -1, 1, -5, -3, 6, 8};
        assertArrayEquals(expected, stream.toArray());
    }

    @Test
    public void reduce() {
        stream = AsIntStream.of(testIntArr);
        int actual = stream.reduce(0, (sum, x) -> sum -= (x * x));
        int expected = -217;
        assertEquals(actual, expected);

    }


}