package stream;

import joshimo.io.utils.Level;
import joshimo.io.utils.WindowLogger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StreamTest {

    private static WindowLogger logger;

    public StreamTest() {
        logger = WindowLogger.getInstance();
        logger.setLevel(Level.FINE);
    }

    public void print(String song) {
        Stream.of(song).forEach(logger::log);
    }

    public long calculateLength(String song) {
        return song.chars().count();
    }

    public String changeToUppercase(String song) {
        StringBuilder result = new StringBuilder();
        Arrays.stream(song.split("")).map(String::toUpperCase).forEach(result::append);
        return result.toString();
    }

    public String changeToLowercase(String song) {
        StringBuilder result = new StringBuilder();
        Arrays.stream(song.split("")).map(String::toLowerCase).forEach(result::append);
        return result.toString();
    }

    public String replaceWord(String songText, String word, String replacement) {
        StringBuilder result = new StringBuilder();
        Arrays.stream(songText.split(" ")).map((s) -> s = s.equals(word) ? replacement : s).forEach((s) -> result.append(s + " "));
        return result.toString();
    }

    public long countIndexes(String song, String word) {
        return Arrays.stream(song.split(" ")).map((s) -> s.replaceAll("\\W", "")).filter(word::equals).count();
    }

    public void printDuplications(String song) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(song.split(" ")).map((s) -> s.replaceAll("\\W", "")).forEach((s) -> {
            if (!map.containsKey(s)) map.put(s, 1);
            else map.replace(s, map.get(s) + 1);});
        logger.log(map);
    }

    public static void main(String... args) {
        StreamTest test = new StreamTest();
        String songText = "Don`t worry be happy now!";
        logger.log("Song text: ");
        test.print(songText);
        logger.log("Total char length: " + test.calculateLength(songText));
        logger.log("Text in uppercase: " + test.changeToUppercase(songText));
        logger.log("Text in lowercase: " + test.changeToLowercase(songText));
        logger.log("Text with \"worry\" replaced to \"hurry\": " + test.replaceWord(songText, "worry", "hurry"));
        songText = "Don`t worry now, be happy now!";
        logger.log("Counting \"now\" words: " + test.countIndexes(songText, "now"));
        logger.log("Counting all words duplications:");
        test.printDuplications(songText);
    }
}