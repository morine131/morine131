import java.util.ArrayList;

public class Tramp {

	//インスタンスフィールド
	String type;						//etc..."スペード"
	String name;						//etc..."【3】ダイヤ"
	int str;							//比較するときの強さ 最弱の【2】に0を設定 【A】に12を設定 【ジョーカー】に13を設定
	
	//スタティックフィールド
	static ArrayList<Tramp> deck;

	//コンストラクタ
	Tramp(String type,String name,int str){
		this.type = type;
		this.name = name;
		this.str = str;
		deck.add(this);
		System.out.println(this.name+" "+"をデッキに加えました");
		//System.out.println(deckCount ());
	}

	//メソッド


		//deckListを用意
	public static void deckBirth() {
		deck = new ArrayList<Tramp>();
	}

	//54枚のトランプを生成
	public static void cardBirth() {
			//繰り返し処理で書ける、気がする
			Tramp S01 = new Tramp("スペード","【 2,♤ 】",0);
			Tramp S02 = new Tramp("スペード","【 3,♤ 】",1);
			Tramp S03 = new Tramp("スペード","【 4,♤ 】",2);
			Tramp S04 = new Tramp("スペード","【 5,♤ 】",3);
			Tramp S05 = new Tramp("スペード","【 6,♤ 】",4);
			Tramp S06 = new Tramp("スペード","【 7,♤ 】",5);
			Tramp S07 = new Tramp("スペード","【 8,♤ 】",6);
			Tramp S08 = new Tramp("スペード","【 9,♤ 】",7);
			Tramp S09 = new Tramp("スペード","【10,♤ 】",8);
			Tramp S10 = new Tramp("スペード","【 J,♤ 】",9);
			Tramp S11 = new Tramp("スペード","【 Q,♤ 】",10);
			Tramp S12 = new Tramp("スペード","【 K,♤ 】",11);
			Tramp S13 = new Tramp("スペード","【 A,♤ 】",12);

			Tramp CL01 = new Tramp("クローバー","【 2,♧ 】",0);
			Tramp CL02 = new Tramp("クローバー","【 3,♧ 】",1);
			Tramp CL03 = new Tramp("クローバー","【 4,♧ 】",2);
			Tramp CL04 = new Tramp("クローバー","【 5,♧ 】",3);
			Tramp CL05 = new Tramp("クローバー","【 6,♧ 】",4);
			Tramp CL06 = new Tramp("クローバー","【 7,♧ 】",5);
			Tramp CL07 = new Tramp("クローバー","【 8,♧ 】",6);
			Tramp CL08 = new Tramp("クローバー","【 9,♧ 】",7);
			Tramp CL09 = new Tramp("クローバー","【10,♧ 】",8);
			Tramp CL10 = new Tramp("クローバー","【 J,♧ 】",9);
			Tramp CL11 = new Tramp("クローバー","【 Q,♧ 】",10);
			Tramp CL12 = new Tramp("クローバー","【 K,♧ 】",11);
			Tramp CL13 = new Tramp("クローバー","【 A,♧ 】",12);

			Tramp d01 = new Tramp("ダイヤ","【 2,♦ 】",0);
			Tramp d02 = new Tramp("ダイヤ","【 3,♦ 】",1);
			Tramp d03 = new Tramp("ダイヤ","【 4,♦ 】",2);
			Tramp d04 = new Tramp("ダイヤ","【 5,♦ 】",3);
			Tramp d05 = new Tramp("ダイヤ","【 6,♦ 】",4);
			Tramp d06 = new Tramp("ダイヤ","【 7,♦ 】",5);
			Tramp d07 = new Tramp("ダイヤ","【 8,♦ 】",6);
			Tramp d08 = new Tramp("ダイヤ","【 9,♦ 】",7);
			Tramp d09 = new Tramp("ダイヤ","【10,♦ 】",8);
			Tramp d10 = new Tramp("ダイヤ","【 J,♦ 】",9);
			Tramp d11 = new Tramp("ダイヤ","【 Q,♦ 】",10);
			Tramp d12 = new Tramp("ダイヤ","【 K,♦ 】",11);
			Tramp d13 = new Tramp("ダイヤ","【 A,♦ 】",12);

			Tramp h01 = new Tramp("ハート","【 2,♡ 】",0);
			Tramp h02 = new Tramp("ハート","【 3,♡ 】",1);
			Tramp h03 = new Tramp("ハート","【 4,♡ 】",2);
			Tramp h04 = new Tramp("ハート","【 5,♡ 】",3);
			Tramp h05 = new Tramp("ハート","【 6,♡ 】",4);
			Tramp h06 = new Tramp("ハート","【 7,♡ 】",5);
			Tramp h07 = new Tramp("ハート","【 8,♡ 】",6);
			Tramp h08 = new Tramp("ハート","【 9,♡ 】",7);
			Tramp h09 = new Tramp("ハート","【10,♡ 】",8);
			Tramp h10 = new Tramp("ハート","【 J,♡ 】",9);
			Tramp h11 = new Tramp("ハート","【 Q,♡ 】",10);
			Tramp h12 = new Tramp("ハート","【 K,♡ 】",11);
			Tramp h13 = new Tramp("ハート","【 A,♡ 】",12);

			Tramp j01 = new Tramp("ジョーカー","【JOKER】",13);
			Tramp j02 = new Tramp("ジョーカー","【JOKER】",13);


		}


	public String getCardName() {
		return this.name;
}
	public int getCardStr() {
		return this.str;
}
}
