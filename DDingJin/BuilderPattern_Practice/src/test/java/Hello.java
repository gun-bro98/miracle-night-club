public class Hello {
    public static void main(String args[]) {
        System.out.println("Hello");
        Singleton testSingleton = new Singleton();
        testSingleton.getInstance();
        testSingleton.getInstance();
        testSingleton.getInstance();
    }

    private static class Singleton {
        private static Singleton singleton;

        private Singleton() {}

        public static Singleton getInstance() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) singleton = new Singleton();
                }
            }
            return singleton;
        }

    }
}
