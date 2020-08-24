package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Proby implements ActionListener {
	JTextField text;
	
	JButton przycisk1;
	JButton przycisk2;
	JButton przycisk3;
	JButton przycisk4;
	JButton przycisk5;
	JButton przycisk6;
	JButton przycisk7;
	JButton przycisk8;
	JButton przycisk9;
	JButton przycisk0;
	
	public void actionPerformed(ActionEvent zdarzenie) 
	{
		
		if (zdarzenie.getSource() == przycisk1) 
		{
			przycisk3.setText("1");
		}
		
		if (zdarzenie.getSource() == przycisk2) 
		{
			przycisk2.setText("dzia³a?");
		}		
	}
	
	public void doRysuj() {
		
		JFrame ramka = new JFrame();
		
		
		JButton przycisk1 = new JButton("1");
		JButton przycisk2 = new JButton("2");
		JButton przycisk3 = new JButton("3");
		JButton przycisk4 = new JButton("4");
		JButton przycisk5 = new JButton("5");
		JButton przycisk6 = new JButton("6");
		JButton przycisk7 = new JButton("7");
		JButton przycisk8 = new JButton("8");
		JButton przycisk9 = new JButton("9");
		JButton przycisk0 = new JButton("0");
		
		przycisk1.addActionListener(this);
		przycisk2.addActionListener(this);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();	
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ramka.getContentPane().add(BorderLayout.NORTH, panel1);
		ramka.getContentPane().add(BorderLayout.WEST, panel2);
		
		ramka.getContentPane().add(panel3, FlowLayout.CENTER);
	
		ramka.getContentPane().add(BorderLayout.EAST, panel4);
		ramka.getContentPane().add(BorderLayout.SOUTH, panel5);
		
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		
		JTextField text = new JTextField(30);
		panel1.add(text);
		text.setText("Kalkulator");
		
		
		panel3.add(przycisk1);
		panel3.add(przycisk2);
		panel3.add(przycisk3);
		panel3.add(przycisk4);
		panel3.add(przycisk5);
		panel3.add(przycisk6);
		panel3.add(przycisk7);
		panel3.add(przycisk8);
		panel3.add(przycisk9);
		panel3.add(przycisk0);
		
		ramka.setSize(300,300);
		ramka.pack();	
		ramka.setResizable(false);
		ramka.setVisible(true);
		

	}
	
	
	
	public static void main(String[] args) {
		Proby gui = new Proby();
		gui.doRysuj();
	}
	

}
