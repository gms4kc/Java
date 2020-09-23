/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcmvcstopwatchfxmls20;

import javafx.scene.chart.XYChart;

/**
 *
 * @author genev
 */
public class DigitalModel extends AbstractModel{
    
    private double tempTime;
    private double lapTime;
    private int lapCount;
    private double hours;
    private double minutes;
    private double seconds;
    private XYChart.Series<String, Number> series;

    
    public DigitalModel(){
        tempTime = 0;
        lapTime = 0;
        lapCount = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        series = new XYChart.Series();
    }
    
    public String updateTime(){
        double correctSeconds = secondsElapsed - .01;
        double hoursDigital = Math.floor(correctSeconds/3600);
        double minutesDigital = Math.floor(correctSeconds/60) - (hoursDigital*60);
        double secondsDigital = correctSeconds - (minutesDigital*60);
        return String.format("%.0f:%.0f:%.2f", hoursDigital, minutesDigital, secondsDigital);
    }
    
    @Override
      public void update(){
        firePropertyChange("Digital", null, updateTime()); 
    }
      
    @Override
      public void reset(){
        super.reset();
        lapCount = 0;
        series.getData().clear();
      }
      
      public String updateRecord(){
          lapCount++;
         
         if(lapCount == 1){
            tempTime = secondsElapsed - .01;
            hours = Math.floor(tempTime/3600);
            minutes = Math.floor(tempTime/60) - (hours*60);
            seconds = tempTime - (minutes*60);
             series.getData().add(new XYChart.Data(Integer.toString(lapCount), tempTime)); 
         }
         else if(lapCount == 2){
            lapTime = (secondsElapsed - .01) - tempTime;
            hours = Math.floor(lapTime/3600);
            minutes = Math.floor(lapTime/60) - (hours*60);
            seconds = lapTime - (minutes*60);
            series.getData().add(new XYChart.Data(Integer.toString(lapCount), lapTime)); 
         }
         else{
            lapTime = (secondsElapsed - .01) - lapTime;
            hours = Math.floor(lapTime/3600);
            minutes = Math.floor(lapTime/60) - (hours*60);
            seconds = lapTime - (minutes*60);
            series.getData().add(new XYChart.Data(Integer.toString(lapCount), lapTime)); 
         }
         
         return String.format("Lap %d: %.0f:%.0f:%.2f", lapCount, hours, minutes, seconds);
            
      }
      
      public void record(){
        firePropertyChange("Lap", null, updateRecord()); 
      }
      
      public XYChart.Series getSeries(){
          return series;
      }
      
      public void myLineChart(){
        System.out.println("line chart functioni");
        firePropertyChange("myLineChart", null, getSeries()); 
      }  
}
