/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcgradescalculators20;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author genev
 */
public class Gms4kcGradesCalculatorS20 extends Application {
    
    public String title = "Grade Calculator"; 
    public int width = 500; 
    public int height = 300; 
    public String fontStyle = "Comic Sans MS"; 
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);
        
        //GridPane
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
//        root.setGridLinesVisible(true);
        
        //Label 1
        Label label1 = new Label("Assignments (40%):");
        label1.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(label1, 0, 0); //columns then rows
        
        //Textfield 1
        TextField score1 = new TextField();
        score1.prefWidth(300);
        root.add(score1, 1, 0);
        
        //Label 2
        Label label2 = new Label("Exams (40%):");
        label2.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(label2, 0, 1); 
        
        //Textfield 2
        TextField score2 = new TextField();
        score2.prefWidth(300);
        root.add(score2, 1, 1);
        
        //Label 3
        Label label3 = new Label("Projects (20%):");
        label3.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(label3, 0, 2); 
        
        //Textfield 3
         TextField score3 = new TextField();
        score3.prefWidth(300);
        root.add(score3, 1, 2);
        
        //Label 4
        Label label4 = new Label("Final Score:");
        label4.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(label4, 0, 3); 
        
        //Textarea 
        TextArea finalScore = new TextArea();
        finalScore.setPrefRowCount(2);
        finalScore.setWrapText(true);
        finalScore.setEditable(false);
        root.add(finalScore, 1, 3);
        
        //Buttons
        HBox hboxForButtons = new HBox(15);
        Button calculate = new Button("Calculate");
        calculate.minWidth(80);
        Button fullScore = new Button("Full Score");
        fullScore.minWidth(80);
        Button clear = new Button("Clear");
        clear.minWidth(80);
        Button alert = new Button("Alert");
        alert.minWidth(80);
        
        //Configure and add hbox
        GridPane.setColumnSpan(hboxForButtons, 2);
        GridPane.setRowSpan(hboxForButtons, 1);
        hboxForButtons.setPadding(new Insets(10,0,10,0));
        hboxForButtons.setAlignment(Pos.CENTER);
        hboxForButtons.getChildren().addAll(calculate, fullScore, clear, alert);
        root.add(hboxForButtons, 0, 4);
        
        //Calculate 
          calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                //https://beginnersbook.com/2013/12/how-to-convert-string-to-double-in-java/
               double s1 = Double.parseDouble(score1.getText());
               double s2 = Double.parseDouble(score2.getText());
               double s3 = Double.parseDouble(score3.getText());
               double calculation = (s1 * .4) + (s2 * .4) + (s3 * .2);
               finalScore.setText(String.format("My final score should be %.0f*0.4+%.0f*0.4+%.0f*0.2=%.2f", s1, s2, s3, calculation)); //https://stackoverflow.com/questions/8065114/how-to-print-a-double-with-two-decimals-in-android
        
        }
    });
        
        //Full Score
        fullScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               score1.setText("100");
               score2.setText("100");
               score3.setText("100");
        }
    });
        
        //Clear
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               score1.setText("");
               score2.setText("");
               score3.setText("");
               finalScore.setText("");
        }
    });
        
        //Alert
        alert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Alert a = new Alert(AlertType.NONE); 
                a.setTitle("Alert");
                
                // set alert type 
                a.setAlertType(AlertType.INFORMATION); 
                
                // set content text 
                a.setContentText(finalScore.getText()); 
  
                // show the dialog 
                a.show(); 
        
        }
    });
       
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
