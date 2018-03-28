/*
 * @Jakub Hamerliński
 */
package factory;

import java.io.IOException;
import java.io.File;
import java.util.Vector;

public class PoznanFactory implements VehicleFactory {
    static int finalCost = 0;
    static int completedVehicles = 0;
    static int s = 0;

    @Override
    public Vehicle produceVehicle(VehicleType type) {
        Vehicle vehicle = null;
        switch (type) {
            case CAR:
                vehicle = new Car();
                break;
            case TRUCK:
                vehicle = new Truck();
                break;
            case MOTORCYCLE:
                vehicle = new Motorcycle();
                break;
        }
        return vehicle;
    }


    public static void startProduction(String xmlFilePath) throws Exception {
        VehicleFactory factory = new PoznanFactory();
        Vehicle productionLine1 = factory.produceVehicle(VehicleType.DEFAULT);
        Vehicle productionLine2 = factory.produceVehicle(VehicleType.DEFAULT);
        Vehicle productionLine3 = factory.produceVehicle(VehicleType.DEFAULT);

        Thread buildingVehicle1 = new Thread(productionLine1);
        Thread buildingVehicle2 = new Thread(productionLine2);
        Thread buildingVehicle3 = new Thread(productionLine3);

        File xmlFile = new File(xmlFilePath);
        Vector<String> order = CustomerService.analyzeOrder(xmlFile);

        while (completedVehicles != order.size()) {
            VehicleType newVehicle = chooseVehicle(order.elementAt(s));
            if (!buildingVehicle1.isAlive()) {
                productionLine1 = factory.produceVehicle(newVehicle);
                buildingVehicle1 = new Thread(productionLine1);
                buildingVehicle1.start();
                updateCostAndBuffers(order.elementAt(s));
            } else if (!buildingVehicle2.isAlive()) {
                productionLine2 = factory.produceVehicle(newVehicle);
                buildingVehicle2 = new Thread(productionLine2);
                buildingVehicle2.start();
                updateCostAndBuffers(order.elementAt(s));
            } else if (!buildingVehicle3.isAlive()) {
                productionLine3 = factory.produceVehicle(newVehicle);
                buildingVehicle3 = new Thread(productionLine3);
                buildingVehicle3.start();
                updateCostAndBuffers(order.elementAt(s));
            }

        }
        buildingVehicle1.join();
        buildingVehicle2.join();
        buildingVehicle3.join();
        System.out.println(finalCost);
        finalCost = 0;
        completedVehicles = 0;
        s = 0;
    }

    public static void updateCostAndBuffers(String order) {
        switch (order) {
            case "car": {
                finalCost += Car.price;
                break;
            }
            case "truck": {
                finalCost += Truck.price;
                break;
            }
            case "motorcycle": {
                finalCost += Motorcycle.price;
                break;
            }

        }
        s++;
        completedVehicles++;

    }

    public static VehicleType chooseVehicle(String order) {
        VehicleType newVehicle;
        switch (order) {
            case "car": {
                newVehicle = VehicleType.CAR;
                break;
            }
            case "truck": {
                newVehicle = VehicleType.TRUCK;
                break;
            }
            case "motorcycle": {
                newVehicle = VehicleType.MOTORCYCLE;
                break;
            }
            default:
                newVehicle = VehicleType.CAR;

        }
        return newVehicle;
    }

    public static void main(String args[]) throws Exception {
        String xmlFilePath = "src/factory/order.xml"; // PATH TO .xml file with order details.
        while (true) {

            PoznanFactory.startProduction(xmlFilePath);
            System.out.println("Press ENTER to start.");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
