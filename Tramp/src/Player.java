import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {

	//staticフィールド
	static Player[] player_list = new Player[6];
	static int playerCount = 0;

	//インスタンスフィールド
	List<Tramp>hand = new ArrayList<Tramp>();
	private String name;
	private int point =0;


	Player(String name){

		this.name = name;
		System.out.println(this.name+"を生成しました");
		player_list[playerCount]=this;
		playerCount++;

	}


	//6人のプレイヤーをオブジェクト配列で生成
	public static void playerBirth() {
		for (int i = 0;i<player_list.length;i++) {
			Player player = new Player("Player"+(i+1));
			}
		player_list[0].setPlayerName("あなた");
	}


//	手札を確認
	public  static void handShow() {
		System.out.println("あなたの手札は、");
		for(int x=0;x<player_list[0].hand.size();x++) {
			try {
				sleep(200);
			}
			catch(InterruptedException e) {	}
			System.out.print(" "+(x+1)+":"+player_list[0].hand.get(x).getCardName());

			}System.out.println();
		//6人全員の手札を確認(デバッグ用)
//		for(int i=0;i<6;i++) {
//			System.out.println(player_list[i].name+"のハンドは、");
//			for(int x=0;x<player_list[i].hand.size();x++) {
//			System.out.println(player_list[i].hand.get(x).getCardName());
//
//			}
//			System.out.println("です");
//		}
	}

	//引数で渡されたカードをデッキからハンドに加える
	  void setCard(Tramp t)
	  {
		 hand.add(t);
	 }

	 //ポイントを加算する
	  void addPoint(int winPoint) {
		  point += winPoint;
	  }

	  //プレイヤーの名前セッター
	  void setPlayerName(String name)
	  {
		 this.name=name;
	 }

	//1枚目の手札を削除する
	  void removeCard() {
		  hand.remove(0);
	  }

	//ゲッターたち
	  public String getPlayerName() {
		  return this.name;
	  }

	  public int getPlayerPoint() {
		  return this.point;
	  }
}


