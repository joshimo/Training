package joshimo.io.utils;

public class Tester {

    static WindowLogger logger;

    public static void main(String... args) throws Exception, InterruptedException {
        logger = WindowLogger.getInstance();
        logger.setFileName("WindowLogger.html");
        logger.showStackTrace(false);
        logger.log(Level.ANYWAY, "Testing different level visualization: default level");
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.FINEST");
        logger.setLevel(Level.FINEST);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.FINER");
        logger.setLevel(Level.FINER);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.FINE");
        logger.setLevel(Level.FINE);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.CONFIG");
        logger.setLevel(Level.CONFIG);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.INFO");
        logger.setLevel(Level.INFO);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.WARNING");
        logger.setLevel(Level.WARNING);
        test();
        //Thread.sleep(1000);
        logger.log(Level.ANYWAY,"Setting up level: Level.SEVERE");
        logger.setLevel(Level.SEVERE);
        test();
        //Thread.sleep(500);
        logger.setLevel(Level.FINEST);
        logger.log(Level.ANYWAY, "Now we testing exception visualization");
        //Thread.sleep(2500);
        try {
            Integer.parseInt(" ");
        } catch (NumberFormatException nfe) {
            logger.log(Level.SEVERE, nfe);
        }
        //Thread.sleep(2500);
        logger.log(Level.ANYWAY, "After exception");
        logger.setLevel(Level.FINE);
        logger.log(Level.ANYWAY, "Now testing loggigng with stack tracing:");
        logger.showStackTrace(true);
        logger.log("abcde");
        logger.log(5 + 8);
    }

    private static void test() {
        logger.log(Level.FINE, "Visualization: default level");
        logger.log(Level.FINEST, "Visualization: FINEST level");
        logger.log(Level.FINER, "Visualization: FINER level");
        logger.log(Level.FINE, "Visualization: FINE level");
        logger.log(Level.CONFIG, "Visualization: CONFIG level");
        logger.log(Level.INFO, "Visualization: INFO level");
        logger.log(Level.WARNING, "Visualization: WARNING level");
        logger.log(Level.SEVERE, "Visualization: SEVERE level");
        logger.log(Level.SEVERE, "");
    }
}
