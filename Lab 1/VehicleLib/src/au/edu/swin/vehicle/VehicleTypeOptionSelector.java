/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package au.edu.swin.vehicle;

import java.util.ArrayList;
import mclib.OptionSelector;
/**
 *
 * @author Matthew Coulter
 */
public class VehicleTypeOptionSelector extends OptionSelector {
    private ArrayList<Vehicle> _vehicles;
    public VehicleTypeOptionSelector(
            ArrayList<Object> options,
            ArrayList<Vehicle> vehicles
        ){
        super(options);
        this._vehicles = vehicles;
    }
    
    @Override
    public void handle(Object value){
        if (value == "Exit") return;
        for (Vehicle vehicle : this._vehicles) {
            if (vehicle.getType() == value) {
                System.out.println(vehicle);
            }
        }
        System.out.println("\n");
        
        this.prompt();
    }
}
