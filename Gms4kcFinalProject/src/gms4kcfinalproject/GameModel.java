/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcfinalproject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;
import java.util.ArrayList;

/**
 *
 * @author Genevieve Saab
 */
public class GameModel implements PropertyChange{
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    ArrayList<String> strings = new ArrayList<>();
    
    int index = -1;
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    public void initializeArray(){
        strings.add("Nice!");
        strings.add("Super cute!!");
        strings.add("Fabulous");
        strings.add("Are you a fashion designer!?");
        strings.add("Great outfit!");
        strings.add("Love this look");
        strings.add("Stunning!!");
        strings.add("Loves it");
        strings.add("Looking amazing");
        strings.add("Gorgeous!!"); 
        strings.add("Very glamorous"); 
        strings.add("Lovely");
        strings.add("So chic");
        strings.add("Beautiful!!");
        strings.add("Great job!");
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
    public void update() { //This method updates the comment box based on the next string in the arrayList 
        Platform.runLater(() -> {
             pcs.firePropertyChange("Person", null, getString()); 
           });  
    }
    
    public String getString(){ //Moves through each index in the array to return the correct string
        
        index++;
        
        if(index > 14){
            index = -1; 
        }
        
        if(index == -1){
            index++;
            return strings.get(0);
        }
        else{
            return strings.get(index);
        }
        
    }
    
    public double getOrgSceneX(){
        return orgSceneX;
    }
    
    public void setOrgSceneX(double orgSceneX){
        this.orgSceneX = orgSceneX;
    }
    
      public double getOrgSceneY(){
        return orgSceneY;
    }
    
    public void setOrgSceneY(double orgSceneY){
        this.orgSceneY = orgSceneY;
    }
    
    public double getOrgTranslateX(){
        return orgTranslateX;
    }
    
    public void setOrgTranslateX(double orgTranslateX){
        this.orgTranslateX = orgTranslateX;
    }
    
    public double getOrgTranslateY(){
        return orgTranslateY;
    }
    
    public void setOrgTranslateY(double orgTranslateY){
        this.orgTranslateY = orgTranslateY;
    }

   
}
