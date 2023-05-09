package Kalkulator_Serwer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class Tluszcz {
 JPanel Tluszcz_Panel;
 JLabel Tluszcz_label, wzrost, talia, wiek, plec;
 JTextArea opis, Wynik;
 JTextField  Wzrost, Talia, Wiek;

 JButton Submit;
 String Plec[]= {"Mê¿czyzna", "Kobieta"};
  
 Tluszcz(){
	 Tluszcz_Panel = new JPanel();
	 
	 
	 Tluszcz_label = new JLabel("Kalkulator RFM");
	 Tluszcz_label.setBounds(310, 10, 450, 60);
	 Tluszcz_label.setFont(new Font("Serif", Font.PLAIN, 54)); 
	 
	 opis = new JTextArea("RFM (relative fat mass index) - WskaŸnik dziêki któremu mo¿esz zmierzyæ poziom tkanki t³uszczowej w swoim organizmie"
	 		+ "\nDla mê¿czyzn: 64 - (20 x wzrost / obwód talii)  \nDla bobiet: 72 - (20 x wzrost / obwód talii)");
	 opis.setBounds(0, 70, 280, 300);;
	 opis.setOpaque(false);
	 opis.setLineWrap(true);
	 opis.setWrapStyleWord(true);
	 
	 plec = new JLabel("Wybierz p³eæ");
	 plec.setBounds(330, 70, 250, 30);;

	 
	 JComboBox ComboBox = new JComboBox(Plec); 
	 ComboBox.setBounds(330, 100,90,20);    
	 
	 wzrost = new JLabel("Podaj wzrost (cm)");
	 wzrost.setBounds(330, 130, 150, 20);;
	 Wzrost = new JTextField(20);
	 Wzrost.setBounds(330, 150, 150, 20);;
	 
	 talia = new JLabel("Podaj obwód talii (cm)");
	 talia.setBounds(330, 180, 150, 20);;
	 Talia = new JTextField(20);
	 Talia.setBounds(330, 200, 150, 20);;
	 
	 wiek = new JLabel("Podaj wiek (lata)");
	 wiek.setBounds(330, 230, 150, 20);;
	 Wiek = new JTextField(20);
	 Wiek.setBounds(330, 250, 150, 20);;
	 
	
	 
	 Submit = new JButton("Oblicz");
	 Submit.setBounds(330, 350, 200, 30);
	 
	Wynik = new JTextArea("");
	Wynik.setBounds(600, 90, 200, 200);;
	Wynik.setDisabledTextColor(Color.black);
	Wynik.setOpaque(false);	
	Wynik.setLineWrap(true);
	Wynik.setWrapStyleWord(true);
	Wynik.setFont(new Font("Serif", Font.PLAIN, 20));
	Wynik.setEnabled(false); 
	
	 Tluszcz_Panel.add(Tluszcz_label);
	 Tluszcz_Panel.add(opis);
	 Tluszcz_Panel.add(plec);
	 Tluszcz_Panel.add(wzrost);
	 Tluszcz_Panel.add(talia);
	 Tluszcz_Panel.add(wiek);
	 Tluszcz_Panel.add(Wzrost);
	 Tluszcz_Panel.add(Talia);
	 Tluszcz_Panel.add(Wiek);
	 Tluszcz_Panel.add(ComboBox);
	 Tluszcz_Panel.add(Submit);
	 Tluszcz_Panel.add(Wynik);
	 
	 Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int zmienna = ComboBox.getSelectedIndex();
				if(zmienna == 0) {
					
				float M = 64-(20*Float.valueOf(Wzrost.getText()).floatValue()/Float.valueOf(Talia.getText()).floatValue());
				String formattedFloat = String.format("%.02f", M);
					if(M<8.0) {
						Wynik.setText("WskaŸnik RFM wynosi : " + formattedFloat + " Niedowaga" );}	
					else if(8.0<= M && M<19.0) {
						Wynik.setText("WskaŸnik RFM wynosi : " + formattedFloat +" Zdrowy Poziom");}
					else if(M>=19.0)	{
						Wynik.setText("WskaŸnik RFM wynosi : " + formattedFloat +" Nadwaga");}
				}
				else
				{
				float K = 72-(20*Float.valueOf(Wzrost.getText()).floatValue()/Float.valueOf(Talia.getText()).floatValue());
				String formattedFloat = String.format("%.02f", K);
					if(K<21.0) {
						Wynik.setText("WskaŸnik RFM wynosi : " +formattedFloat + " Niedowaga" );}	
					else if(21.0<= K && K<33.0) {
						Wynik.setText("WskaŸnik RFM wynosi : " + formattedFloat +" Zdrowy Poziom");}
					else if(K>=33.0)	{
						Wynik.setText("WskaŸnik RFM wynosi : " + formattedFloat +" Nadwaga");}	
				};
			
				}
				catch(Exception k) {
					Wynik.setText("Podaj poprawne dane");
					
				}
			};
		});
	 
	 
	 
	 Tluszcz_Panel.setBounds(310, 10, 400, 400);
	 Tluszcz_Panel.setLayout(null);
	 Tluszcz_Panel.setVisible(true); 
	 
 };
 
 
}
