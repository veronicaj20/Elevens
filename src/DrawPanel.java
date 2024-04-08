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
    private ArrayList<Card> deck;
    private Deck currentDeck;
    private Rectangle button;
    private Rectangle otherButton;
    private boolean won = false;
    private boolean lost = false;
    private ArrayList<Card> selected = new ArrayList<Card>();
    private ArrayList<Integer> positions = new ArrayList<Integer>();
    private int count;

    public DrawPanel() {
        button = new Rectangle(153, 230, 160, 26);
        otherButton = new Rectangle(153, 285, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
        currentDeck = new Deck(hand);
        deck = currentDeck.getDeck();
        count = deck.size();
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
            g.drawString("REPLACE CARDS", 155, 305);
        g.drawString("CARDS LEFT: " + count, 10, 450);
        if (won){
            g.drawString("YOU WON!!", 50, 400);
        }
        if (lost){
            g.drawString("YOU LOST!", 50, 400);
        }
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawRect((int)otherButton.getX(), (int)otherButton.getY(), (int)otherButton.getWidth(), (int)otherButton.getHeight());

    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();
        if (e.getButton() == 1 || e.getButton() == 3) {
            if (button.contains(clicked)) {
                hand = Card.buildHand();
                deck = new Deck(hand).getDeck();
                won = false;
                lost = false;
            }
            for (int i = 0; i < hand.size(); i++){
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)){
                    hand.get(i).flipHighlight();
                    boolean currHighlight = hand.get(i).getHighlight();
                    if (currHighlight){
                        selected.add(hand.get(i));
                        positions.add(i);
                    }
                    else{
                        Integer x = i;
                        selected.remove(hand.get(i));
                        positions.remove(positions.indexOf(x));
                    }
                }
            }

            if (otherButton.contains(clicked)){
                if (currentDeck.replaceCard(selected)){
                    for (int i = 0; i < selected.size(); i++){
                        Card replaced = selected.get(i);
                        int position = positions.get(i);
                        Card c = currentDeck.genNewCard();
                        hand.remove(position);
                        hand.add(position, c);
                        replaced.flipHighlight();
                        count -= selected.size();
                    }
                    resetSelected();
                    resetPositions();
                }
            }
        }
        count = deck.size();
        if (count == 0) won = true;
        if (currentDeck.noMoves(hand)) lost = true;
    }

    public void resetSelected(){
        selected = new ArrayList<Card>();
    }
    public void resetPositions(){
        positions = new ArrayList<Integer>();
    }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}