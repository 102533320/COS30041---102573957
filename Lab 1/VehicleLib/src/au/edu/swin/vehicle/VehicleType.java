/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package au.edu.swin.vehicle;

/**
 *
 * @author Matthew Coulter
 */
public class VehicleType {

    private String code;
    private String description;
    private Integer seats;

    public VehicleType(String code, String description, Integer seats) {
        this.code = code;
        this.description = description;
        this.seats = seats;
    }

    public String getCode() {
        return code.toUpperCase();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
