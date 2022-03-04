/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vehiclehireapp;

import java.util.ArrayList;

import au.edu.swin.vehicle.Vehicle;
import au.edu.swin.vehicle.VehicleType;
import au.edu.swin.vehicle.VehicleTypeOptionSelector;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Matthew Coulter
 */
public class VehicleHireApp {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create the vehicle types
        ArrayList<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
        vehicleTypes.add(new VehicleType("SEDAN", "A standard sedan", 4));
        vehicleTypes.add(new VehicleType("LIMO6", "A six seater limo", 6));
        vehicleTypes.add(new VehicleType("LIMO8", "An eight seater limo", 8));
        
        // Create the vehicles
        ArrayList<Vehicle> vehicles = new ArrayList();
        vehicles.add(new Vehicle("Ed's Holden Caprice", "Silver", vehicleTypes.get(0), 2002));
        vehicles.add(new Vehicle("John's Mercedes C200", "Black", vehicleTypes.get(0), 2005));
        vehicles.add(new Vehicle("Guy's Volvo 244 DL", "Blue", vehicleTypes.get(0), 1976));
        vehicles.add(new Vehicle("Sasco's Ford Limo", "White", vehicleTypes.get(1), 2014));
        vehicles.add(new Vehicle("Peter's Ford Limo", "White", vehicleTypes.get(1), 2004));
        vehicles.add(new Vehicle("Robert's Ford Limo", "White", vehicleTypes.get(2), 2003));
        System.out.println("\n\nList of vehicles in system:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
        String typeCode = vehicleTypes.get(0).getCode();
        System.out.println("\n\nList of vehicle of type " + typeCode);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().getCode().equals(typeCode)) {
                System.out.println(vehicle);
            }
        }
        
        System.out.println("\n\nIt will display a list of the vehicle based on the type that you choose");

        // generate list of options that can be chosen from
        ArrayList<Object> options = new ArrayList<>(vehicleTypes);
        options.add("Exit");
        
        // create the option selector which handles choosing the option
        VehicleTypeOptionSelector optionSelector = new VehicleTypeOptionSelector(options,vehicles);
        
        // start the procedure
        optionSelector.prompt();
    }
}
