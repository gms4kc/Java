/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kctimerstopwatchs20;

//import java.awt.Color;
import javafx.scene.paint.Color; 
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author Genevieve Saab
 * @reference Professor Wergeles
 *      The template of this program was created by Wergeles in class
 *      I added in other methods, customization, and functionality. 
 */
public class AnalogStopWatch {
    //animation
    private Timeline timeline;
    private KeyFrame keyframe;
    
    //images on the display
    private ImageView dialImageView;
    private ImageView handImageView;
    private StackPane rootContainer;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    Button startStop;
    Button lapReset;
    private Text timeLabel;
    private Text timerLabel;
    private Text lapLabel;
    private int lapCount;
    private double tempTime;
    private double lapTime;
    
    //stopwatch algorithm
    private double secondsElapsed;
    private double tickTimeInSeconds; //how to change the resolution 
    private double angleDeltaPerSeconds; //60 tick marks 
    
    public AnalogStopWatch(){
        secondsElapsed = 0.0;
        tickTimeInSeconds = .01;
        angleDeltaPerSeconds = 6.0;
        
        lapCount = 0;
        tempTime = 0;
        lapTime = 0;
        
        setupUI();
        setupTimer();
    }
    
    public void setupUI(){
        rootContainer = new StackPane(); 
        rootContainer.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, CornerRadii.EMPTY, Insets.EMPTY)));
        dialImageView = new ImageView();
        handImageView = new ImageView();
        
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName)); 
        
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);

        VBox digitalDisplay = new VBox();
        digitalDisplay.setAlignment(Pos.TOP_CENTER);
        timeLabel = new Text("00:00.00");
        timerLabel = new Text("Timer 60.00");
        lapLabel = new Text("Lap --:--:--");
        digitalDisplay.getChildren().addAll(timeLabel, timerLabel, lapLabel);
        
        
        VBox buttonControls = new VBox(); 
        startStop = new Button("Start");
        lapReset = new Button("Record");
        startStop.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        lapReset.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        startStop.setMaxWidth(Double.MAX_VALUE);
        lapReset.setMaxWidth(Double.MAX_VALUE);
        buttonControls.setAlignment(Pos.BOTTOM_CENTER);
        buttonControls.getChildren().addAll(lapReset, startStop);

        rootContainer.getChildren().addAll(digitalDisplay, buttonControls, dialImageView, handImageView);
        
        buttonControls.toFront();
        
        startStop.setOnAction((ActionEvent Event) -> {
           if(isRunning()){
               pause();
           }
           else {
            start();
           }
        });
        
        lapReset.setOnAction((ActionEvent Event) -> {
            if(isRunning()) {
                lap();
            }
            else{
                reset();
            }
        });
    }
    
    public void setupTimer(){
        keyframe = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000),(ActionEvent actionEvent) -> {
            
            if(secondsElapsed < 60){
            
            update();
            updateDigital();
            
            }
            else {
                pause();
                alertUser();
                timeLabel.setText("1:00.00");
                timerLabel.setText("Timer 00.00");
                lapLabel.setText("Lap --:--:--");
                secondsElapsed = 0.0;
                tickTimeInSeconds = .01;
                angleDeltaPerSeconds = 6.0;
                lapCount = 0;
                tempTime = 0;
                lapTime = 0;
            }
          
        });
        
        timeline = new Timeline(keyframe);
        
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void update(){
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
    }
    
    public void updateDigital(){
        double correctSeconds = secondsElapsed - .01;
        timeLabel.setText(String.format("00:%.2f", correctSeconds));
        double timerValue = 60 - correctSeconds;
        timerLabel.setText(String.format("Timer %.2f", timerValue));
    }
    
     public boolean isRunning(){
        if(timeline != null){ //check if object has been created
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
     
     
     public StackPane getRootContainer(){
         return rootContainer;
     }
     
     public Double getWidth(){
         if(dialImage != null) return dialImage.getWidth();
         else return 0.0;
     }
     
     public Double getHeight(){
         if(dialImage != null) return dialImage.getHeight();
         else return 0.0;
     }
     
     public void start(){
         timeline.play();
         startStop.setText("Stop");
         lapReset.setText("Record");
     }
     
     public void pause(){
         timeline.pause();
         startStop.setText("Start");
         lapReset.setText("Reset");
     }
     
     public void reset(){
        secondsElapsed = 0.0;
        tickTimeInSeconds = .01;
        angleDeltaPerSeconds = 6.0;
        lapLabel.setText("Lap --:--:--");
        lapCount = 0;
        tempTime = 0;
        lapTime = 0;
        update();
        updateDigital();
     }
     
     public void lap(){
         lapCount++;
         
         if(lapCount == 1){
             tempTime = secondsElapsed - .01;
             lapLabel.setText(String.format("Lap 00:%.2f", tempTime));
         }
         else if(lapCount == 2){
             lapTime = (secondsElapsed - .01) - tempTime;
             lapLabel.setText(String.format("Lap 00:%.2f", lapTime));
         }
         else{
             lapTime = (secondsElapsed - .01) - lapTime;
             lapLabel.setText(String.format("Lap 00:%.2f", lapTime));
         }
     }
     
     public void setTickInSeconds(Double tickTimeInSeconds){
         this.tickTimeInSeconds = tickTimeInSeconds; 
         setupTimer();
     }
     
     public void alertUser(){
           Alert a = new Alert(Alert.AlertType.NONE); 
           a.setTitle("Time is up!!!");
           a.setAlertType(Alert.AlertType.INFORMATION); 
           a.setContentText(String.format("You have %d records in 60 seconds!", lapCount)); 
           a.show(); 
     }
}
