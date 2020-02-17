import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Board extends Thread {

	static ArrayList<Tramp> board = new ArrayList<Tramp>();
	static int stackPoint = 0;
	static Tramp usedCard = null;//プレイヤーが使ったカードを一時出来に保存する変数


	public static void playerPut() {
		//プレイヤーが出すカードを選んで入れる
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		while(true)
		try {
				System.out.println("【場に出すカードの選択】"
						+ "\n数字を入力しエンターキーを押してください");
				System.out.println();
				String str =  br.readLine();
				int choice = Integer.parseInt(str);
				board.add(Player.player_list[0].hand.get(choice-1));
				usedCard =Player.player_list[0].hand.get(choice-1);
				return;

		} catch(Exception e) {
			// TODO: handle exception
			System.out.println("その入力は無効です");
		}
	}
			
//		}5人のCPUが入れる
	public static void cpuPut(){
		 for(int x=0;x<Player.player_list.length-1;x++) {
			 	board.add(Player.player_list[x+1].hand.get(0));
		 }
}

	//ボードに出たカードを確認する
	public static void showBoard() {
		for(int i=0;i<Player.player_list.length;i++) {
		System.out.print(Player.player_list[i].getPlayerName()+"が出したカードは ");
		System.out.println(board.get(i).getCardName()+" です");
		try {
			sleep(600);
		}
		catch(InterruptedException e) {	}
		}
	}

	//ボードに出たカードの強さを比較する
	public static void  checkBoard() {
		  Collections.sort(board, new MyComp());
		  try {
				sleep(750);
			}
			catch(InterruptedException e) {	}
//		  for(Tramp c : board) {
//			  System.out.println(c.getCardName() + ", " + c.getCardStr());
//		  }
		  //[4][5]のカードを比較し、同じならスタックに６点
		  //違うなら[5]のカードをハンドに持つプレイヤーを特定し、6点加算
		  if(board.get(4).getCardStr()==board.get(5).getCardStr()) {
			  stackPoint += 6;
			  System.out.println("スタックに6点追加します");
		  }else {
			  for(int i=0;i<6;i++) {
					if(Player.player_list[i].hand.contains(board.get(5))) {
						Player.player_list[i].addPoint(6);
						System.out.println(Player.player_list[i].getPlayerName()+"が6点獲得しました");
							if(stackPoint != 0) {
								getStack(i);
							}
					}
			  }
		  }
		  //プレイヤーの使った手札を削除する
		  Player.player_list[0].hand.remove(usedCard);
		  //CPUの使用した手札を削除する
		  for(int x=0;x<Player.player_list.length-1;x++) {
			  Player.player_list[x+1].removeCard();
		  }
		  board.clear();
	}
	//スタックに積まれた点数を獲得して、スタックを空にする。
	public static void getStack(int i) {
		Player.player_list[i].addPoint(stackPoint);
		System.out.println("スタックの "+stackPoint+"点 も獲得しました");
		stackPoint=0;
	}
}

