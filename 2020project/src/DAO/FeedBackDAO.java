
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.FeedBackBeans;

public class FeedBackDAO extends DAO {



	//就活体験談一覧表示用のメソッド
	public List<FeedBackBeans> feedbackList(int  jobid) throws Exception {
		List<FeedBackBeans> returnList = new ArrayList<FeedBackBeans>();


		String sql = "SELECT fbid, fbcontent, feedback_list.jobid, feedback_list.userid, nickname, fblastupdate, company  FROM feedback_list  left JOIN user_info ON   user_info.userid = feedback_list.userid  left join job_info on job_info.jobid= feedback_list.jobid  WHERE  feedback_list.jobid = ? ";
//				fbid, fbcontent, jobid, userid, nickname, fblastupdate

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		statement.setInt(1,jobid);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をFeedBackBeansインスタンスへ格納する
		while (rs.next()) {
			FeedBackBeans fbdto = new FeedBackBeans();


			// クエリー結果をVOへ格納（あらかじめクエリー結果とfbdtoの変数名は一致させている）
			fbdto.setFbid(rs.getInt("fbid"));
			fbdto.setFbcontent(rs.getString("fbcontent"));
			fbdto.setJobid(rs.getInt("jobid"));
			fbdto.setUserid(rs.getString("userid"));
			fbdto.setNickname(rs.getString("nickname"));
			fbdto.setFblastupdate(rs.getTimestamp("fblastupdate"));
			fbdto.setCompany(rs.getString("company"));

			returnList.add(fbdto);

		}

		return returnList;
	}





//	  新規登録処理を行う
	/* 就活体験談IDは AutoIncrement のキー項目なので、INSERT文のSQLに含めなくても自動的に最新のIDが登録される
	 *
	 * @param fbdto 入力された就活体験談
	 * @return 追加された件数
	 * @throws Exception
     */

	public int registerInsert(FeedBackBeans fbdto) throws Exception{

		String sql = "INSERT INTO feedback_list ( fbcontent, jobid, userid, fblastupdate)  VALUES(?,?,?,now())";

		int result = 0;
		// プリペアステートメントを取得し、実行SQL を渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);
//			statement.setInt(1,fbdto.getFbid());
			statement.setString(1,fbdto.getFbcontent());
			statement.setInt(2, fbdto.getJobid());
			statement.setString(3, fbdto.getUserid());
	//		statement.setString(4, fbdto.getNickname());
	//		statement.setTimestamp(6, fbdto.getFblastupdate());

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




// 就活体験談を詳細表示する
	/*
	 * 表示する就活体験談IDを指定して、就活体験談を詳細を返す。
	 * @param id 表示対象の就活体験談ID
	 * @return
	 * @throws Exception
	 */

	public FeedBackBeans feedbackUpdate(int fbid) throws Exception{
		FeedBackBeans fbdto = new FeedBackBeans();

		String sql = " SELECT fbid, fbcontent, jobid, feedback_list.userid ,user_info.nickname  FROM feedback_list LEFT JOIN user_info ON feedback_list.userid = user_info.userid WHERE fbid = ?";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		statement.setInt(1, fbid);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をFeedBackBeansインスタンスへ格納する
		while (rs.next()) {
			// クエリー結果をVOへ格納（あらかじめクエリー結果とfbdtoの変数名は一致させている）
			fbdto.setFbid(rs.getInt("fbid"));
			fbdto.setFbcontent(rs.getString("fbcontent"));
			fbdto.setJobid(rs.getInt("jobid"));
			fbdto.setUserid(rs.getString("userid"));
			fbdto.setNickname(rs.getString("nickname"));
			// fbdto.setFblastupdate(rs.getTimestamp("fblastupdate"));

		}
		return fbdto;
	}




//	更新処理を行う
		 /* @param fbdto
		 * @return
		 * @throws Exception
         */

		public int registerUpdate(FeedBackBeans fbdto) throws Exception{
			String sql = "UPDATE feedback_list SET fbcontent = ?,fblastupdate = now() WHERE fbid = ? ";

			// プリペアードステートメントを取得し、実行SQLを渡す
			int result = 0;
			try {
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setString(1,fbdto.getFbcontent());
				statement.setInt(2, fbdto.getFbid());


				result= statement.executeUpdate();

				// コミットを行う
				super.commit();
			}catch (Exception e) {
				super.rollback();
				throw e;
			}
			return result;
		}




//		  削除処理を行う。(指定された就活体験談ID の就活体験談を削除する)
		 /*
		 * @param fbid
		 * @return 削除件数
		 * @throws Exception
		 */

		public int delete(FeedBackBeans fbdto) throws Exception{
			String sql = "DELETE FROM feedback_list WHERE fbid = ?";

			// SQLを実行してその結果を取得し、実行SQLを渡す
			int result = 0;
			try {
				// プリペアステーメントを取得し、実行SQLを渡す
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setInt(1, fbdto.getFbid());

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
