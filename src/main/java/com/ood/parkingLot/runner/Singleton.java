package com.ood.parkingLot.runner;

public class Singleton {

    // Private constructor to prevent instantiation
    private Singleton() {
        // Prevent reflection from breaking the Singleton pattern
        throw new IllegalStateException("Singleton instance already created");
    }

    // Static inner class that contains the Singleton instance
    private static class SingletonHolder {
        // The Singleton instance is created when this class is loaded
        private static final Singleton INSTANCE = new Singleton();
    }

    // Public method to get the Singleton instance
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Check if both references point to the same instance
        System.out.println(singleton1 == singleton2); // Should print 'true'
    }
}
