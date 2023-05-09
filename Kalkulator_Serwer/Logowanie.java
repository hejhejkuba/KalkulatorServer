package Kalkulator_Serwer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static Kalkulator_Serwer.Welcome_Page.User;
import static Kalkulator_Serwer.Welcome_Page.CLayout;
import static Kalkulator_Serwer.Users.user_login;
import static Kalkulator_Serwer.Kalkulator.erase;
import static Kalkulator_Serwer.Kalkulator.wynik;
import javax.swing.*;  

public class Logowanie{
	JPanel Okno;
	JTextField Login;
	JLabel Logowanie_label, Podaj_login, Podaj_passw;
	JPasswordField Password;
	JButton Submit;
	JLabel Passwd;
	static JTextArea Wynik;
	
	static void log_user(String Login, String Password) {	
		Connection c = null;
		Statement stmt = null;
	      try {
	          Class.forName("org.postgresql.Driver");
	          c = DriverManager
			            .getConnection("jdbc:postgresql://localhost:5432/Baza_danych_1",
			                    "postgres", "admin");
	          if (c != null) {
	          c.setAutoCommit(false);
	         // System.out.println("Opened database successfully");

	          stmt = c.createStatement();
	          ResultSet rs = stmt.executeQuery( "SELECT Count(*) FROM \"Users\" WHERE \"FLogin\" = '" + Login + "' AND \"FPassword\" = '" + Password + "';" );
	          rs.next();
	          if(rs.getInt(1)==0)
	          {
	        	  //user dont exist
	        	  Wynik.setText("Taki U¿ytkownik nie istnieje");
	        	  rs.close();
		          stmt.close();
		          c.close(); 
	          }
	          else {
	        	 
	        	  rs.close();
		          stmt.close();
		          c.close(); 
		          User.setText("User: "+ Login);
		          user_login = Login;
		          Wynik.setText("Zalogowano");
		          wynik.setText("");
		          
	          };
	          }
	          else {
	        	  User.setText("User: "+ "gosc");
	        	  user_login = Login;
	        	  Wynik.setText("Serwer jest wy³¹czony :( ");
	          }
	         
	       } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          User.setText("User: "+ "gosc");
	          user_login = Login;
	          Wynik.setText("Serwer jest wy³¹czony :( ");
	   
	       }
		
		};
	Logowanie(){
		
		Okno = new JPanel();
		
		Logowanie_label = new JLabel("Logowanie");
		Logowanie_label.setBounds(310, 10, 250, 65);
		Logowanie_label.setFont(new Font("Serif", Font.PLAIN, 54));
		
		Podaj_login = new JLabel("Podaj Login");
		Podaj_login.setBounds(330, 170, 200, 20);
		Login= new JTextField(20);
		Login.setBounds(330,200,200,20);
		
		Podaj_passw = new JLabel("Podaj Has³o");
		Podaj_passw.setBounds(330, 230, 200, 20);
		Password= new JPasswordField(20);
		Password.setBounds(330,250,200,20);
		
		Submit = new JButton("Zaloguj");
		Submit.setBounds(330, 300, 200, 40);
		
		Wynik = new JTextArea();
		Wynik.setBounds(560, 150, 200, 60);
		Wynik.setDisabledTextColor(Color.black);
		Wynik.setOpaque(false);		
		Wynik.setLineWrap(true);
		Wynik.setWrapStyleWord(true);
		Wynik.setFont(new Font("Serif", Font.PLAIN, 20));
		Wynik.setEnabled(false); 
		
		
		
		
		
		
		Okno.add(Logowanie_label);
		Okno.add(Podaj_login);
		Okno.add(Login);
		Okno.add(Podaj_passw);
		Okno.add(Password);
		Okno.add(Submit);
		Okno.add(Wynik);
		
		Okno.setLayout(null);
		Okno.setOpaque(true);
		Okno.setBounds(50, 190, 870, 550);
		//Okno.setBackground(Color.CYAN);
		Okno.setVisible(true);
		

		//Okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(Login.getText().isEmpty()==true || Password.getText().isEmpty()==true) {	
					Wynik.setText("¯adne pole nie mo¿e byæ puste");
					}
					else {
						log_user(Login.getText(), Password.getText());
					}
					Login.setText("");
					Password.setText("");
				}
				catch(Exception k ) {
					Wynik.setText("Unexpected error :( ");
				};
			
			}
		});
		};
		
	
	
	}
