/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcfinalproject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Genevieve Saab
 * @reference http://java-buddy.blogspot.com/2013/07/javafx-drag-and-move-something.html for onMousePressed and onMouseDragged methods
 */
public class GameController extends Switchable implements Initializable {
    
    GameModel gameModel = new GameModel();
    
    @FXML
    private TextArea textArea;
    @FXML
    public Text username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameModel.initializeArray();
    }  
    
    // onMousePressed and onMouseDragged are for the draggable clothing items
 
    @FXML
    private void onMousePressed(MouseEvent event) {
        gameModel.setOrgSceneX(event.getSceneX());
        gameModel.setOrgSceneY(event.getSceneY());
        gameModel.setOrgTranslateX(((ImageView)(event.getSource())).getTranslateX());
        gameModel.setOrgTranslateY(((ImageView)(event.getSource())).getTranslateY());
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        double offsetX = event.getSceneX() - gameModel.getOrgSceneX();
        double offsetY = event.getSceneY() - gameModel.getOrgSceneY();
        double newTranslateX = gameModel.getOrgTranslateX() + offsetX;
        double newTranslateY = gameModel.getOrgTranslateY() + offsetY;
        
        ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
        ((ImageView)(event.getSource())).setTranslateY(newTranslateY);
            
    }

    @FXML
    private void outfitDone(ActionEvent event) { //Method occurs when user clicks the "Done!" button
        gameModel.update();
        gameModel.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt){
                      String string = (String)evt.getNewValue();
                      textArea.setText(string);
                }
            });
    }

    @FXML
    private void backToStart(ActionEvent event) {
        Switchable.switchTo("Start");
    }

    @FXML
    private void instructions(ActionEvent event) {
         Alert a = new Alert(Alert.AlertType.NONE); 
                a.setTitle("About");
                
                // set alert type 
                a.setAlertType(Alert.AlertType.INFORMATION); 
                
                // set content text 
                a.setContentText("Drag the clothes onto the model! Click 'Done!' when you're finished to receive a comment! Click home to return to the start page where you can change the model's name."); 
  
                // show the dialog 
                a.show(); 
    }

    
}
