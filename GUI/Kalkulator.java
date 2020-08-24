package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Kalkulator implements ActionListener {
	
	private int dzialanie;
	private int odp;
	private double a;
	private double b; 
	private double wynik;
	private String t;
	private String liczba;
//	private String zapisane;
	private JLabel tytul;
//	private JLabel spis;
//	private JLabel lista;
	private JTextField text;
	private JButton przycisk0;
	private JButton przycisk1;
	private JButton przycisk2;
	private JButton przycisk3;
	private JButton przycisk4;
	private JButton przycisk5;
	private JButton przycisk6;
	private JButton przycisk7;
	private JButton przycisk8;
	private JButton przycisk9;
	private JButton przyciskRazy;
	private JButton przyciskDziel;
	private JButton przyciskRowna;
//	private JButton przyciskReset;
//	private JButton przyciskZapisz;
	
	
	public void dzialanie(int dz) {
		switch(dz) {
			case 1:
				wynik = a*b;
				break;
			case -1:
				wynik = a/b;
				break;
			default:
				wynik = 0;
		}
	}
	
	
	public void actionPerformed(ActionEvent zdarzenie) {
		odp= 0;
		if (zdarzenie.getSource() == przycisk0) {
			liczba+=0;
			t+=0;
		}
		if (zdarzenie.getSource() == przycisk1) {
			liczba+=1;
			t+=1;
		}	
		if (zdarzenie.getSource() == przycisk2) {
			liczba+=2;
			t+=2;
		}
		if (zdarzenie.getSource() == przycisk3) {
			liczba+=3;
			t+=3;
		}	
		if (zdarzenie.getSource() == przycisk4) {
			liczba+=4;
			t+=4;
		}
		if (zdarzenie.getSource() == przycisk5) {
			liczba+=5;
			t+=5;
		}	
		if (zdarzenie.getSource() == przycisk6) {
			liczba+=6;
			t+=6;
		}
		if (zdarzenie.getSource() == przycisk7) {
			liczba+=7;
			t+=7;
		}	
		if (zdarzenie.getSource() == przycisk8) {
			liczba+=8;
			t+=8;
		}
		if (zdarzenie.getSource() == przycisk9) {
			liczba+=9;
			t+=9;
		}
/*		if (zdarzenie.getSource() == przyciskReset) {
			t="";
		}
		if (zdarzenie.getSource() == przyciskZapisz) {
			zapisane += text.getText() +"\n";
			lista.setText(zapisane);
		}													*/
		if (zdarzenie.getSource() == przyciskRazy) {
			a= Double.parseDouble(liczba);
			liczba="";
			dzialanie = 1;
			t+="*";
			przyciskRazy.setEnabled(false);
			przyciskDziel.setEnabled(false);
			przyciskRowna.setEnabled(true);
		}
		if (zdarzenie.getSource() == przyciskDziel) {
			a= Double.parseDouble(liczba);
			liczba="";
			dzialanie = -1;
			t+="/";
			przyciskDziel.setEnabled(false);
			przyciskRazy.setEnabled(false);
			przyciskRowna.setEnabled(true);
		}
		if (zdarzenie.getSource() == przyciskRowna) {
			b= Double.parseDouble(liczba);
			dzialanie(dzialanie);
			odp = 1;
			t+="=" + wynik;
			przyciskDziel.setEnabled(true);
			przyciskRazy.setEnabled(true);
			przyciskRowna.setEnabled(false);
			text.setText(t);
			t="";
			liczba="";
		}
		
		if(odp!=1)
			text.setText(t);
	}
	
	public void okno() {
		
		t= "";
		liczba="";
//		zapisane="";
		
		JFrame ramka = new JFrame();
		przycisk0 = new JButton("0");
		przycisk1 = new JButton("1");
		przycisk2 = new JButton("2");
		przycisk3 = new JButton("3");
		przycisk4 = new JButton("4");
		przycisk5 = new JButton("5");
		przycisk6 = new JButton("6");
		przycisk7 = new JButton("7");
		przycisk8 = new JButton("8");
		przycisk9 = new JButton("9");
//		przyciskReset = new JButton("Reset");
//		przyciskZapisz = new JButton("Zapisz");
		przyciskRazy = new JButton("*");
		przyciskDziel = new JButton("/");
		przyciskRowna = new JButton("=");
		
		tytul = new JLabel("Kalkulator");
//		spis = new JLabel("Twoja liczba:");
//		lista = new JLabel(" ");
		
		text = new JTextField(15);
		
		przycisk0.addActionListener(this);
		przycisk1.addActionListener(this);
		przycisk2.addActionListener(this);
		przycisk3.addActionListener(this);
		przycisk4.addActionListener(this);
		przycisk5.addActionListener(this);
		przycisk6.addActionListener(this);
		przycisk7.addActionListener(this);
		przycisk8.addActionListener(this);
		przycisk9.addActionListener(this);
//		przyciskReset.addActionListener(this);
//		przyciskZapisz.addActionListener(this);
		przyciskRazy.addActionListener(this);
		przyciskDziel.addActionListener(this);
		przyciskRowna.addActionListener(this);
		
		JPanel panel1 = new JPanel();		
		JPanel panel2 = new JPanel();	
		JPanel panel3 = new JPanel();
		
		ramka.getContentPane().add(BorderLayout.NORTH, panel2);
		ramka.getContentPane().add(BorderLayout.CENTER, panel1);
		ramka.getContentPane().add(BorderLayout.SOUTH, panel3);
		
//		panel3.add(spis);
		panel3.add(text);
//		panel3.add(przyciskReset);
//		panel3.add(przyciskZapisz);
	
		panel2.add(tytul);
		
		panel1.add(przycisk1);
		panel1.add(przycisk2);
		panel1.add(przycisk3);
		panel1.add(przycisk4);
		panel1.add(przycisk5);
		panel1.add(przycisk6);
		panel1.add(przycisk7);
		panel1.add(przycisk8);
		panel1.add(przycisk9);
		panel1.add(przycisk9);
		panel1.add(przyciskRazy);
		panel1.add(przycisk0);
		panel1.add(przyciskDziel);
		panel1.add(przyciskRowna);
//		panel1.add(lista);
		
		przyciskRowna.setEnabled(false);
		text.setEditable(false);
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.pack();
		ramka.setSize(180,300);
		ramka.setVisible(true);
		
	}
	
	public static void main(String[]args) {
		Kalkulator program= new Kalkulator();
		program.okno();
	}
	
}