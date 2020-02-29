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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/Servlet/UserDetail")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 * ナビバー「マイアカウント管理」選択時に呼び出される
	 * 対象IDのユーザ情報をセッションスコープから取得し、userinput.jsp画面で表示する
	 * 戻り先はdoGet内での記載が必要
	 *     渡される値：なし
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("UTF-8");

		// Register/Delete完了後の戻り先を指定
		String returnPath = "/index.jsp";

		// セッション取得とtokenのクリア
		HttpSession session = request.getSession();
		session.removeAttribute("token");

		// セッションからログインユーザ情報を取得し、dtoへ格納する
		UserInfoBeans dto = (UserInfoBeans)session.getAttribute("loginUser");

    	// ログインユーザのユーザ情報を取得する
    	try(UserInfoDAO dao = new UserInfoDAO()){

			// useridから詳細を取得
			dto = dao.userUpdate(dto.getUserid());
		}catch (Exception e) {
			throw new ServletException(e);
		}

		// DTO、処理項目判定token、戻り先をセッションスコープに格納
		session.setAttribute("dto", dto);
		session.setAttribute("returnPath", returnPath);

		// 画面を返す
		RequestDispatcher rd = request.getRequestDispatcher("/userinput.jsp");
		rd.forward(request,response);

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 * adminlist.jsp/memberlist.jspの「更新/削除」を選択時に呼び出される
	 * 対象IDのユーザ情報を取得し、userinput.jsp画面で表示する
	 * 戻り先は～list.jspから取得
	 *     渡される値：adminlist  -- 一覧で選択したuserid、returnPath=/Servlet/AdminSearch
	 *				   memberlist -- 一覧で選択したuserid、returnPath=/Servlet/MemberSearch
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータを取得
    	String userid = request.getParameter("userid");			//選択したuseridを取得
		String returnPath = request.getParameter("returnPath");	//Register/Delete完了後の戻り先に使用
		// マイアカウントから遷移してきた場合は値あり
		String token = request.getParameter("token");	//★リスト遷移の場合、この段階では取得/設定できない（更新か削除か分からないため）


    	UserInfoBeans dto;

    	// 更新対象のuseridを取得する
    	try( UserInfoDAO dao = new UserInfoDAO() ) {

			// userid詳細結果を取得
			dto = dao.userUpdate(userid);

    	} catch (Exception e) {
			throw new ServletException(e);
		}


		// DTO、戻り先をセッションスコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("dto", dto);
		session.setAttribute("token", token);
		session.setAttribute("returnPath", returnPath);


		// 画面を返す
		RequestDispatcher rd = request.getRequestDispatcher("/userinput.jsp");
		rd.forward(request,response);

	}

}
