/*
 * @Jakub Hamerliński
 */
package factory;

enum VehicleType {
    CAR, TRUCK, MOTORCYCLE, DEFAULT
}

public interface VehicleFactory {
    Vehicle produceVehicle(VehicleType type);
}

