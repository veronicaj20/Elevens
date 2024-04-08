import java.util.*;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public Deck(ArrayList<Card> hand) {
        deck = Card.buildDeck();
        this.hand = hand;
        for (int i = 0; i < hand.size(); i++) {
            Card current = hand.get(i);
            for (int j = 0; j < deck.size(); j++) {
                Card x = deck.get(j);
                if (current.getValue().equals(x.getValue()) && current.getSuit().equals(x.getSuit())) {
                    deck.remove(x);
                    j--;
                }
            }
        }


    }

    public Card genNewCard() {
        int rand = (int) (Math.random() * deck.size());
        Card c = deck.get(rand);
        removeCard(c);
        return c;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void removeCard(Card c) {
        deck.remove(c);
    }

    public boolean replaceCard(ArrayList<Card> selected) {
        int total = 0;
        boolean jack = false;
        boolean queen = false;
        boolean king = false;
        for (Card c : selected) {
            if (c.getValue().equals("J") || c.getValue().equals("Q") || c.getValue().equals("K") || c.getValue().equals("A")) {
                if (c.getValue().equals("J")) jack = true;
                if (c.getValue().equals("Q")) queen = true;
                if (c.getValue().equals("K")) king = true;
                if (c.getValue().equals("A")) {
                    total++;
                }
            } else {
                total += Integer.parseInt(c.getValue());
            }
        }
        if (jack && queen && king && selected.size() == 3) return true;
        else if (total == 11) return true;
        return false;
    }

    public boolean noMoves(ArrayList<Card> cards) {
        ArrayList<Card> temp = new ArrayList<Card>();
        temp.addAll(cards);
        boolean jack = false;
        boolean queen = false;
        boolean king = false;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getValue().equals("J") || temp.get(i).getValue().equals("Q") || temp.get(i).getValue().equals("K")) {
                if (temp.get(i).getValue().equals("J")) jack = true;
                if (temp.get(i).getValue().equals("Q")) queen = true;
                if (temp.get(i).getValue().equals("K")) king = true;
                temp.remove(i);
                i--;
            }
        }
        if (jack && queen && king) {
            return false;
        }
        for (int i = 0; i < temp.size(); i++){
            int currSum = 0;
            if (temp.get(i).getValue().equals("A")) currSum++;
            else currSum = Integer.parseInt(temp.get(i).getValue());
            for (int j = i + 1; j < temp.size(); j++){
                int add = 0;
                if (temp.get(j).getValue().equals("A")) add = 1;
                else{
                    add = Integer.parseInt(temp.get(j).getValue());
                }
                if (currSum + add == 11) {
                    return false;
                }
            }
        }
        return true;
    }
}