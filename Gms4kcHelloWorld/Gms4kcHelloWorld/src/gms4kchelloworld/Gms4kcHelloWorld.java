/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms4kchelloworld;
import java.util.Date;
import java.util.*;
import java.text.*;

/**
 *
 * @author Genevieve Saab
 * 
 * @refernce tutorialspoint
 *      Lines: 33-35
 *      Link: https://www.tutorialspoint.com/java/java_date_time.htm
 *      Used the "Date Formatting Using SimpleDateFormat" section of the article to get current date and time. 
 */
public class Gms4kcHelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        invokeMe();
        
    }
    
    public static void invokeMe() {
        //Print pawprint
        String myPawPrint = "gms4kc";
        System.out.println("My PawPrint is " + "\"" + myPawPrint + "\"");
        
        //Print current date and time 
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy hh:mma");
        System.out.println("Today's date is " + ft.format(dNow));
    } 
}
