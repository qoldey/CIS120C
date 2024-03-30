/**
 * A standard playing card.
 */
public class Card {

    enum Rank {
        NULL, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    };

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    };

    Suit suit;
    Rank rank;

    /**
     * Constructs a card of the given rank and suit.
     */
    public Card(Rank rank, Suit suit) {

        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns a negative integer if this card comes before
     * the given card, zero if the two cards are equal, or
     * a positive integer if this card comes after the card.
     */
    public int compareTo(Card that) {
        if (this.suit.ordinal() < that.suit.ordinal()) {
            return -1;
        }
        if (this.suit.ordinal() > that.suit.ordinal()) {
            return 1;
        }
        if (this.rank.ordinal() < that.rank.ordinal()) {
            return -1;
        }
        if (this.rank.ordinal() > that.rank.ordinal()) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns true if the given card has the same
     * rank AND same suit; otherwise returns false.
     */
    public boolean equals(Card that) {
        return this.rank == that.rank
                && this.suit == that.suit;
    }

    /**
     * Gets the card's rank.
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Gets the card's suit.
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Returns the card's index in a sorted deck of 52 cards.
     */
    public int position() {
        return this.suit.ordinal() * 13 + this.rank.ordinal() - 1;
    }

    /**
     * Returns a string representation of the card.
     */
    public String toString() {
        return this.rank + " of " + this.suit;
    }
}
