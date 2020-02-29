package テキサスホールデム;

public class test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		for(int i=0;i<10000;i++) {
		Tramp.deckBirth();

		Tramp.cardBirth();

		Player.playerBirth();

		Dealer.dealCard();

		Player.handShow();

		Dealer.dealFropCard();
		Dealer.dealTurnCard();
		Dealer.dealRiverCard();

		Dealer.judgeHand();
		}

		System.out.println(Dealer.StraightFlash) ;
		 System.out.println(Dealer.FourCard) ;
		 System.out.println(Dealer.FullHouse) ;
		 System.out.println(Dealer.Flash) ;
		 System.out.println(Dealer.Straight) ;
		 System.out.println(Dealer.ThreeCard) ;
		 System.out.println(Dealer.TwoPair) ;
		 System.out.println(Dealer.OnePair) ;
		 System.out.println(Dealer.HighCard) ;
	}

}
