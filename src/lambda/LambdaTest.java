package lambda;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import joshimo.io.utils.WindowLogger;

public class LambdaTest {

    static WindowLogger logger = WindowLogger.getInstance();

    public static void main(String[] args) {

        Predicate<Integer> isPositive = (i) -> (i > 0);
        logger.log(isPositive.test(5));
        logger.log(5, (Integer i) -> (i > 0));

        UnaryOperator<String> uppercase = String::toUpperCase;
        logger.log(uppercase.apply("abcde"));
        logger.log("abcde", (String s) -> s.toUpperCase());

        Function<Integer, String> function = (Integer i) -> "Squared i = " + (i * i);
        logger.log(function.apply(5));
        logger.log(5, (Integer i) -> "Squared i = " + (i * i));

        BinaryOperator<String> concater = (s1, s2) -> s1.concat(" + ").concat(s2);
        logger.log(concater.apply("abc", "def"));
    }
}