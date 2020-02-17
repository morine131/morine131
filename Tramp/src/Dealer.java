import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dealer {

	static int gameTurn=0;


	//それぞれのプレイヤーに9枚のカードを配る
	  public static void dealCard()
	  {
		 Collections.shuffle(Tramp.deck);

		 for(int x=0;x<Player.player_list.length;x++) {
		 	for(int y=0;y<9;y++) {
		 	Player.player_list[x].setCard(Tramp.deck.get(0));
		 	Tramp.deck.remove(0);
		 	}
		 }
	  }

	  public static void gameOnce() {
		  System.out.println();

		  gameTurn++;
		  if(gameTurn==9) {
			  System.out.print("【ラストターン】");
		  }else {
		  System.out.print("【"+gameTurn+"ターン目"+"】");
		   }
		  System.out.println(" ・現在のスタック:"+Board.stackPoint);
		  
			Player.handShow();
		    Board.playerPut();
		    Board.cpuPut();

			Board.showBoard();

			Board.checkBoard();
	  }
	  //結果発表
	  public static void judgeGame() {
		  List<Player>judge = Arrays.asList(Player.player_list);
		  System.out.println();
		  System.out.println("【ゲーム終了】");
		  System.out.println();
		  System.out.println("★結果発表★");
		  Collections.sort(judge, new MyComp02());
		  for(Player c : judge) {
			  System.out.println(c.getPlayerName() + ", " + c.getPlayerPoint());
		  }
	  }
}