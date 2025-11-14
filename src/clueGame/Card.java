package clueGame;

import java.util.Objects;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public boolean equals(Card target) {
		return this.cardName.equals(target.cardName) && this.cardType == target.cardType; 
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Card)) return false;
		Card c = (Card) o;
		return this.cardName.equals(c.cardName) && this.cardType == c.cardType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cardName, cardType);
	}
}
