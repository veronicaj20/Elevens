import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(153, 230, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 130;
        int y = 10;
        for (int i = 0; i < hand.size() - 6; i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 10;
        }

        int x2 = 130;
        int y2 = 80;
        for (int i = 3; i < hand.size() - 3; i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                g.drawRect(x2, y2, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x2, y2);
            g.drawImage(c.getImage(), x2, y2, null);
            x2 = x2 + c.getImage().getWidth() + 10;
        }

        int x3 = 130;
        int y3 = 150;
        for (int i = 6; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                g.drawRect(x3, y3, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x3, y3);
            g.drawImage(c.getImage(), x3, y3, null);
            x3 = x3 + c.getImage().getWidth() + 10;
        }


        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 155, 250);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                hand = Card.buildHand();
            }

            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }

//                for (int j = 0; j < hand.size(); j++) {
//                    if (hand.get(j).getHighlight()) {
//
//                    }
//                }

            }


        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}