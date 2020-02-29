package テキサスホールデム;

import java.util.Collections;

public class Dealer {

	static int gameTurn=0;
	static Tramp sfChecker[];

	static int StraightFlash = 0;
	static int  FourCard= 0;
	static int FullHouse = 0;
	static int Flash = 0;
	static int Straight = 0;
	static int ThreeCard = 0;
	static int TwoPair = 0;
	static int OnePair = 0;
	static int HighCard = 0;



	//それぞれのプレイヤーに2枚のカードを配る
	public static void dealCard()
	{
		Collections.shuffle(Tramp.deck);

		for(int x=0;x<Player.player_list.length;x++) {
			for(int y=0;y<2;y++) {
				Player.player_list[x].setCard(Tramp.deck.get(0));
				Player.player_list[x].board_and_hand.add(Tramp.deck.get(0));
				Tramp.deck.remove(0);
			}

		}
	}

	//フロップに3枚カードを出す
	public static void dealFropCard()
	{
		Collections.shuffle(Tramp.deck);

		Board.Frop01=Tramp.deck.get(0);
		Tramp.deck.remove(0);

		Board.Frop02=Tramp.deck.get(0);
		Tramp.deck.remove(0);

		Board.Frop03=Tramp.deck.get(0);
		Tramp.deck.remove(0);

		System.out.println("フロップのカードは、");
		System.out.println(Board.Frop01.getCardName()+" "+
				Board.Frop02.getCardName()+" "+
				Board.Frop03.getCardName());

		for(int y=0;y<2;y++) {
			Player.player_list[y].board_and_hand.add(Board.Frop01);
			Player.player_list[y].board_and_hand.add(Board.Frop02);
			Player.player_list[y].board_and_hand.add(Board.Frop03);
		}
	}

	//ターンとリバーのカードを配る
	public static void dealTurnCard()
	{
		Collections.shuffle(Tramp.deck);

		Board.Turn=Tramp.deck.get(0);
		Tramp.deck.remove(0);

		System.out.println("ターンのカードは、");
		System.out.println(Board.Turn.getCardName());

		for(int y=0;y<2;y++) {
			Player.player_list[y].board_and_hand.add(Board.Turn);
		}
	}
	public static void dealRiverCard()
	{
		Collections.shuffle(Tramp.deck);

		Board.River=Tramp.deck.get(0);
		Tramp.deck.remove(0);

		System.out.println("リバーのカードは、");
		System.out.println(Board.River.getCardName());

		for(int y=0;y<2;y++) {
			Player.player_list[y].board_and_hand.add(Board.River);
		}
	}

