/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Genevieve Saab
 */
public class Gms4kcSuperVisual implements Visualizer{
    private AnchorPane vizPane;
    private final String name = "Gms4kc Super Visual";
    private Text[] hearts;
    private final Double startHue = 260.0;
    private Integer numOfBands;
    private Double width = 0.0;
    private Double height = 0.0;
    
    void initializeHeart(Text heart){
       heart.setFill(Color.RED);
       heart.setScaleX(7);
       heart.setScaleY(6);
       heart.setY(height*.6);
    }

    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        this.vizPane = vizPane;
        this.numOfBands = numBands;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        vizPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        hearts = new Text[4];

        Text heart1 = new Text("\u2665");
        Text heart2 = new Text("\u2665");
        Text heart3 = new Text("\u2665");
        Text heart4 = new Text("\u2665");


        initializeHeart(heart1);
        initializeHeart(heart2);
        initializeHeart(heart3);
        initializeHeart(heart4);

        heart1.setX(150);
        heart2.setX(300);
        heart3.setX(450);
        heart4.setX(600);

       vizPane.getChildren().addAll(heart1,heart2,heart3,heart4);
       
       hearts[0] = heart1;
       hearts[1] = heart2;
       hearts[2] = heart3;
       hearts[3] = heart4;
       
     
    }

    @Override
    public void end() {
        if(hearts != null){
            vizPane.getChildren().removeAll(hearts[0], hearts[1], hearts[2], hearts[3]);
            hearts = null;
        }    
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void draw(double timestamp, double lenght, float[] magnitudes, float[] phases) {
         if (hearts == null) {
            return;
        }
        
        for (int i = 0; i < 4; i++) {
          hearts[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
          hearts[i].setScaleX(.2*magnitudes[i]); 
          hearts[i].setScaleY(.2*magnitudes[i]);
          hearts[i].setY(Math.abs(magnitudes[i]*5)-50);
          hearts[i].setRotate(180);
       }
    }
}
