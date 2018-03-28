/*
 * @Jakub Hamerliński
 */
package factory;

public abstract class Vehicle implements Runnable {
    protected VehicleType type;

    public abstract Vehicle manufactureVehicle();
}
