package テキサスホールデム;

	import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

	public class Player extends Thread {

		//staticフィールド
		static Player[] player_list = new Player[2];
		static int playerCount = 0;

		//インスタンスフィールド
		private String name;
		List<Tramp>hand = new ArrayList<Tramp>();
		List<Tramp>board_and_hand = new ArrayList<Tramp>();
		List<Tramp>a_poker_hand =new ArrayList<Tramp>();
		private int board_and_handStr =1;

		private int countSpade;
		private int countClub;
		private int countDiyamond;
		private int countHeart;

		private int countA;
		private int countK;
		private int countQ;
		private int countJ;
		private int count10;
		private int count9;
		private int count8;
		private int count7;
		private int count6;
		private int count5;
		private int count4;
		private int count3;
		private int count2;

//		private int point =0;


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


//		手札を確認
		public  static void handShow() {
			System.out.println("あなたの手札は、");
			for(int x=0;x<player_list[0].hand.size();x++) {
				try {
					sleep(200);
				}
				catch(InterruptedException e) {	}
				System.out.print(player_list[0].hand.get(x).getCardName()+" ");

				
			}System.out.println();

			//CPUの手札を確認(デバッグ用)

				System.out.println(player_list[1].name+"のハンドは、");
				for(int x=0;x<player_list[1].hand.size();x++) {
				System.out.print(player_list[1].hand.get(x).getCardName()+" ");
				}
				System.out.println();


			}

		//手札とボードの
		public static void sortHand() {
			Collections.sort(Player.player_list[0].board_and_hand, new MyComp());
			Collections.sort(Player.player_list[1].board_and_hand, new MyComp());

			for(int x=0;x<2;x++) {
				for(int y=0;y<Player.player_list[x].board_and_hand.size();y++) {
			switch (Player.player_list[x].board_and_hand.get(y).getCardStr()) {
			case 0:
				Player.player_list[x].count2++;
				break;
			case 1:
				Player.player_list[x].count3++;
				break;
			case 2:
				Player.player_list[x].count4++;
				break;
			case 3:
				Player.player_list[x].count5++;
				break;
			case 4:
				Player.player_list[x].count6++;
				break;
			case 5:
				Player.player_list[x].count7++;
				break;
			case 6:
				Player.player_list[x].count8++;
				break;
			case 7:
				Player.player_list[x].count9++;
				break;
			case 8:
				Player.player_list[x].count10++;
				break;
			case 9:
				Player.player_list[x].countJ++;
				break;
			case 10:
				Player.player_list[x].countQ++;
				break;
			case 11:
				Player.player_list[x].countK++;
				break;
			case 12:
				Player.player_list[x].countA++;
				break;

			default:
				break;
			}
			switch (Player.player_list[x].board_and_hand.get(y).getCardSuit()) {
			case "スペード":
				Player.player_list[x].countSpade++;
				break;
			case "クローバー":
				Player.player_list[x].countClub++;
				break;
			case "ダイヤ":
				Player.player_list[x].countDiyamond++;
				break;
			case "ハート":
				Player.player_list[x].countHeart++;
				break;

			default:
				break;
			}
			}
			}
		}


		//引数で渡されたカードをデッキからハンドに加える
		  void setCard(Tramp t)
		  {
			 hand.add(t);
		 }



		  //プレイヤーの名前セッター
		  void setPlayerName(String name)
		  {
			 this.name=name;
		 }

		  public void setBoard_and_handStr(int i) {
			  this.board_and_handStr=i;
		  }

		//1枚目の手札を削除する
		  void removeCard() {
			  hand.remove(0);
		  }

		//ゲッターたち
		  public String getPlayerName() {
			  return this.name;
		  }

		  public int getCount2() {
			  return this.count2;
		  }
		  public int getCount3() {
			  return this.count3;
		  }
		  public int getCount4() {
			  return this.count4;
		  }
		  public int getCount5() {
			  return this.count5;
		  }
		  public int getCount6() {
			  return this.count6;
		  }
		  public int getCount7() {
			  return this.count7;
		  }
		  public int getCount8() {
			  return this.count8;
		  }
		  public int getCount9() {
			  return this.count9;
		  }
		  public int getCount10() {
			  return this.count10;
		  }
		  public int getCountJ() {
			  return this.countJ;
		  }
		  public int getCountQ() {
			  return this.countQ;
		  }
		  public int getCountK() {
			  return this.countK;
		  }
		  public int getCountA() {
			  return this.countA;
		  }

		  public int getCountSpade() {
			  return this.countSpade;
		  }
		  public int getCountClub() {
			  return this.countClub;
		  }
		  public int getCountDiyamond() {
			  return this.countDiyamond;
		  }
		  public int getCountHeart() {
			  return this.countHeart;
		  }

		  public int getBoard_and_handStr() {
			  return this.board_and_handStr;
		  }




//		  public int getPlayerPoint() {
//			  return this.point;
//		  }
	}

