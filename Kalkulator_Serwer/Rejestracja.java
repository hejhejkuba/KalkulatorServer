package Kalkulator_Serwer;


import static Kalkulator_Serwer.Welcome_Page.CLayout;
import static Kalkulator_Serwer.Welcome_Page.Main_Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Rejestracja {

	JPanel Rejestr_Form;
	
	JLabel Logowanie_label;
	JTextField Name, Surname, Login ;
	JButton Submit;

	JLabel Passwd, User;

	JLabel User_Name;

	JLabel Sur_Name;
	JPasswordField Password;
	static JTextArea Wynik;
	
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
	static boolean PasswordCheck(String str)
	{
		boolean small_char=false;
		boolean big_char=false;
		boolean integer=false;
		boolean special_char=false;
	    for(int i=0;i<str.length();i++)
	    {
	       if(Character.isUpperCase(str.charAt(i)))
	       {
	    	   small_char = true;
	       }
	       else if(Character.isLowerCase(str.charAt(i)))
	       {
	    	   big_char = true;
	       }
	       else if(Character.isDigit(str.charAt(i)))
	       {
	    	   integer = true;
	       }
	       else
	       {
	    	   special_char = true;
	       }
        }
	    if(small_char == true && big_char==true && integer==true && special_char==true)
	    {
	    	return true;
	    }
	    else {
		return false;
	    }
	    };
		static void add_user(String name, String surname, String Login, String Password) {	
			  Connection c = null;
		      Statement stmt = null;
	              Statement stmt2 = null;
		
			try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Baza_danych_1",
		                    "postgres", "admin");
		         
		         if (c != null) {
		        	   c.setAutoCommit(true);
				         stmt = c.createStatement();
				          ResultSet rs = stmt.executeQuery( "SELECT Count(*) FROM \"Users\" WHERE \"FLogin\" = '" + Login +"';" );
				          rs.next();
				          if(rs.getInt(1)==0)
				          {
			                      stmt2 = c.createStatement();
			                    
				              String sql = "INSERT INTO \"Users\" (\"FName\", \"FSurname\", \"FLogin\", \"FPassword\") VALUES ('"+ name +"', '"+surname+"', '"+Login+"', '"+Password+"');";
				              stmt2.executeUpdate(sql); 
				              Wynik.setText("U¿ytkownik Dodany");
				              rs.close();
				              stmt2.close();
					          c.close(); 
					          //CLayout.show(Main_Panel, "1");
				          }
				          else {
				        	  Wynik.setText("Taki U¿ytkownik ju¿ istnieje");
				        	  rs.close();
					          //stmt2.close();
					          c.close();   
				          };
		            } else {
		            	Wynik.setText("Serwer jest wy³¹czony :( ");

		        }
		      
		         
		 	} catch (Exception e) {
		 		Wynik.setText("Serwer jest wy³¹czony :( ");
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         
	      }
			};
	Rejestracja(){
		
		Rejestr_Form = new JPanel();
		
		
		
		Logowanie_label = new JLabel("Rejestracja");
		Logowanie_label.setBounds(310, 10, 250, 65);;
		Logowanie_label.setFont(new Font("Serif", Font.PLAIN, 54));
		
		Name = new JTextField(20);
		Name.setBounds(330, 100, 200, 20);
		
		Surname = new JTextField(20);
		Surname.setBounds(330, 150, 200, 20);
		
		Login= new JTextField(20);
		Login.setBounds(330,200,200,20);
		
		Password= new JPasswordField(20);
		Password.setBounds(330,250,200,20);
		
		Submit = new JButton("Zarestruj");
		Submit.setBounds(330, 300, 200, 40);
		
		User = new JLabel("Login");
		Passwd = new JLabel("Has³o");
		User.setBounds(330, 180, 50, 20);
		Passwd.setBounds(330, 230, 50, 20);
		User_Name = new JLabel("Imiê");
		Sur_Name = new JLabel("Nazwisko");
		User_Name.setBounds(330, 80, 50, 20);
		Sur_Name.setBounds(330, 130, 60, 20);
		
		
		Wynik = new JTextArea();
		Wynik.setBounds(560, 150, 200, 60);
		Wynik.setDisabledTextColor(Color.black);
		Wynik.setOpaque(false);		
		Wynik.setLineWrap(true);
		Wynik.setWrapStyleWord(true);
		Wynik.setFont(new Font("Serif", Font.PLAIN, 20));
		Wynik.setEnabled(false); 
		
		Rejestr_Form.add(Login);
		Rejestr_Form.add(Logowanie_label);
		Rejestr_Form.add(Password);
		Rejestr_Form.add(Submit);
		Rejestr_Form.add(Passwd);
		Rejestr_Form.add(User);
		Rejestr_Form.add(Name);
		Rejestr_Form.add(Surname);
		Rejestr_Form.add(User_Name);
		Rejestr_Form.add(Sur_Name);
		Rejestr_Form.add(Wynik);
		
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(Surname.getText().isEmpty()==true || Name.getText().isEmpty()==true  || Login.getText().isEmpty()==true || Password.getText().isEmpty()==true ) {
					//System.out.println(Password.getText()+Name.getText()+Surname.getText());	
					Wynik.setText("¯adne pole nie mo¿e byæ puste");
					}
					else if(hasSpace(Surname.getText())==true || hasSpace(Name.getText())==true || hasSpace(Login.getText())==true || hasSpace(Password.getText())==true ){
						Wynik.setText("¯adne pole nie mo¿e zawierac spacji");
					}
					else if(Password.getText().length()<=8)
					{
						Wynik.setText("Haslo musi skladac sie minimalnie z 8 znakow");
					}
					else if(PasswordCheck(Password.getText())==false)
					{
						
						Wynik.setText("Niepoprawne Has³o");
					}
					else
					{
						add_user(Name.getText(), Surname.getText(), Login.getText(), Password.getText());
					}
					Name.setText("");
					Surname.setText("");
					Password.setText("");
					Login.setText("");
				}
				catch(Exception k ) {
					Wynik.setText("Unexpected error :( ");
				};
			
			}
		});
		
		
		
		
		Rejestr_Form.setBounds(310, 10, 250, 60);
		Rejestr_Form.setLayout(null);
		//Rejestr_Form.setLocationRelativeTo(null);
		//Rejestr_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Rejestr_Form.setResizable(false); 
		
		Rejestr_Form.setVisible(true); 
		};
}
