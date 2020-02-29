
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.JobInfoBeans;

/*import job.dto.job;*/

public class JobInfoDAO extends DAO {


	//一覧表示用のメソッド
	public List<JobInfoBeans> jobList() throws Exception {
		List<JobInfoBeans> returnList = new ArrayList<JobInfoBeans>();

		String sql = "SELECT jobid,company,job_info.postcode,area_list.pref as pref,area_list.city as city,salary1,salary2,\r\n" +
				"job_info.empid,empstatus,job_info.typeid,typestatus,workarea,publish,validperiod,joblastupdate,pdfname\r\n" +
				"FROM job_info\r\n" +
				"left join area_list  on job_info.postcode = area_list.postcode " +
				"left join emp_status on job_info.empid = emp_status.empid " +
				"left join job_type on job_info.typeid = job_type.typeid ORDER BY joblastupdate desc";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {
			JobInfoBeans dto = new JobInfoBeans();

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setJobid(rs.getInt("jobid"));
			dto.setCompany(rs.getString("company"));
			//	dto.setCompany_post_code(rs.getInt("company_post_code"));
			dto.setPref(rs.getString("pref"));
			dto.setCity(rs.getString("city"));



			dto.setSalary1(rs.getInt("salary1"));
			dto.setSalary2(rs.getInt("salary2"));

			dto.setEmpstatus(rs.getString("empstatus"));
			dto.setTypestatus(rs.getString("typestatus"));

			dto.setWorkarea(rs.getString("workarea"));
			dto.setJoblastupdate(rs.getTimestamp("joblastupdate"));

			dto.setPdfname(rs.getString("pdfname"));


			returnList.add(dto);
		}
		return returnList;
	}

	/*	 新規登録を行うメソッド
	 *
	 */


