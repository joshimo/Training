package collections;

import joshimo.io.utils.Level;
import joshimo.io.utils.WindowLogger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CollectionsTest {

    long time;

    WindowLogger logger;

    abstract class AbstractValueClass {
        public final Integer i = 20;
        public final String s = "abc";
        public int value;
        private String[] str = {"abcde", "vwwer", "ssssassf", "№5766687-2502-АТХ.С"};

        public AbstractValueClass(int value) {
            this.value = value;
        }
    }

    class BadValueClass extends AbstractValueClass {

        public BadValueClass(int value) {
            super(value);
        }

        @Override
        public boolean equals(Object o) {
             return false;
        }

        @Override
        public int hashCode() {
            int result = i.hashCode();
            result = 31 * result + s.hashCode();
            return result;
        }
    }

    class GoodValueClass extends AbstractValueClass {

        public GoodValueClass(int value) {
            super(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BadValueClass that = (BadValueClass) o;

            if (!i.equals(that.i)) return false;
            if (! (value == that.value)) return false;
            return s.equals(that.s);
        }

        @Override
        public int hashCode() {
            int result = i.hashCode();
            result = 31 * result + s.hashCode();
            result = 31 * result * value;
            return result;
        }
    }

    class ComparableValueClass extends AbstractValueClass implements Comparable<ComparableValueClass> {

        public ComparableValueClass(int value) {
            super(value);
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            int result = i.hashCode();
            result = 31 * result + s.hashCode();
            return result;
        }

        @Override
        public int compareTo(ComparableValueClass o) {
            return value - o.value;
        }
    }

    long performTest(Class C, Integer iterartions) {
        long timeBefore;
        long timeAfter;
        HashMap<? super AbstractValueClass, ? super AbstractValueClass> map = new HashMap<>();
        if (C == BadValueClass.class) {
            timeBefore = System.currentTimeMillis();
            for (int i = 0; i < iterartions; i ++)
                map.put(new BadValueClass(i), new BadValueClass(i));
            timeAfter = System.currentTimeMillis();
            return timeAfter - timeBefore;
        }
        if (C == GoodValueClass.class) {
            timeBefore = System.currentTimeMillis();
            for (int i = 0; i < iterartions; i ++)
                map.put(new GoodValueClass(i), new GoodValueClass(i));
            timeAfter = System.currentTimeMillis();
            return timeAfter - timeBefore;
        }
        if (C == ComparableValueClass.class) {
            timeBefore = System.currentTimeMillis();
            for (int i = 0; i < iterartions; i ++)
                map.put(new ComparableValueClass(i), new ComparableValueClass(i));
            timeAfter = System.currentTimeMillis();
            return timeAfter - timeBefore;
        }
        map = null;
        System.gc();
        return -1;
    }

    CollectionsTest() {
        logger = WindowLogger.getInstance();
        logger.setFileName("HashMap_results.html");
        logger.setLevel(Level.FINE);
    }

    void performAllTests() {

        int iterations;
        logger.log(Level.INFO, "Preparing tests");
        logger.log(Level.INFO, "Testing BadValueClass. Wait...");
        iterations = 10_000;
        logger.log("Map size: " + iterations);
        time = performTest(BadValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
        iterations = 20_000;
        logger.log("Map size: " + iterations);
        time = performTest(BadValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
        iterations = 40_000;
        logger.log("Map size: " + iterations);
        time = performTest(BadValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
        iterations = 80_000;
        logger.log("Map size: " + iterations);
        time = performTest(BadValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");

        logger.log(Level.INFO, "Testing GoodValueClass. Wait...");
        iterations = 80_000;
        logger.log("Map size: " + iterations);
        time = performTest(GoodValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
        iterations = 800_000;
        logger.log("Map size: " + iterations);
        time = performTest(GoodValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");

        logger.log(Level.INFO, "Testing ComparableValueClass. Wait...");
        iterations = 80_000;
        logger.log("Map size: " + iterations);
        time = performTest(ComparableValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
        iterations = 800_000;
        logger.log("Map size " + iterations);
        time = performTest(ComparableValueClass.class, iterations);
        logger.log("Total operation time: " + time + " ms.");
    }

    public static void main(String... args) {
        new CollectionsTest().performAllTests();
    }

}
