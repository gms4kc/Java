/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kctimerstopwatchs20;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author genev
 * @reference Wergeles
 *      This code comes from lecture 
 */
public class Gms4kcTimerStopwatchS20 extends Application {
    
    private String appName = "Stopwatch";
    
    @Override
    public void start(Stage primaryStage) {

        AnalogStopWatch analog = new AnalogStopWatch();
        Scene scene = new Scene(analog.getRootContainer(), analog.getWidth(), analog.getHeight());
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
                       
        analog.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
