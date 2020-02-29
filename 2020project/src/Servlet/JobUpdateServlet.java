package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.JobInfoDAO;
import DTO.JobInfoBeans;
/**
 * Servlet implementation class JobUpdateServlet
 */
@WebServlet("/Servlet/JobUpdate")
public class JobUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータから選択したタスクidを取得する

    	request.setCharacterEncoding("UTF-8");	// ※ 追加行（文字化けを防ぐ）

		// Stringからintへ変換し、daoで処理を行う。更新対象のタスクを１件取得する。
		JobInfoBeans dto = new JobInfoBeans();
		try{
			// intへ変換※NumberFormatExceptionが発生する可能性あり。チェック対象。
			int jobid = Integer.parseInt(request.getParameter("jobid"));
			int changejobid = Integer.parseInt(request.getParameter("changejobid"));
			String company = request.getParameter("company");
			int postcode = Integer.parseInt(request.getParameter("postcode"));
			int salary1 = Integer.parseInt(request.getParameter("salary1"));
			int salary2 = Integer.parseInt(request.getParameter("salary2"));
			int empid = Integer.parseInt(request.getParameter("empid"));
			int typeid = Integer.parseInt(request.getParameter("typeid"));
			int publish = Integer.parseInt(request.getParameter("publish"));
			String inputvalidperiod = request.getParameter("inputvalidperiod");
			String workarea = request.getParameter("workarea");


			dto.setJobid(jobid);
			dto.setCompany(company);
			dto.setPostcode(postcode);
			dto.setSalary1(salary1);
			dto.setSalary2(salary2);
			dto.setEmpid(empid);
			dto.setTypeid(typeid);
			dto.setPublish(publish);
			dto.setInputvalidperiod(inputvalidperiod);
			dto.setWorkarea(workarea);
			request.setAttribute("changejobid", changejobid);

		} catch (Exception e) {
			throw new ServletException(e);
		}

		// タスク１件のvoをリクエスト属性へバインド
		request.setAttribute("dto", dto);


		//更新操作フラグをrequestに格納して
		String commandMessage = "更新";
		request.setAttribute("commandMessage",commandMessage);

		// 画面を返す
		// 検索一覧を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/jobconfirm.jsp");
		rd.forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");	// ※ 追加行（文字化けを防ぐ）

		// リクエストパラメータを受け取り、DTOに格納する準備をする
		int jobid = Integer.parseInt(request.getParameter("jobid"));
		int changejobid = Integer.parseInt(request.getParameter("changejobid"));
		String company = request.getParameter("company");
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		int salary1 = Integer.parseInt(request.getParameter("salary1"));
		int salary2 = Integer.parseInt(request.getParameter("salary2"));
		int empid = Integer.parseInt(request.getParameter("empid"));
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		int publish = Integer.parseInt(request.getParameter("publish"));
		String inputvalidperiod = request.getParameter("inputvalidperiod");
		String workarea = request.getParameter("workarea");
//		String pdfname = request.getParameter("pdfname");

		// DTOへ格納する。登録される期限（limit)はTodoクラスではinputLimitになる
		JobInfoBeans dto = new JobInfoBeans();
		dto.setJobid(jobid);
		dto.setCompany(company);
		dto.setPostcode(postcode);
		dto.setSalary1(salary1);
		dto.setSalary2(salary2);
		dto.setEmpid(empid);
		dto.setTypeid(typeid);
		dto.setPublish(publish);
		dto.setInputvalidperiod(inputvalidperiod);
		dto.setWorkarea(workarea);
//		dto.setPdfname(pdfname);


		try(JobInfoDAO dao = new JobInfoDAO()){
				dao.registerUpdate(dto,changejobid);
				String message = "更新処理が完了しました。";

				 HttpSession session = request.getSession(true);
				  session.setAttribute("message", message);
			//	setMessage(request, message);
			} catch (Exception e) {
				throw new ServletException(e);
			}

		//検索一覧画面へ
		String ServletPath =  request.getContextPath()+"/Servlet/JobSearch";
        response.sendRedirect(ServletPath);

			// 登録完了 → 一覧画面を表示する
//			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//			rd.forward(request, response);
	}

	/*
	 * JSPで表示するメッセージを設定する
	 *
	 * @param request
	 * 			サーブレットリクエスト
	 *
	 * @param message
	 * 			メッセージ文字列
	 */
	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}
}