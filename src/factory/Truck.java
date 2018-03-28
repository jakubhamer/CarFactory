/*
 * @Jakub Hamerliński
 */
package factory;

public class Truck extends Vehicle {
    protected int manufactureTime = 10 * 1500;
    static int price = 2000;

    public Truck() {

        type = VehicleType.TRUCK;
    }

    @Override
    public Vehicle manufactureVehicle() { return this; }

    @Override
    public void run() {
        try {
            Thread.sleep(manufactureTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
