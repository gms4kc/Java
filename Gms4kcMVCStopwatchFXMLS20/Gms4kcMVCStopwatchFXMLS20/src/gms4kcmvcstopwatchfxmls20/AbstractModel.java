/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcmvcstopwatchfxmls20;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author genev
 */
public class AbstractModel {
    
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    protected double secondsElapsed;
    protected double tickTimeInSeconds; //how to change the resolution 
   
   
    protected PropertyChangeSupport propertyChangeSupport;
    
    public AbstractModel()
    {
        propertyChangeSupport = new PropertyChangeSupport(this);
        secondsElapsed = 0.0;
        tickTimeInSeconds = .01;
       
        setup();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public void setup(){
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000),(ActionEvent actionEvent) -> {
         
            secondsElapsed += tickTimeInSeconds;
            update();
             
        });
        
        timeline = new Timeline(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
    }
        
    public void update(){
        
    }
        
    public boolean isRunnning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
        
    public void start() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset(){
        timeline.stop();
        secondsElapsed = 0.0;
        tickTimeInSeconds = .01;
        update();
    }
    
}
