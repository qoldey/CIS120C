
public class AnonymousClient implements Comparable {

	public static void main(String[] args) {
		Comparable withAnonymous;
		withAnonymous = new Comparable() {
			public int compareTo(Card c1, Card c2) {
				return 13 * (c1.suit - c2.suit) + (c1.rank - c2.rank);
			}
		};

		Card card1 = new Card(1, 2);
		Card card2 = new Card(2, 2);

		int diff = withAnonymous.compareTo(card1, card2);
		System.out.println(diff);

	}

	@Override
	public int compareTo(Card c1, Card c2) {
		return 13 * (c1.suit - c2.suit) + (c1.rank - c2.rank);
	}

}