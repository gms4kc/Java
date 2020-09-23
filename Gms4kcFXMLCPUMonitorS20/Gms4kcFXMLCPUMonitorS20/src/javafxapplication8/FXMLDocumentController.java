/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author genev
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button startStop;
    @FXML
    private Button recordReset;
    @FXML
    private ImageView hand;
    
    private static double cpu = 0;
    private Timeline timeline;
    
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
     private LineChart<String, Number> lineChart;
    
    private XYChart.Series<String, Number> series;
    int count = 0;
    
    @FXML
    private Text text;
    
    public double getCPUUsage() { //This function comes from challenge document
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            
            if (method.getName().startsWith("getSystemCpuLoad") && Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
    
       public void setupGauge(){
          timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent) -> {
            cpu = this.getCPUUsage();
            update(cpu);
            updateDigital(cpu);     
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
     }
       
      public boolean isRunning(){
        if(timeline != null){ //check if object has been created
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    public void update(double cpu){
        double rotation = cpu*300-150;
        hand.setRotate(rotation);
    }
    
    public void updateDigital(double cpu){
        double percent = cpu * 100;
        text.setText(String.format("%.2f%%", percent));
    }
    
    public void start(){
        timeline.play();
        startStop.setText("Stop");
        recordReset.setText("Record");
    }
    
    public void stop(){
        timeline.pause();
        startStop.setText("Start");
        recordReset.setText("Reset");
    }
    
    public void reset(){
        timeline.pause();
        hand.setRotate(-150);
        startStop.setText("Start");
        recordReset.setText("Record");
        text.setText("0.00%");
        series.getData().clear();
        count = 0;
    }
    
    public void record(){
        count++;
        series.getData().add(new XYChart.Data(Integer.toString(count), getCPUUsage() * 100)); 
        System.out.println("Count = " + count + " CPUUsage = " + getCPUUsage() * 100);
    }
    
    @FXML
    public void startStopButton(){
        if(isRunning()){
            stop();
        }
        else {
         start();
        }
    }
    
    @FXML
    public void recordResetButton(){
        if(isRunning()) {
            record();
        }
        else{
            reset();
        }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        series = new XYChart.Series(); 
        yAxis.setAutoRanging(false); 
        yAxis.setLowerBound(0); 
        yAxis.setUpperBound(100); 
        lineChart.getData().add(series);
        lineChart.setAnimated(false); 
        
       setupGauge();
    }    
}
