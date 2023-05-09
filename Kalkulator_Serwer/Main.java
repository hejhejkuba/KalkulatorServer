package Kalkulator_Serwer;
import javax.swing.*;
import java.awt.*;

public class Main {

	
	public static void main(String[] args) {
		
		DATA_NOW data = new DATA_NOW();

	       javax.swing.SwingUtilities.invokeLater(new Runnable() {  
	    	   @Override 
	            public void run() {  
	            	Welcome_Page Welcome = new Welcome_Page();
	            	//Kalkulator Kalkulator = new Kalkulator();
	            }  
	        });  
	    } 
	}