	public int registerInsert(JobInfoBeans dto) throws Exception{

		int result = 0;
		if(dto.getInputvalidperiod()=="") {

			String sql = "INSERT INTO job_info (jobid,company,postcode,salary1,salary2,empid,typeid,publish,"
					+ "joblastupdate,workarea) VALUES(?,?,?,?,?,?,?,?,now(),?)";


			// プリペアステートメントを取得し、実行SQL を渡す
			try {
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setInt(1,dto.getJobid());
				statement.setString(2,dto.getCompany());
				statement.setInt(3, dto.getPostcode());
				statement.setInt(4, dto.getSalary1());
				statement.setInt(5, dto.getSalary2());
				statement.setInt(6, dto.getEmpid());
				statement.setInt(7, dto.getTypeid());
				statement.setInt(8, dto.getPublish());
				//			statement.setTimestamp(10, dto.getJoblastupdate());
				statement.setString(9,dto.getWorkarea());

				result = statement.executeUpdate();

				// コミットを行う
				super.commit();
			}catch (Exception e) {
				// ロールバックを行い、スローした例外は呼ぶ出し元のクラスへ渡す
				super.rollback();
				throw e;
			}

		}else {

		String sql = "INSERT INTO job_info (jobid,company,postcode,salary1,salary2,empid,typeid,publish,validperiod,"
				+ "joblastupdate,workarea) VALUES(?,?,?,?,?,?,?,?,?,now(),?)";


		// プリペアステートメントを取得し、実行SQL を渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1,dto.getJobid());
			statement.setString(2,dto.getCompany());
			statement.setInt(3, dto.getPostcode());
			statement.setInt(4, dto.getSalary1());
			statement.setInt(5, dto.getSalary2());
			statement.setInt(6, dto.getEmpid());
			statement.setInt(7, dto.getTypeid());
			statement.setInt(8, dto.getPublish());
			statement.setString(9, dto.getInputvalidperiod());
			//			statement.setTimestamp(10, dto.getJoblastupdate());
			statement.setString(10,dto.getWorkarea());

			result = statement.executeUpdate();

			// コミットを行う
			super.commit();
		}catch (Exception e) {
			// ロールバックを行い、スローした例外は呼ぶ出し元のクラスへ渡す
			super.rollback();
			throw e;
		}
		}
		return result;
	}

	/*
	 * 表示するタスクの番号を指定して、タスク詳細意を返す。
	 * @param id 表示対象のタスクID
	 * @return
	 * @throws Exception
	 */


	public JobInfoBeans jobUpdate(int jobid) throws Exception{
		JobInfoBeans dto = new JobInfoBeans();

		String sql = " SELECT jobid,company,postcode,salary1,salary2,empid,typeid,publish,validperiod,workarea FROM job_info WHERE jobid = ?";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		statement.setInt(1,jobid);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {
			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setJobid(rs.getInt("jobid"));
			dto.setCompany(rs.getString("company"));
			//	dto.setCompany_post_code(rs.getInt("company_post_code"));
			dto.setPostcode(rs.getInt("postcode"));
			/*					dto.setPref(rs.getString("pref"));
								dto.setCity(rs.getString("city"));*/


			//一旦ミニマムの給与だけ表示
			dto.setSalary1(rs.getInt("salary1"));
			dto.setSalary2(rs.getInt("salary2"));
			dto.setEmpid(rs.getInt("empid"));
			dto.setTypeid(rs.getInt("typeid"));
			dto.setPublish(rs.getInt("publish"));
			dto.setValidperiod(rs.getDate("validperiod"));
			dto.setWorkarea(rs.getString("workarea"));


		}
		return dto;
	}



	/* 更新処理を行う
	 * @param dto
	 * @return
	 * @throws Exception
	 */


	public int registerUpdate(JobInfoBeans dto,int changejobid) throws Exception{
		int result = 0;
		if(dto.getInputvalidperiod()=="") {

			String sql = "UPDATE job_info SET jobid = ?,company = ? ,postcode = ?,salary1 = ?,salary2 = ? ,"
					+ "empid = ?,typeid = ?,publish = ?, joblastupdate = now(),workarea = ? "
					+ "WHERE jobid = ? ";

			// プリペアードステートメントを取得し、実行SQLを渡す

			try {
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setInt(1,dto.getJobid());
				statement.setString(2,dto.getCompany());
				statement.setInt(3,dto.getPostcode());
				statement.setInt(4,dto.getSalary1());
				statement.setInt(5,dto.getSalary2());
				statement.setInt(6,dto.getEmpid());
				statement.setInt(7,dto.getTypeid());
				statement.setInt(8,dto.getPublish());
				statement.setString(9,dto.getWorkarea());
				statement.setInt(10,changejobid);

			result=	statement.executeUpdate();

				// コミットを行う
				super.commit();
			}catch (Exception e) {
				super.rollback();
				throw e;
			}

		}else {


		String sql = "UPDATE job_info SET jobid = ?,company = ? ,postcode = ?,salary1 = ?,salary2 = ? ,"
				+ "empid = ?,typeid = ?,publish = ?, validperiod = ?, joblastupdate = now(),workarea = ? "
				+ "WHERE jobid = ? ";

		// プリペアードステートメントを取得し、実行SQLを渡す

		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1,dto.getJobid());
			statement.setString(2,dto.getCompany());
			statement.setInt(3,dto.getPostcode());
			statement.setInt(4,dto.getSalary1());
			statement.setInt(5,dto.getSalary2());
			statement.setInt(6,dto.getEmpid());
			statement.setInt(7,dto.getTypeid());
			statement.setInt(8,dto.getPublish());
			statement.setString(9,dto.getInputvalidperiod());
			statement.setString(10,dto.getWorkarea());
			statement.setInt(11,changejobid);

		result=	statement.executeUpdate();

			// コミットを行う
			super.commit();
		}catch (Exception e) {
			super.rollback();
			throw e;
		}
		}
	return result;
	}

	public int feedbackidUpdate(JobInfoBeans dto,int changejobid) throws Exception{
		int result = 0;
			String sql = "UPDATE feedback_list SET jobid = ?  WHERE jobid = ? ";

			// プリペアードステートメントを取得し、実行SQLを渡す

			try {
				PreparedStatement statement = getPreparedStatement(sql);

				statement.setInt(1,changejobid);
				statement.setInt(2,dto.getJobid());
			result=	statement.executeUpdate();

				// コミットを行う
				super.commit();
			}catch (Exception e) {
				super.rollback();
				throw e;
			}


	return result;
	}


	public int delete(int jobid) throws Exception{
		String sql = "DELETE FROM job_info WHERE jobid = ?";

		// SQLを実行してその結果を取得し、実行SQLを渡す
		int result = 0;
		try {
			// プリペアステーメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1, jobid);;

			result = statement.executeUpdate();

			// コミットを行う
			super.commit();
		}catch (Exception e) {
			super.rollback();
			throw e;
		}
		return result;
	}

