package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserInfoDAO;
import DTO.UserInfoBeans;

/**
 * Servlet implementation class UserDeleteServlet2
 */
@WebServlet("/Servlet/UserDelete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("UTF-8");	// ※ 追加行（文字化けを防ぐ）

		// DTOの生成
		UserInfoBeans dto = new UserInfoBeans();

		// セッション取得
		HttpSession session = request.getSession();

		// リクエストパラメータ取得
		String param = request.getParameter("param");
		String token = request.getParameter("token");

		/**
		 * userinput.jspで表示された内容を、最終確認画面へフォワードする
		 *
		 */
		if( param == null ) {

			// userinput.jspの表示内容をリクエストパラメータから受け取り、DTOに格納する
			try {
				String userid = request.getParameter("userid");
				int roleid = Integer.parseInt(request.getParameter("roleid"));

				dto.setUserid(userid);
				dto.setRoleid(roleid);

			} catch (Exception e) {
				throw new ServletException(e);
			}

			// セッションスコープに処理方法と入力内容を格納
			session.setAttribute("token", token);	//処理方法
			session.setAttribute("dto", dto);		//入力データ

			// 最終確認画面にフォワードする
			RequestDispatcher rd = request.getRequestDispatcher("/userconfirm.jsp");
			rd.forward(request, response);

		}


		/**
		 * 最終確認画面で「実行」を指示した場合（～UserDelete?@param=execで呼ばれた場合）
		 *   削除を実行し、呼び元のadmin/memberlist.jsp、index.jspのいずれかに戻る
		 *   自分自身を削除した場合は、ログアウトしてindex.jspに戻る
		 *
		 *  @param param=exec  userconfirm.jspからリクエストパラメータで渡される
		 *
		 */
		if( param.equals("exec") ) {

			// セッションスコープから削除対象のユーザ情報と現在のログインユーザ情報を取得
			dto = (UserInfoBeans)session.getAttribute("dto");
			UserInfoBeans loginUser = (UserInfoBeans)session.getAttribute("loginUser");

			// メッセージ格納用
			String message = "";

			try(UserInfoDAO dao = new UserInfoDAO()) {
				// 削除処理を実行
				dao.userDelete(dto.getUserid());

				message = "削除処理が完了しました。";
				setMessage(request, message);

			} catch (Exception e) {
				throw new ServletException(e);
			}


			// 自分自身を削除した場合、ログアウトしてindex.jspへ戻る
			if( dto.getUserid().equals(loginUser.getUserid() ) ) {
				// セッションスコープの情報を破棄
				session.invalidate();

				message += "ログアウトしました。";
				request.setAttribute("message", message);

				// フォワード実行
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);

			}


			// セッションスコープ内の不要インスタンスを削除
			session.removeAttribute("dto");
			session.removeAttribute("token");

			// 呼び元のパスをセッションスコープから取得し、admin/memberlist.jspもしくはindex.jspに戻る
			String returnPath = (String)session.getAttribute("returnPath");

			RequestDispatcher rd = request.getRequestDispatcher(returnPath);
			rd.forward(request, response);

		}

	}


	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}


}
