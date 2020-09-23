/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcfinalproject;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Genevieve Saab
 * 
 * This interface is for classes that need to use property change listeners. 
 */
public interface PropertyChange {
    
    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);
    
    public void update();
}
