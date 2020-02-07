package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.UserInfoBeans;

/**
 * Servlet implementation class UserInputServlet
 */
@WebServlet("/Servlet/UserInput")
public class UserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInputServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 * 新規ユーザ登録  userinput.jspを呼び出す
	 *     呼び出し元：login.jspの「新規登録はこちら」
	 *                 adminlist.jsp/memberlist.jspの「新規登録」
	 *                 UserRegisterServlet（doPost）のユーザ重複チェックで重複を検知した場合
	 *    ---------------------------------------------------------------
	 *     渡される値：    login.jsp  -- roleid=3, token=新規登録
	 *                     adminlist  -- roleid=1, token=新規登録
	 *                     memberlist -- roleid=3, token=新規登録
	 *                     UserRegisterServlet -- dto(roleid含む), token=新規登録
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("UTF-8");

		// セッション取得とtokenのクリア
		HttpSession session = request.getSession();
		session.removeAttribute("token");

		// リクエストパラメータを取得
		String returnPath = request.getParameter("returnPath");			//Register/Delete完了後の戻り先に使用
		int roleid = Integer.parseInt(request.getParameter("roleid"));	//画面表示項目の制限で使用
		String token = request.getParameter("token");					//表示項目判定用

		// DTOの作成
		UserInfoBeans dto = new UserInfoBeans();
		dto.setUserid(null);	// 新規登録であることを判断するために userid=null としている
		dto.setRoleid(roleid);	// 呼ばれたJSPでセットされたロールIDをdtoへセット
								// adminlist : 1、memberlist : 3、index : 3

		// DTOをリクエスト属性へバインド
		request.setAttribute("dto", dto);

		// token、戻り先をセッションスコープに格納
		session.setAttribute("token", token);
		session.setAttribute("returnPath", returnPath);


		// 入力画面へフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/userinput.jsp");
		rd.forward(request, response);

	}

}
