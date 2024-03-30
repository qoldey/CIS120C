
public class CardDriver {
    public static void main(String[] args) {
        Card testCard = new Card(Card.Rank.FOUR, Card.Suit.HEARTS);
        Card testCard2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);
        Card testCard3 = testCard;

        System.out.println(testCard.rank);

        int testCompare = testCard.compareTo(testCard2);
        System.out.println(testCompare);

        boolean testEquals = testCard.equals(testCard3);
        System.out.println(testEquals);

        System.out.println(testCard);
    }
}
