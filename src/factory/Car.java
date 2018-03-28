/*
 * @Jakub Hamerliński
 */
package factory;

public class Car extends Vehicle {
    protected int manufactureTime = 10 * 1000;
    static int price = 1000;

    public Car() {
        type = VehicleType.CAR;
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
