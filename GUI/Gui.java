package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui implements ActionListener {
	private JButton przycisk1;
	private JButton przycisk2;
	private int ile1, ile2;

	
	public void actionPerformed(ActionEvent zdarzenie) 
	{
		
		if (zdarzenie.getSource() == przycisk1) 
		{
			ile1++;
			przycisk1.setText("Zosta³em kliniêty razy " + ile1);
		}
		
		if (zdarzenie.getSource() == przycisk2) 
		{
			ile2++;
			przycisk2.setText("Zosta³em kliniêty razy " + ile2);
		}		
	}
	
	public void doRysuj() {
		
		JFrame ramka = new JFrame();
		przycisk1 = new JButton("Kliknij mnie");
		przycisk2 = new JButton("Kliknij mnie");
	
		
		przycisk1.addActionListener(this);
		przycisk2.addActionListener(this);
		
		JPanel panel = new JPanel();		
		ramka.getContentPane().add(panel);
		panel.add(przycisk1);
		panel.add(przycisk2);
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.pack();
		ramka.setSize(500,300);
		ramka.setVisible(true);
	}
	
	public static void main(String[]args) {
		Gui g= new Gui();
		g.doRysuj();
	}
}
 