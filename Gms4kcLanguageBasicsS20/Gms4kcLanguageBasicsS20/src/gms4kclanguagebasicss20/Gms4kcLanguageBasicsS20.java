/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kclanguagebasicss20;
import static java.lang.Character.compare;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

/**
 *
 * @author Genevieve Saab
 * 
 * @reference
 *      Lines: 32-25
 *      Link: https://www.ntu.edu.sg/home/ehchua/programming/java/DateTimeCalendar.html
 *      Used code from their example
 * 
 * @refernce tutorialspoint
 *      Lines: 142-143
 *      Link: https://www.tutorialspoint.com/java/java_date_time.htm
 *      Used the "Date Formatting Using SimpleDateFormat" section of the article to get current date and time. 
 */

public class Gms4kcLanguageBasicsS20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Variable Declarations
        char c1 = 'D';
        char c2 = 68;
        short qualityScore = 89;
        float weight = (float)154.7;
        float height = (float)70.87;
        boolean sunny = true;
        boolean warm = false;
        
        //Variables involving time
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        
        String greeting = "Hello";
        String myPawPrint = "gms4kc";
        
        //Compare c1 and c2
        int checkChars = compare(c1, c2);
        if(checkChars == 0){
            System.out.println(c1 + " and " + c2 + " are the same.");
        }
        else{
            System.out.println(c1 + " and " + c2 + " are NOT the same");
        }
        
        //Check qualityScore
        if((qualityScore >= 0) && (qualityScore <= 60)){
            System.out.println("The quality is bad.");
        }
        else{
            System.out.println("Good quality.");
        }
        
        //Calculate bmi
        float bmi = (float)((weight * 703)/(Math.pow(height, 2)));
        System.out.printf("My BMI = %.2f\n", bmi);
        
        //Check sunny and warm
        if((sunny == true) && (warm == true)){
            System.out.println("Go hiking.");
        }
        else if((sunny == false) && (warm == true)){
            System.out.println("Go barbeque.");
        }
        else{
            System.out.println("Stay home.");
        }
        
        switch(hour){
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("The current time is " + hour + ":" + minute + " in the MORNING.");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                System.out.println("The current time is " + hour + ":" + minute + " in the AFTERNOON.");
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                System.out.println("The current time is " + hour + ":" + minute + " in the EVENING.");
                break;
            case 23:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("The current time is " + hour + ":" + minute + " in the NIGHT.");
                break;
            default:
                System.out.println("You have the wrong time.");
        }
        
        //Loop count
        for(int count = 3; count <= 9; count++){
            if((count % 2) == 0){
                System.out.println("Count: " + count);
            }
        }
        
        int countDown = 3;
        while(countDown > 0){
            System.out.println("Count Down: " + countDown);
            countDown--;
        }
        System.out.println("Houston, we have a lift off!");
        
        invokeMe(greeting, myPawPrint, hour, minute);
        
            
    }
    
    public static void invokeMe(String greeting, String myPawPrint, int hour, int minute) {
        
         //Get current date 
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
       
        System.out.println(greeting + ", my pawprint is " + myPawPrint + " and today's date is " + ft.format(dNow) + " " + hour + ":" + minute);
        
    } 
    
}
