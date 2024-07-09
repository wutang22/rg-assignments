package src;

public class Singleton {
    private Singleton() {
        // Initialize any resources here
    }
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void show() {
        System.out.println("rg-assignments singleton");
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.show();
    }
}
