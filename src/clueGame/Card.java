package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String cardName, CardType cardType) {
		
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
		return false;
	}
}
