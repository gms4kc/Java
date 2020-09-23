/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcvehicles;

/**
 *
 * @author genev
 */
public class Gms4kcVehicles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Vehicle vehicle1 = new Vehicle("3FA6P0LUXKR106257", "Ford");
       vehicle1.setColor("Gray");
       vehicle1.setOdometer(43075);
       vehicle1.setCategory(Category.SEDAN);
       vehicle1.setPrice(14999);
        
       Vehicle vehicle2 = new Vehicle("2LMPJ8L99KBL39829", "Lincoln", "Silver", 17259, Category.SUV, 37997);
       
       Vehicle vehicle3 = new Vehicle();
       vehicle3.setVin("1G1FB3DS6J0164204");
       vehicle3.setMake("Chevrolet");
       vehicle3.setColor("Bright Yellow");
       vehicle3.setOdometer(38601);
       vehicle3.setCategory(Category.CONVERTIBLE);
       vehicle3.setPrice(21970);
       vehicle3.setPrice(19350);
       
       System.out.println("Vehicle 1:");    
       System.out.println("Vin Number: " + vehicle1.getVin());
       System.out.println("Make: " + vehicle1.getMake());
       System.out.println("Color: " + vehicle1.getColor());
       System.out.println("Category: " + vehicle1.getCategory());
       System.out.println("Odometer: " + vehicle1.getOdometer());
       System.out.println("Price: " + vehicle1.getPrice());
       System.out.println("Version: " + vehicle1.getVersion());
       
       System.out.println("\nVehicle 2:");    
       System.out.println("Vin Number: " + vehicle2.getVin());
       System.out.println("Make: " + vehicle2.getMake());
       System.out.println("Color: " + vehicle2.getColor());
       System.out.println("Category: " + vehicle2.getCategory());
       System.out.println("Odometer: " + vehicle2.getOdometer());
       System.out.println("Price: " + vehicle2.getPrice());
       System.out.println("Version: " + vehicle2.getVersion());
       
       System.out.println("\nVehicle 3:");    
       System.out.println("Vin Number: " + vehicle3.getVin());
       System.out.println("Make: " + vehicle3.getMake());
       System.out.println("Color: " + vehicle3.getColor());
       System.out.println("Category: " + vehicle3.getCategory());
       System.out.println("Odometer: " + vehicle3.getOdometer());
       System.out.println("Price: " + vehicle3.getPrice());
       System.out.println("Version: " + vehicle3.getVersion() + "\n");
       
       System.out.println("Number of Vehicles: " + Vehicle.numOfVehicles);
    }
}
