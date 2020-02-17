
public class test01 extends Thread {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Tramp.deckBirth();



		Tramp.cardBirth();

		Player.playerBirth();

		Dealer.dealCard();
		System.out.println();
		System.out.println("★ゲームを開始します★");
		try {
			sleep(800);
		}
		catch(InterruptedException e) {	}

		for(int i=0;i<9;i++) {
		Dealer.gameOnce();
		try {
			sleep(1500);
		}
		catch(InterruptedException e) {	}
		}
		
		Dealer.judgeGame();


}
}