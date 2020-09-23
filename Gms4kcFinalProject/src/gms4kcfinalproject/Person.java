/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcfinalproject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;

/**
 *
 * @author Genevieve Saab
 * @reference This class is based off of Wergeles' Person.java class.
 * 
 * This class is a model for Person that stores a person's name. 
 */
public class Person implements java.io.Serializable, PropertyChange{
    
    private String name;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public Person(){
         
    }
    
    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
  
    public String getName() {
        return name;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    @Override
    public void update(){ //This method updates the name 
           Platform.runLater(() -> {
             pcs.firePropertyChange("Person", null, getName()); 
           });
    }
    
   
    
   
   
}
