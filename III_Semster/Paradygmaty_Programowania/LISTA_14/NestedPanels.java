import java.awt.*;       // Container; FlowLayout; BorderLayout;
import javax.swing.*;    // JButton; JPanel; JLabel; JComboBox;
import java.awt.event.*; // ActionEvent; ActionListener; ItemEvent; ItemListener;

public class NestedPanels extends JFrame {
    private BorderLayout outLayout = new BorderLayout();
    private JButton[] inB, outB;
    private JLabel messageBar;
    private JComboBox<String> choice;

    public NestedPanels() {
        inB = new JButton[5];  // inner panel buttons
        outB = new JButton[5]; // outer panel buttons
        JPanel center = new JPanel();
        center.setBackground(Color.CYAN);
        center.setLayout(new BorderLayout(5,5));
        center.add(inB[0]=new JButton("Hide Center"), BorderLayout.CENTER);
        center.add(inB[1]=new JButton("Hide West"), BorderLayout.WEST);
        center.add(inB[2]=new JButton("Hide North"), BorderLayout.NORTH);
        center.add(inB[3]=new JButton("Hide East"), BorderLayout.EAST);
        center.add(inB[4]=new JButton("Hide South"), BorderLayout.SOUTH);

        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(outB[0]=new JButton("Show Center"));
        south.add(outB[4]=new JButton("Show South"));
        String[] choices = {"one","two","three","four","five"};
        choice = new JComboBox<String>(choices);
        choice.setMaximumRowCount( 3 );
        south.add(choice);
        messageBar = new JLabel("This is a message bar.");
        choice.addItemListener( new ItemEventHandler() );
        south.add(messageBar);

        Container c = getContentPane();
        c.setLayout(outLayout);
        c.add(outB[1]=new JButton("Show West"), BorderLayout.WEST);
        c.add(outB[2]=new JButton("Show North"), BorderLayout.NORTH);
        c.add(outB[3]=new JButton("Show East"), BorderLayout.EAST);
        c.add(south, BorderLayout.SOUTH);
        c.add(center, BorderLayout.CENTER);

        ButtonEventHandler bhandler = new ButtonEventHandler();
        for (int i=0; i<5; i++) {
            inB[i].addActionListener(bhandler); outB[i].addActionListener(bhandler);
        }
    }

    private class ItemEventHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event)  {
            if (event.getStateChange() == ItemEvent.SELECTED)
                messageBar.setText("Choice selected: " + event.getItem());
        }
    }

    private class ButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            messageBar.setText("Button pushed: " + source.getText());
            for (int i = 0; i < inB.length; i++ ) {
                if (source == inB[i])  source.setVisible ( false );
                if (source == outB[i]) inB[i].setVisible ( true );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                NestedPanels frame = new NestedPanels();
                frame.setTitle("NestedPanels");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setSize(500, 200 );
                frame.setResizable(false);  // nie można zmieniać rozmiaru okna
                frame.setLocation(300,300); // współrzędne lewego górnego rogu okna
                frame.setVisible(true);
            }
        });
    }
}



