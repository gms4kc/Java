/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcmvcstopwatchfxmls20;


/**
 *
 * @author genev
 */

public class AnalogModel extends AbstractModel{
    
    private double angleDeltaPerSeconds; 
    
    public AnalogModel(){
        angleDeltaPerSeconds = 6.0;
    }
    
   public double updateAngle(){
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        return rotation;
    }
   
    @Override
    public void update(){
        firePropertyChange("Analog", null, updateAngle()); 
    }
   
    @Override
    public void reset() {
        super.reset();
        angleDeltaPerSeconds = 6.0;
    }
}
