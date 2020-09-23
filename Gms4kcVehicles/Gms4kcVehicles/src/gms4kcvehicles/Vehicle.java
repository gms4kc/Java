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
public class Vehicle {
    private String vin;
    private String make;
    private double price;
    private Category category;
    private String color;
    private int odometer = 0;
    private int version;
    public static int numOfVehicles = 0;
    
    public Vehicle(){
        this.vin = "";
        this.make = "";
        this.version = 0; 
        numOfVehicles++;
    } 
    public Vehicle(String vin, String make){
        this();
        this.vin = vin;
        this.make = make;
    }
    public Vehicle(String vin, String make, String color, int odometer, Category category, double price){
        this(vin, make);
        this.version = 1;
        this.color = color;
        this.odometer = odometer;
        this.category = category;
        this.price = price; 
    }
    
    public void setVin(String vin){
        this.vin = vin; 
    }
    
    public void setMake(String make){
        this.make = make;
    }
    
    public void setPrice(double price){
        this.price = price;
        this.version++;
    }
    
    public void setCategory(Category category){
        this.category = category;
    }
    
    public void setColor(String color){
        this.color = color;
        this.version++;
    }
    
    public void setOdometer(int odometer){
        this.odometer = odometer;
        this.version++;
    }
    
    public String getVin(){
        return this.vin;
    }
    
    public String getMake(){
        return this.make;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public Category getCategory(){
        return this.category;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public int getOdometer(){
        return this.odometer;
    }
    
    public int getVersion(){
        return this.version;
    }
}


