import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class TossACoin extends JFrame {
    private static final String headsLabelPrefix = "Heads: ";
    private static final String tailsLabelPrefix = "Tails: ";
    private int heads = 0;
    private int tails = 0;
    private final JLabel headsLabel = new JLabel(headsLabelPrefix + heads);
    private final JLabel tailsLabel = new JLabel(tailsLabelPrefix + tails);
    private final JButton button;
    public TossACoin() {
        super("Toss a coin");

        final Container c = getContentPane();
        c.setLayout(new GridLayout());

        button = new JButton("Toss a coin");
        button.addActionListener(e -> {
            final boolean hit = ThreadLocalRandom.current().nextBoolean();
            if(hit) {
                heads++;
                headsLabel.setText(headsLabelPrefix + heads);
            } else {
                tails++;
                tailsLabel.setText(tailsLabelPrefix + tails);
            }
        });

        c.add(button);
        c.add(headsLabel);
        c.add(tailsLabel);
    }

}