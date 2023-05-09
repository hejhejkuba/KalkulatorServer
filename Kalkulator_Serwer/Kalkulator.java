package Kalkulator_Serwer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Kalkulator {
int suma;
static boolean hasSpace(String str)
{
    for(int i=0;i<str.length();i++)
    {
       if(str.charAt(0)==' ' || str.charAt(i)==' '&&str.charAt(i+1)!=' ')
           {
             return true;
       }	
        }
	return false;
};
static void erase()
{
	wynik.setText("");
	Wynik.setText("");
}
static int kontrolna_suma;
JPanel Kalkulator;
JLabel Tytul, Nazwa, Gram, Kcal;
static JLabel Wynik;
JButton  Dodaj, Wybierz_plik, Zapisz, Zaladuj;
JTextField nazwa, gram, kcal;
Connection c = null;
Statement stmt = null;
int zapis = 1;
static JTextArea wynik;
JScrollPane scroll;
Vector<String> Inserts_1 = new Vector<String>();
Vector<String> Inserts_2 = new Vector<String>();
Kalkulator(){
	
	Kalkulator = new JPanel();
	//Kalkulator.setSize(800, 800);
	Tytul = new JLabel("Kalkulator Kalorii");
	Tytul.setBounds(310, 10, 400, 60);
	Tytul.setFont(new Font("Serif", Font.PLAIN, 54));

	Nazwa = new JLabel("Nazwa:");
	Nazwa.setBounds(45, 100, 50, 20);
	Nazwa.setFont(new Font("Serif", Font.PLAIN, 14));
	Gram = new JLabel("Gramy:");
	Gram.setBounds(45, 150, 50, 20);
	Gram.setFont(new Font("Serif", Font.PLAIN, 14));
	Kcal = new JLabel("Kcal:");
	Kcal.setBounds(45, 200, 50, 20);
	Kcal.setFont(new Font("Serif", Font.PLAIN, 14));
	
	
	Wynik = new JLabel("");
	Wynik.setBounds(30, 40, 300, 50);
	Wynik.setFont(new Font("Serif", Font.PLAIN, 18));
	
	nazwa = new JTextField();
	nazwa.setBounds(100, 100, 100, 20);
	nazwa.setFont(new Font("Serif", Font.PLAIN, 14));
	gram = new JTextField();
	gram.setBounds(100, 150, 100, 20);
	gram.setFont(new Font("Serif", Font.PLAIN, 14));
	kcal = new JTextField();
	kcal.setBounds(100, 200, 100, 20);
	kcal.setFont(new Font("Serif", Font.PLAIN, 14));
	
	wynik = new JTextArea();
	wynik.setDisabledTextColor(Color.BLACK);
	scroll = new JScrollPane(wynik);
	scroll.setBounds(250, 100, 500, 400);
	
	//wynik.setBounds(45,250,700,500);
	wynik.setEnabled(false);
	
	
	Dodaj = new JButton("Dodaj");
	Dodaj.setBounds(100, 250, 100, 20);
	 
	
	Zapisz = new JButton("Zapisz");
	Zapisz.setBounds(100, 300, 100, 20);
	
	
	
	Zaladuj = new JButton("Za³aduj");
	Zaladuj.setBounds(100, 10, 100, 20);
	
	
	
	Kalkulator.add(Tytul);
	Kalkulator.add(scroll);
	Kalkulator.add(Nazwa);
	Kalkulator.add(Gram);
	Kalkulator.add(Kcal);
	Kalkulator.add(nazwa);
	Kalkulator.add(gram);
	Kalkulator.add(kcal);
	Kalkulator.add(Zaladuj);
	Kalkulator.add(Dodaj);
	Kalkulator.add(Wynik);
	//Kalkulator.add(Wybierz_plik);
	Kalkulator.add(Zapisz);
	
	
	
	//Kalkulator.setLocationRelativeTo(null);
	//Okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Kalkulator.setResizable(false); 

	Kalkulator.setLayout(null);
	Kalkulator.setVisible(true); 
	
	
	
     
	Zaladuj.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(Users.user_login == "gosc")
				{
				Wynik.setText("U¿ytkownik nie zalogowany");
				}
				else {
				wynik.setText("");
				Wynik.setText(Users.user_login);
				 Class.forName("org.postgresql.Driver");
		          c = DriverManager
				            .getConnection("jdbc:postgresql://localhost:5432/Baza_danych_1",
				                    "postgres", "admin");
		          if (c != null) 
		          {
		        	  c.setAutoCommit(false);
		        	  stmt = c.createStatement();
			          ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Calories\" Where \"FLogin\"='"+ Users.user_login + "';" );
			          Wynik.setText("");
			          while (rs.next())
			          {
			    
			        	  String data =  rs.getString("FDate");;
			        	  String content =  rs.getString("FContent");;
			        	  wynik.setText(wynik.getText() + data + " | " + content +"\n");
			          }
			          kontrolna_suma = 1;
			          rs.close();
			          stmt.close();
			          c.close();
			          Inserts_1.clear();
			          Inserts_2.clear();
		          }  
		          else 
		          {
		        	 System.out.print("Bla bla.");
				 
		          }
				}
			}
			catch(Exception k ) {
				Wynik.setText("Serwer wy³¹czony :(");
			};
		
		}
	});
	Dodaj.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(kontrolna_suma != 1)
				{
				Wynik.setText("Wpierw Za³aduj");
				}
				else if(nazwa.getText().isEmpty()==true || gram.getText().isEmpty()==true  || kcal.getText().isEmpty()==true) {
					Wynik.setText("¯adne pole nie mo¿e byæ puste");
			    	 nazwa.setText("");
			    	 gram.setText("");
			    	 kcal.setText("");
				}
				else if(hasSpace(nazwa.getText())==true || hasSpace(gram.getText())==true || hasSpace(kcal.getText())==true){
					Wynik.setText("¯adne pole nie mo¿e zawierac spacji");
			    	 nazwa.setText("");
			    	 gram.setText("");
			    	 kcal.setText("");
				}
				else 
				{
					
					 DATA_NOW date = new DATA_NOW();
					 Class.forName("org.postgresql.Driver");
			          c = DriverManager
					            .getConnection("jdbc:postgresql://localhost:5432/Baza_danych_1",
					                    "postgres", "admin");
			          if (c != null) 
			          {  
			        	
			        	 wynik.setText(wynik.getText() + date.current_hour + " | " + nazwa.getText() + " | " + gram.getText() + " gram |" + kcal.getText() + " kcal\n");
			        	 
			        	 String content =  nazwa.getText() + " | " + gram.getText() + " gram |" + kcal.getText() + " kcal";
			        	 
			        	 Inserts_1.add(date.current_hour);
			        	 Inserts_2.add(content);
			        	 /*
			        	 stmt = c.createStatement();
				         stmt.executeUpdate( "Insert into \"Calories\" (\"FLogin\", \"FContent\", \"FDate\") values ('"+ Users.user_login +"', '" + content + "', '"+ date.current_hour +"' );" );
				    	 */
			        	 nazwa.setText("");
				    	 gram.setText("");
				    	 kcal.setText("");
				    	 Wynik.setText("");
				    	 zapis = 0;
				    	 
			          }
			          else {
			        	Wynik.setText("Serwer jest wy³¹czony :( ");  
			          };
					 
			    	
				};
			}
			catch(Exception k ) {
				System.err.println( e.getClass().getName()+": "+ k.getMessage() );
				Wynik.setText("Serwer wy³¹czony :(");
			};
		
		}
	});
	Zapisz.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				
				if(kontrolna_suma != 1)
				{
				Wynik.setText("Wpierw Za³aduj");
				}
				else {

						if(zapis == 0)
			          { 
			        	  if (c != null) 
			        	  {
			        		  Class.forName("org.postgresql.Driver");
					          c = DriverManager
							            .getConnection("jdbc:postgresql://localhost:5432/Baza_danych_1",
							                    "postgres", "admin");
			        		  c.setAutoCommit(true);
			        		  for(int i=0;i<Inserts_1.size();i++) {
			        		  stmt = c.createStatement();
						      stmt.executeUpdate( "Insert into \"Calories\" (\"FLogin\", \"FContent\", \"FDate\") values ('"+ Users.user_login +"', '" + Inserts_2.get(i) + "', '"+ Inserts_1.get(i) +"' );" );
			        		  };	
				        	  zapis = 1;
				        	  Wynik.setText("Zapisano do bazy"); 
				        	  Inserts_1.clear();
				        	  Inserts_2.clear();
			        	  }
			        	  else {
			        		  Wynik.setText("Serwer jest wy³¹czony :( ");  
			        	  };
			        	  
			
			          }
			          else {
			        	  Wynik.setText("Wpierw dodaj coœ ");   
			          };
					
				};
			}
			catch(Exception k ) {
				Wynik.setText("Errror :( ");
				System.err.println( e.getClass().getName()+": "+ k.getMessage() );
			};
		
		}
	});
};
}
