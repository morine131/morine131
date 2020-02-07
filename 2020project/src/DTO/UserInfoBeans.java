

package DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserInfoBeans {

	private String userid;	 // ユーザーID

	private String password; //パスワード

	private String nickname; //ニックネーム

	private String mail;	 //メールアドレス

	private int typeid;		 //希望職種

	private String area;		 //希望地域

	private int roleid;		 //ステータス

	private String rolename; //ステータスの名称


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}




	// エラーメッセージ格納用オブジェクト型変数
	private List<String> errorMessage;

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * 入力値のチェックを実施
	 * エラーがある場合は、errorMessageにエラー内容を追記する。
	 *
	 * @return errorMessage
	 *
	 */
	public boolean valueCheck() {

		// エラーメッセージ格納用配列
		errorMessage = new ArrayList<String>();

		// 半角英数以外の入力値
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]");

		// ユーザID（ログインID）：半角英数のみ許可
		if ( userid == null || userid.isEmpty() ) {
			errorMessage.add("ログイン名が入力されていません");
		} else if ( pattern.matcher(userid).matches() ) {
			errorMessage.add("半角英数字のみを入力してください");
		}

		// パスワード：半角英数のみ許可
		if ( password == null || password.isEmpty() ) {
			errorMessage.add("パスワードが入力されていません");
		} else if ( pattern.matcher(password).matches() ) {
			errorMessage.add("半角英数字のみを入力してください");
		}

		// ニックネーム：省略
		if ( nickname == null || nickname.isEmpty() ) {
			errorMessage.add("ニックネームが入力されていません");
		}

		// メールアドレス：＠があるか（メールアドレスなしも許可したため、未入力は削除 1/10
		//if ( mail == null || mail.isEmpty() ) {
		//	errorMessage.add("メールアドレスが入力されていません");
		//} else
		if ( Objects.nonNull(mail) && mail.contains("^@") ) {
			errorMessage.add("メールアドレスが正しく入力されていません");
		}

		// 希望職種（null許可）：省略（検索キーワードに利用するだけのため）

		// 希望地域（null許可）：省略（検索キーワードに利用するだけのため）

		// ステータス（権限名）roleidは1-3まで：省略（フォームで強制プルダウン指定のため）
//		if ( roleid < 1 || 3 < roleid ) {
//			errorMessage.add("ユーザの権限が正しく入力されていません");
//		}

		return ( errorMessage.size() == 0 );

	}


}

