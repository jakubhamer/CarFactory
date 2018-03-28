/*
 * @Jakub Hamerliński
 */
package factory;

public class Motorcycle extends Vehicle {
    protected int manufactureTime = 10 * 500;
    static int price = 600;

    public Motorcycle() {

        type = VehicleType.MOTORCYCLE;
    }

    @Override
    public Vehicle manufactureVehicle() {
        return this;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(manufactureTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
