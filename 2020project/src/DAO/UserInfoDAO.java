
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.UserInfoBeans;

/*import job.dto.job;*/

public class UserInfoDAO extends DAO {

	//ユーザー詳細画面取得のメソッド
	public UserInfoBeans userUpdate(String userid) throws Exception{

		UserInfoBeans dto = new UserInfoBeans();

		String sql = " SELECT userid,password,nickname,mail,typeid,area,roleid FROM user_info WHERE userid = ?";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		statement.setString(1,userid);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setUserid(rs.getString("userid"));
			dto.setPassword(rs.getString("password"));
			dto.setNickname(rs.getString("nickname"));
			dto.setMail(rs.getString("mail"));
			dto.setTypeid(rs.getInt("typeid"));
			dto.setArea(rs.getString("area"));
			dto.setRoleid(rs.getInt("roleid"));

		}
		return dto;
	}


	//管理者一覧表示用のメソッド
	public List<UserInfoBeans> adminList() throws Exception {
		List<UserInfoBeans> returnList = new ArrayList<UserInfoBeans>();

		String sql = "SELECT userid,nickname,role_info.rolename as rolename FROM user_info inner join role_info  on user_info.roleid = role_info.roleid WHERE user_info.roleid < 3  " ;

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {
			UserInfoBeans dto = new UserInfoBeans();

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setUserid(rs.getString("userid"));
			dto.setNickname(rs.getString("nickname"));
			dto.setRolename(rs.getString("rolename"));

			returnList.add(dto);
		}
		return returnList;

	}


	//利用者一覧表示用のメソッド
	public List<UserInfoBeans> memberList() throws Exception {
		List<UserInfoBeans> returnList = new ArrayList<UserInfoBeans>();

		String sql = "SELECT userid,nickname,role_info.rolename as rolename FROM user_info inner join role_info  on user_info.roleid = role_info.roleid WHERE user_info.roleid = 3  " ;

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {
			UserInfoBeans dto = new UserInfoBeans();

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setUserid(rs.getString("userid"));
			dto.setNickname(rs.getString("nickname"));
			dto.setRolename(rs.getString("rolename"));

			returnList.add(dto);
		}
		return returnList;
	}


	/**
	 * ログイン処理（ログインユーザ存在確認）  1/6 市橋
	 *   指定されたユーザIDとパスワードでDB検索し、検索結果を返す
	 *
	 * @param  UserInfoBeans<loginUser>
	 * @return
	 * @throws Exception
	 */
	public UserInfoBeans loginUserCheck(UserInfoBeans loginUser) throws Exception {
		UserInfoBeans returnList = new UserInfoBeans();

		String sql = "SELECT userid, password, nickname, roleid FROM user_info "
					+ " where userid = ? AND password = ? " ;

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setString( 1, loginUser.getUserid() );
		statement.setString( 2, loginUser.getPassword() );

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をUserInfoBeansインスタンスへ格納する
		while (rs.next()) {

			// クエリー結果をVOへ格納
			returnList.setUserid(rs.getString("userid"));
			returnList.setNickname(rs.getString("nickname"));
			returnList.setRoleid(rs.getInt("roleid"));
		}
		return returnList;

	}


	/**
	 * ユーザーの重複チェック
	 */
	public UserInfoBeans userCheck(UserInfoBeans checkUser) throws Exception {

		UserInfoBeans returnList = new UserInfoBeans();

		String sql = "SELECT userid, password, nickname, roleid FROM user_info "
					+ " where userid = ?" ;

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setString( 1, checkUser.getUserid() );


		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をUserInfoBeansインスタンスへ格納する
		while (rs.next()) {

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			returnList.setUserid(rs.getString("userid"));

		}
		return returnList;

	}


	//新規登録を行うメソッド
	public int userRegisterInsert(UserInfoBeans dto) throws Exception{

		String sql = "INSERT INTO user_info (userid,password,nickname,mail,typeid,area,roleid) VALUES(?,?,?,?,?,?,?)";

		int result = 0;
		// プリペアステートメントを取得し、実行SQL を渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);

			statement.setString(1, dto.getUserid());
			statement.setString(2, dto.getPassword());
			statement.setString(3, dto.getNickname());
			statement.setString(4, dto.getMail());

			if( dto.getTypeid() == 0 ) {
				statement.setNull(5, java.sql.Types.INTEGER);
			} else {
				statement.setInt(5, dto.getTypeid());
			}

			statement.setString(6, dto.getArea());
			statement.setInt(7,dto.getRoleid());

			result = statement.executeUpdate();

			// コミットを行う
			super.commit();

		}catch (Exception e) {
			// ロールバックを行い、スローした例外は呼ぶ出し元のクラスへ渡す
			super.rollback();
			throw e;
		}
		return result;
	}



	//更新処理を行う
	public int userRegisterUpdate(UserInfoBeans dto) throws Exception{

		String sql = "UPDATE user_info SET password = ? ,nickname = ? ,mail = ?,typeid = ? ,area =? ,roleid = ? where userid =?";

		int result = 0;

		// プリペアードステートメントを取得し、実行SQLを渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			//statement.setString(1,dto.getUserid());
			statement.setString(1, dto.getPassword());
			statement.setString(2, dto.getNickname());
			statement.setString(3, dto.getMail());

			if( dto.getTypeid() == 0 ) {
				statement.setNull(4, java.sql.Types.INTEGER);
			} else {
				statement.setInt(4, dto.getTypeid());
			}

			statement.setString(5,dto.getArea());
			statement.setInt(6,dto.getRoleid());
			statement.setString(7,dto.getUserid());

			result= statement.executeUpdate();

			// コミットを行う
			super.commit();

		}catch (Exception e) {
			super.rollback();
			throw e;
		}
		return result;
	}



	//削除処理を行う。指定されたid のタスクを削除する
	public int userDelete(String userid) throws Exception{

		String sql = "DELETE FROM user_info WHERE userid = ?";

		// SQLを実行してその結果を取得し、実行SQLを渡す
		int result = 0;
		try {
			// プリペアステーメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, userid);

			result = statement.executeUpdate();

			// コミットを行う
			super.commit();

		}catch (Exception e) {
			super.rollback();
			throw e;
		}

		return result;
	}


}
