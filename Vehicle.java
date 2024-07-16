public interface Vehicle {
    // Abstract method
    void move();

    // Default method, all cars need to start engine
    default void startEngine() {
        System.out.println("Engine started.");
    }

    // Static method, VehicleType is related to
    static String getVehicleType() {
        return "Generic Vehicle";
    }
}
