package Kalkulator_Serwer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class Welcome_Page {
	JFrame Welcome;
	static JLabel Welcome_msg, User, Date;
	JTextField Password;
	JButton logowanie, Rejestracja, Kalorie, Tluszcz, Zapotrzebowanie;
	static JPanel Main_Panel = new JPanel();
	JPanel Empty = new JPanel();
	static CardLayout CLayout = new CardLayout();

Welcome_Page(){
	Users user = new Users();
	DATA_NOW data = new DATA_NOW();
	Logowanie LOG = new Logowanie();
	Rejestracja Reg = new Rejestracja();
	Kalkulator Kal = new Kalkulator();
	Tluszcz FAT = new Tluszcz();
	
	Main_Panel.setLayout(CLayout);
	
	
	
	
	Main_Panel.setBounds(50, 190, 870, 550);
	//Main_Panel.setOpaque(true);
	//Main_Panel.setBackground(Color.BLUE);;
	
	Welcome = new JFrame("Moje Zdrowie");
	Welcome.setSize(1000, 800);
	

	
	Welcome_msg = new JLabel("Moje Zdrowie", SwingConstants.CENTER);
	Welcome_msg.setBounds(10, 30, 300, 60);
	Welcome_msg.setFont(new Font("Lobster", Font.PLAIN, 50));
	Welcome_msg.setOpaque(true);
	//Welcome_msg.setBackground(Color.green);


	
	logowanie = new JButton("Logowanie");
	logowanie.setBounds(330, 30, 100, 60);
	
	Rejestracja = new JButton("Rejestracja");
	Rejestracja.setBounds(440, 30, 100, 60);
	
	Kalorie = new JButton("Baza Danych Kalorie");
	Kalorie.setBounds(550, 30, 150, 60);
	
	Tluszcz = new JButton("RFM Kalkulator");
	Tluszcz.setBounds(710, 30, 150, 60);
	
	Zapotrzebowanie = new JButton("Zapotrzebowanie");
	Zapotrzebowanie.setBounds(770, 30, 150, 60);
	
	User = new JLabel("User: gosc", SwingConstants.LEFT);
	User.setFont(new Font("Serif", Font.PLAIN, 20));
	User.setBounds(20, 100, 300, 30);
	
	

	
	Date = new JLabel("Data: " +  data.current_date, SwingConstants.LEFT);
	Date.setFont(new Font("Serif", Font.PLAIN, 20));
	Date.setBounds(20, 140, 170, 30);
	
	Main_Panel.add(Empty, "1");
	Main_Panel.add(LOG.Okno, "2");
	Main_Panel.add(Reg.Rejestr_Form, "3");
	Main_Panel.add(Kal.Kalkulator, "4");
	Main_Panel.add(FAT.Tluszcz_Panel, "5");
	CLayout.show(Main_Panel, "1");
	
	logowanie.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CLayout.show(Main_Panel, "2");
			
		}
	});
	
	Rejestracja.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CLayout.show(Main_Panel, "3");
			
		}
	});
	
	Kalorie.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CLayout.show(Main_Panel, "4");
			
		}
	});
	Tluszcz.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CLayout.show(Main_Panel, "5");
			
		}
	});
	
	Welcome.add(Welcome_msg);
	Welcome.add(logowanie);
	Welcome.add(Rejestracja);
	Welcome.add(Kalorie);
	Welcome.add(Tluszcz);
	
	Welcome.add(User);
	Welcome.add(Date);
	Welcome.add(Main_Panel);
	
	
	Welcome.setLayout(null);
	Welcome.setLocationRelativeTo(null);
	Welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Welcome.setResizable(false); 

	Welcome.setVisible(true); 
	};

}