	//役の判定
	public static void judgeHand() {
		Player.sortHand();
		int pairCount=0;

		for(int x=0;x<2;x++) {
			if(
					(((Player.player_list[x].board_and_hand.get(0).getCardStr()+1
							== Player.player_list[x].board_and_hand.get(1).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+2
							== Player.player_list[x].board_and_hand.get(2).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+3
							== Player.player_list[x].board_and_hand.get(3).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+4
							== Player.player_list[x].board_and_hand.get(4).getCardStr())))){
						Tramp sfChecker[]= {Player.player_list[x].board_and_hand.get(0),
											Player.player_list[x].board_and_hand.get(1),
											Player.player_list[x].board_and_hand.get(2),
											Player.player_list[x].board_and_hand.get(3),
											Player.player_list[x].board_and_hand.get(4)};

					}

			else if(
					(((Player.player_list[x].board_and_hand.get(1).getCardStr()+1
							== Player.player_list[x].board_and_hand.get(2).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(2).getCardStr()+2
							== Player.player_list[x].board_and_hand.get(3).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(3).getCardStr()+3
							== Player.player_list[x].board_and_hand.get(4).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(4).getCardStr()+4
							== Player.player_list[x].board_and_hand.get(5).getCardStr())))){
						Tramp sfChecker[]= {Player.player_list[x].board_and_hand.get(1),
											Player.player_list[x].board_and_hand.get(2),
											Player.player_list[x].board_and_hand.get(3),
											Player.player_list[x].board_and_hand.get(4),
											Player.player_list[x].board_and_hand.get(5)};

					}

				else if(
						(((Player.player_list[x].board_and_hand.get(2).getCardStr()+1
								== Player.player_list[x].board_and_hand.get(3).getCardStr())
						&& (Player.player_list[x].board_and_hand.get(3).getCardStr()+2
								== Player.player_list[x].board_and_hand.get(4).getCardStr())
						&& (Player.player_list[x].board_and_hand.get(4).getCardStr()+3
								== Player.player_list[x].board_and_hand.get(5).getCardStr())
						&& (Player.player_list[x].board_and_hand.get(5).getCardStr()+4
								== Player.player_list[x].board_and_hand.get(6).getCardStr())))){
							Tramp sfChecker[]= {Player.player_list[x].board_and_hand.get(2),
												Player.player_list[x].board_and_hand.get(3),
												Player.player_list[x].board_and_hand.get(4),
												Player.player_list[x].board_and_hand.get(5),
												Player.player_list[x].board_and_hand.get(6)};

				}
			try {
				if		(sfChecker[0].suit ==sfChecker[1].suit&&
						sfChecker[0].suit ==sfChecker[2].suit&&
						sfChecker[0].suit ==sfChecker[3].suit&&
						sfChecker[0].suit ==sfChecker[4].suit) {
					Player.player_list[x].setBoard_and_handStr(9);
					System.out.println(Player.player_list[x].getPlayerName()+"の役はストレートフラッシュです");
					Straight++;
				}


			} catch (Exception e) {
				// TODO: handle exception
			}

			 if((Player.player_list[x].getCountA()==4
					|| Player.player_list[x].getCountK()==4
					|| Player.player_list[x].getCountQ()==4
					||Player.player_list[x].getCountJ()==4
					||Player.player_list[x].getCount10()==4
					||Player.player_list[x].getCount9()==4
					||Player.player_list[x].getCount8()==4
					||Player.player_list[x].getCount7()==4
					||Player.player_list[x].getCount6()==4
					||Player.player_list[x].getCount5()==4
					||Player.player_list[x].getCount4()==4
					||Player.player_list[x].getCount3()==4
					||Player.player_list[x].getCount2()==4)) {
				Player.player_list[x].setBoard_and_handStr(8);
				System.out.println(Player.player_list[x].getPlayerName()+"の役は4カードです");
				FourCard++;
			}

			else if(
					(Player.player_list[x].getCountA()==3
					|| Player.player_list[x].getCountK()==3
					|| Player.player_list[x].getCountQ()==3
					||Player.player_list[x].getCountJ()==3
					||Player.player_list[x].getCount10()==3
					||Player.player_list[x].getCount9()==3
					||Player.player_list[x].getCount8()==3
					||Player.player_list[x].getCount7()==3
					||Player.player_list[x].getCount6()==3
					||Player.player_list[x].getCount5()==3
					||Player.player_list[x].getCount4()==3
					||Player.player_list[x].getCount3()==3
					||Player.player_list[x].getCount2()==3)
					&&
					(Player.player_list[x].getCountA()==2
					|| Player.player_list[x].getCountK()==2
					|| Player.player_list[x].getCountQ()==2
					||Player.player_list[x].getCountJ()==2
					||Player.player_list[x].getCount10()==2
					||Player.player_list[x].getCount9()==2
					||Player.player_list[x].getCount8()==2
					||Player.player_list[x].getCount7()==2
					||Player.player_list[x].getCount6()==2
					||Player.player_list[x].getCount5()==2
					||Player.player_list[x].getCount4()==2
					||Player.player_list[x].getCount3()==2
					||Player.player_list[x].getCount2()==2)
					)
			{
				Player.player_list[x].setBoard_and_handStr(7);
				System.out.println(Player.player_list[x].getPlayerName()+"の役はフルハウスです");
				FullHouse++;
			}

			else if(
					Player.player_list[x].getCountSpade()==5
					|| Player.player_list[x].getCountClub()==5
					|| Player.player_list[x].getCountDiyamond()==5
					||Player.player_list[x].getCountHeart()==5)
			{
				Player.player_list[x].setBoard_and_handStr(6);
				System.out.println(Player.player_list[x].getPlayerName()+"の役はフラッシュです");
				Flash++;
			}

			else if(
					((Player.player_list[x].board_and_hand.get(0).getCardStr()+1
							== Player.player_list[x].board_and_hand.get(1).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+2
							== Player.player_list[x].board_and_hand.get(2).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+3
							== Player.player_list[x].board_and_hand.get(3).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(0).getCardStr()+4
							== Player.player_list[x].board_and_hand.get(4).getCardStr())

					|| (Player.player_list[x].board_and_hand.get(1).getCardStr()+1
							== Player.player_list[x].board_and_hand.get(2).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(1).getCardStr()+2
							== Player.player_list[x].board_and_hand.get(3).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(1).getCardStr()+3
							== Player.player_list[x].board_and_hand.get(4).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(1).getCardStr()+4
							== Player.player_list[x].board_and_hand.get(5).getCardStr())

					|| (Player.player_list[x].board_and_hand.get(2).getCardStr()+1
							== Player.player_list[x].board_and_hand.get(3).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(2).getCardStr()+2
							== Player.player_list[x].board_and_hand.get(4).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(2).getCardStr()+3
							== Player.player_list[x].board_and_hand.get(5).getCardStr())
					&& (Player.player_list[x].board_and_hand.get(2).getCardStr()+4
							== Player.player_list[x].board_and_hand.get(6).getCardStr())))
			{
				Player.player_list[x].setBoard_and_handStr(5);
				System.out.println(Player.player_list[x].getPlayerName()+"の役はストレートです");
				Straight++;
			}

			else if(
					Player.player_list[x].getCountA()==3
					|| Player.player_list[x].getCountK()==3
					|| Player.player_list[x].getCountQ()==3
					||Player.player_list[x].getCountJ()==3
					||Player.player_list[x].getCount10()==3
					||Player.player_list[x].getCount9()==3
					||Player.player_list[x].getCount8()==3
					||Player.player_list[x].getCount7()==3
					||Player.player_list[x].getCount6()==3
					||Player.player_list[x].getCount5()==3
					||Player.player_list[x].getCount4()==3
					||Player.player_list[x].getCount3()==3
					||Player.player_list[x].getCount2()==3)
			{
				Player.player_list[x].setBoard_and_handStr(4);
				System.out.println(Player.player_list[x].getPlayerName()+"の役は3カードです");
				ThreeCard++;
			}

			 		if(Player.player_list[x].getCountA()==2) {pairCount++;}
					if(Player.player_list[x].getCountK()==2) {pairCount++;}
					if(Player.player_list[x].getCountQ()==2) {pairCount++;}
					if(Player.player_list[x].getCountJ()==2) {pairCount++;}
					if(Player.player_list[x].getCount10()==2) {pairCount++;}
					if(Player.player_list[x].getCount9()==2) {pairCount++;}
					if(Player.player_list[x].getCount8()==2) {pairCount++;}
					if(Player.player_list[x].getCount7()==2) {pairCount++;}
					if(Player.player_list[x].getCount6()==2) {pairCount++;}
					if(Player.player_list[x].getCount5()==2) {pairCount++;}
					if(Player.player_list[x].getCount4()==2) {pairCount++;}
					if(Player.player_list[x].getCount3()==2) {pairCount++;}
					if(Player.player_list[x].getCount2()==2) {pairCount++;}

			{
				if(pairCount>=2 && (Player.player_list[x].getBoard_and_handStr()==1)) {
				Player.player_list[x].setBoard_and_handStr(3);
				System.out.println(Player.player_list[x].getPlayerName()+"の役はツーペアです");
				TwoPair++;
				}
				if(pairCount==1 && (Player.player_list[x].getBoard_and_handStr()==1)) {
					Player.player_list[x].setBoard_and_handStr(2);
					System.out.println(Player.player_list[x].getPlayerName()+"の役はワンペアです");
					OnePair++;
					}

			if(Player.player_list[x].getBoard_and_handStr()==1) {
				System.out.println(Player.player_list[x].getPlayerName()+"の役はハイカードです");
				HighCard++;
			}

				for (Tramp check:Player.player_list[x].board_and_hand ){
					  System.out.print(check.getCardName());
				}System.out.println();

		}
	}
	}

	//	  public static void gameOnce() {
	//		  System.out.println();
	//
	//		  gameTurn++;
	//		  if(gameTurn==9) {
	//			  System.out.print("【ラストターン】");
	//		  }else {
	//		  System.out.print("【"+gameTurn+"ターン目"+"】");
	//		   }
	//		  System.out.println(" ・現在のスタック:"+Board.stackPoint);
	//
	//			Player.handShow();
	//		    Board.playerPut();
	//		    Board.cpuPut();
	//
	//			Board.showBoard();
	//
	//			Board.checkBoard();
	//	  }
	//	  //結果発表
	//	  public static void judgeGame() {
	//		  List<Player>judge = Arrays.asList(Player.player_list);
	//		  System.out.println();
	//		  System.out.println("【ゲーム終了】");
	//		  System.out.println();
	//		  System.out.println("★結果発表★");
	//		  Collections.sort(judge, new MyComp02());
	//		  for(Player c : judge) {
	//			  System.out.println(c.getPlayerName() + ", " + c.getPlayerPoint());
	//		  }
	//	  }

}