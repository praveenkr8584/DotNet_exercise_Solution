
public class singletonPattern {

    static class Logger {

        private static volatile Logger instance;

        // Private constructor
        private Logger() {
            System.out.println("Logger instance created");
        }

        // Double-Checked Locking
        public static Logger getInstance() {

            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }

            return instance;
        }

        public void log(String message) {
            System.out.println("[LOG] " + message);
        }
    }

    public static void main(String[] args) {

        // Normal Singleton check
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application Started");

        System.out.println("Logger1 HashCode: " + logger1.hashCode());
        System.out.println("Logger2 HashCode: " + logger2.hashCode());

        if (logger1 == logger2) {
            System.out.println("Only one instance exists.\n");
        }

        // Thread check
        Thread t1 = new Thread(() -> {
            Logger logger = Logger.getInstance();
            System.out.println("Thread 1: " + logger.hashCode());
        });

        Thread t2 = new Thread(() -> {
            Logger logger = Logger.getInstance();
            System.out.println("Thread 2: " + logger.hashCode());
        });

        t1.start();
        t2.start();
        
    }
}