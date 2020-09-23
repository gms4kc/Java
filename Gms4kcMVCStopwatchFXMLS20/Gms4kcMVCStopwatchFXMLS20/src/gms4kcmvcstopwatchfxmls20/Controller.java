/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcmvcstopwatchfxmls20;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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

/**
 * FXML Controller class
 *
 * @author genev
 */
public class Controller implements Initializable,  PropertyChangeListener {

    AnalogModel analogModel; 
    DigitalModel digitalModel;
    
    @FXML
    private AnchorPane root;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private ImageView hand;
    @FXML
    private Text lapText;
    @FXML
    private Text timerText;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    

   @Override
    public void propertyChange(PropertyChangeEvent evt) { //Handles event listener 
        if(evt.getPropertyName().equals(("Analog"))){
                hand.setRotate((double)evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Digital")){
            String value = (String)evt.getNewValue();
              timerText.setText(value);
          
        }
        else if(evt.getPropertyName().equals("Lap")){
            String time = (String)evt.getNewValue();
            lapText.setText(time);
        }
        else if (evt.getPropertyName().equals("myLineChart")){
            if(lineChart.getData().isEmpty()){
                //record values charted in terms of seconds
                lineChart.getData().add((XYChart.Series)evt.getNewValue());
            }
        }
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       analogModel = new AnalogModel(); 
       analogModel.setup(); 
       analogModel.addPropertyChangeListener(this);
       digitalModel = new DigitalModel();
       digitalModel.setup();
       digitalModel.addPropertyChangeListener(this);
       yAxis.setAutoRanging(true); 
       yAxis.setLowerBound(0); 
       yAxis.setUpperBound(100); 
       lineChart.setAnimated(false);
    }    

    @FXML
    private void startStopFunction(ActionEvent event) {
        if(analogModel.isRunnning()){
            analogModel.stop();
            digitalModel.stop();
            startStopButton.setText("Start");
            recordResetButton.setText("Reset");
        }
        else {
         analogModel.start();
         digitalModel.start();
         startStopButton.setText("Stop");
         recordResetButton.setText("Record");
        }
    }

    @FXML
    private void recordResetFunction(ActionEvent event) {
        if(analogModel.isRunnning()) {
                digitalModel.record(); 
                digitalModel.myLineChart();
            }
        else {
            analogModel.reset();
            digitalModel.reset();
            lapText.setText("Lap -: --:--:--.--");
            timerText.setText("--:--:--.--");
            recordResetButton.setText("Record");
            yAxis.setLowerBound(0); 
            yAxis.setUpperBound(100); 
        }
    }
}
