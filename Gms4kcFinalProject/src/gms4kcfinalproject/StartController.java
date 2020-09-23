/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kcfinalproject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Genevieve Saab
 * @reference Wergeles for the handleSave(ActionEvent event), handleOpen(ActionEvent event), 
 *      and displayExceptionAlert(String message, Exception ex) methods.
 */
public class StartController extends Switchable implements Initializable {
    
    Person person;
    private Stage stage;
    
    @FXML
    private TextArea nameText;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        person = new Person(); 
    }    
    

    @FXML
    private void handleGoToGame(MouseEvent event) {
        Switchable.switchTo("Game");
     
        GameController controller = (GameController) getControllerByName("Game");
        
        //Pass name data to the game controller
        if (controller != null) {
            if(!nameText.getText().equals("")){
                controller.username.setText(nameText.getText());
            }
            else {
               
            }
        }

    }

    @FXML
    private void aboutMethod(ActionEvent event) {
         Alert a = new Alert(Alert.AlertType.NONE); 
                a.setTitle("About");
                
                // set alert type 
                a.setAlertType(Alert.AlertType.INFORMATION); 
                
                // set content text 
                a.setContentText("This game was created by CS3330 student Genevieve Saab! Use the file menu to save name info or open a file to input the name."); 
  
                // show the dialog 
                a.show(); 
    }
    
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            
            try {
                
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream input = new ObjectInputStream(fileIn);
                
                person = (Person) input.readObject();
                
                input.close();
                fileIn.close();
                
                //update UI for the user
                fillFormFromPerson(person);
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
                
            } catch (IOException ex) {
                String message = "IO exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex);
                
            } catch (ClassNotFoundException ex) {
                String message = "Class not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
            }  catch (Exception ex){
                String message = "Class not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
            }
        }
    }
    
    public void handleSave(ActionEvent event) {
         
        person = createPersonFromFormData();
        
        if(person == null){
            return; 
        }
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            
            try {
                
                FileOutputStream fileOut = new FileOutputStream(file.getPath());
                ObjectOutputStream output = new ObjectOutputStream(fileOut);
                
                output.writeObject(person);
                
                output.close();
                fileOut.close();
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while saving to " + file.getPath(); 
                displayExceptionAlert(message, ex); 
                
            } catch (IOException ex) {
                String message = "IOException occured while saving to " + file.getPath();
                displayExceptionAlert(message, ex);
            }catch (Exception ex){
                String message = "IOException occured while saving to " + file.getPath();
                displayExceptionAlert(message, ex);
            }
        }        
       
    }
    
     private Person createPersonFromFormData() {
        Person p = new Person();

        p.setName(nameText.getText()); 
     
        
        return p;
    }
    
    private void fillFormFromPerson(Person person) {
        person.update();
        person.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt){
                      String newName = (String)evt.getNewValue();
                      nameText.setText(newName);
                }
            });
        
    }
    
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(ex.getClass().getSimpleName());      
        alert.setContentText(message + "\n\n" + ex.getMessage());
        alert.showAndWait();
    }      
  
}
