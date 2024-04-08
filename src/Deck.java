import java.util.*;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public Deck(ArrayList<Card> hand) {
        deck = Card.buildDeck();
        this.hand = hand;
        int i = 0;
        while (i < deck.size()) {
            Card deckCard = deck.get(i);
            boolean cardRemoved = false;

            for (int j = 0; j < hand.size() && !cardRemoved; j++) {
                Card handCard = hand.get(j);
                if (deckCard.getValue().equals(handCard.getValue()) && deckCard.getSuit().equals(handCard.getSuit())) {
                    deck.remove(deckCard);
                    cardRemoved = true;
                    i--;
                }
            }

            if (!cardRemoved) {
                i++;
            }
        }


    }

    public Card genNewCard() {
        int rand = (int) (Math.random() * deck.size());
        Card c = deck.get(rand);
        removeCard(c);
        return c;
    }

    public void removeCard(Card c) {
        deck.remove(c);
    }

    public void replaceCard() {
        boolean replaced = false;
        for (int i = 0; i < hand.size() && !replaced; i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                Card newest = genNewCard();
                hand.set(i, newest);
                replaced = true;
            }
        }
    }
}