//求人検索用のメソッド and検索のみ
	public List<JobInfoBeans> jobsearch(JobInfoBeans dtoSearch ) throws Exception {
		List<JobInfoBeans> returnList = new ArrayList<JobInfoBeans>();


		 String sql = "SELECT jobid,company,job_info.postcode,area_list.pref as pref,area_list.city as city,salary1,salary2, " +
				"job_info.empid,empstatus,job_info.typeid,typestatus,workarea,publish,validperiod,joblastupdate,pdfname " +
				"FROM job_info " +
				"left join area_list  on job_info.postcode = area_list.postcode " +
				"left join emp_status on job_info.empid = emp_status.empid " +
				"left join job_type on job_info.typeid = job_type.typeid " +
				"WHERE company like ? " +
				"and (pref like ? OR pref IS NULL) " +
				"and (city like ? OR city IS NULL) " +
				"and salary1 >= ? " +
				"and salary2 >= ? " +
				"and empstatus like ? " +
				"and typestatus like ? " +
				"and workarea like ? ORDER BY joblastupdate desc " ;



		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		//sql側に％を入れると''の関係でうまく動かないので、statement.setで%を入れる
		statement.setString(1,"%"+dtoSearch.getCompany()+"%");
		statement.setString(2,"%"+dtoSearch.getPref()+"%");
		statement.setString(3,"%"+dtoSearch.getCity()+"%");
		statement.setInt(4,dtoSearch.getSalary1());
		statement.setInt(5,dtoSearch.getSalary2());
		statement.setString(6,"%"+dtoSearch.getEmpstatus()+"%");
		statement.setString(7,"%"+dtoSearch.getTypestatus()+"%");
		statement.setString(8,"%"+dtoSearch.getWorkarea()+"%");



		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をJobInfoBeansインスタンスへ格納する
		while (rs.next()) {
			JobInfoBeans dto = new JobInfoBeans();

			// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setJobid(rs.getInt("jobid"));
			dto.setCompany(rs.getString("company"));
			//	dto.setCompany_post_code(rs.getInt("company_post_code"));
			dto.setPref(rs.getString("pref"));
			dto.setCity(rs.getString("city"));



			dto.setSalary1(rs.getInt("salary1"));
			dto.setSalary2(rs.getInt("salary2"));

			dto.setEmpstatus(rs.getString("empstatus"));
			dto.setTypestatus(rs.getString("typestatus"));
			dto.setJoblastupdate(rs.getTimestamp("joblastupdate"));
			dto.setWorkarea(rs.getString("workarea"));

			dto.setPdfname(rs.getString("pdfname"));


			returnList.add(dto);
		}
		return returnList;
	}



		//PDFファイル名の登録用メソッド

		public int uploadPDF (JobInfoBeans dto) throws Exception{
			String sql = "UPDATE job_info SET pdfname = ? WHERE jobid = ?";

			int result = 0;
			// プリペアステートメントを取得し、実行SQLを渡す
			try{
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setString(1, dto.getPdfname());
				statement.setInt(2, dto.getJobid());

				// 登録を行う
			result = statement.executeUpdate();

				// コミットを行う
				super.commit();
			}catch (Exception e) {
				super.rollback();
				throw e;
			}
				return result;
		}

//		求人票番号の重複チェック 間に合わなそう　1/17外池
		public JobInfoBeans jobidCheck(int jobid) throws Exception {

			JobInfoBeans returnList = new JobInfoBeans();

			String sql = "SELECT jobid  FROM job_info "
						+ " where jobid = ?" ;

			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt( 1, jobid );


			// SQLを実行してその結果を取得する
			ResultSet rs = statement.executeQuery();

			// 検索結果の行数分フェッチを行い、取得結果をUserInfoBeansインスタンスへ格納する
			while (rs.next()) {

				// クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
				returnList.setJobid(rs.getInt("jobid"));

			}
			return returnList;

		}


}
