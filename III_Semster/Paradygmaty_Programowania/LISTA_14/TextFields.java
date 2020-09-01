// Program ilustrujący użycie pola tekstowego (JTextField)         
import java.awt.*;        // Container, FlowLayout
import java.awt.event.*;  // ActionEvent, ActionListener
import javax.swing.*;     // SwingUtilities, JFrame, JTextField, JLabel

public class TextFields extends JFrame {
    private JTextField inText, outText;
    private JLabel inLabel = new JLabel("  input");
    private JLabel outLabel = new JLabel("output");

    public TextFields() {
        super ( "Text Fields" );
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        inText = new JTextField(10);
        inText.addActionListener((ActionEvent e) -> {outText.setText( e.getActionCommand() ); inText.setText("");});
/* tu też jest metoda SAM	 
     inText.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e)  {         
                outText.setText( e.getActionCommand() );
                inText.setText("");
          }
     });  // koniec anonimowej klasy wewnętrznej  
*/
        c.add(inLabel);
        c.add(inText);

        outText = new JTextField(10);
        outText.setEditable( false );
        c.add(outLabel);
        c.add(outText);
    }

    public static void main(String[] args) {
        final int width = 200, height = 100;
        SwingUtilities.invokeLater (new Runnable() { // tu też jest metoda SAM
            public void run() {
                TextFields frame = new TextFields( );
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize( width, height );
                //             frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}