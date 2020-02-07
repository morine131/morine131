// ログイン関連処理  作成者：市橋 1/6


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
 * Servlet implementation class LoginServlet
 *
 * doGet ：ログイン画面を呼び出す
 *
 * doPost：ログイン処理を実行する
 *         login.jspで入力されたuseridとpasswordに合致するレコードが存在するか、DB照合する
 *         →あり：セッションスコープにuserid、nickname、roleidを格納し、
 *                 ログインを選択した画面に戻る
 *         →なし：ログイン画面に戻り、認証エラーメッセージを表示する
 *
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 * ログイン画面を呼び出す
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストスコープから処理完了後の戻りパスを取得
		String returnPath = request.getParameter("returnPath");
		//戻りパスをセッションスコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("returnPath", returnPath);

		//ログイン画面にフォワードする
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 * ログイン処理を実行する
	 *   ログインフォームに入力された userid, password でDB検索し、
	 *   ユーザが登録されていた場合は、ユーザ情報をセッションスコープに格納する
	 *
	 * @return UserInfoBeans<loginUser>  userid, nickname, roleid
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータ受け取り時の文字フォーマット指定
		request.setCharacterEncoding("UTF-8");

		// フォワードパス格納用変数
		String forwardPath = null;

		// リクエストパラメータの取得と、DTOへの格納（ログインID、パスワード）
		UserInfoBeans dto = new UserInfoBeans();
		dto.setUserid(request.getParameter("userid"));
		dto.setPassword(request.getParameter("password"));

		try ( UserInfoDAO dao = new UserInfoDAO() ){
			// DB検索結果を取得する
			UserInfoBeans loginUser = (UserInfoBeans) dao.loginUserCheck(dto);

			// ログイン情報が格納されている場合
			if ( loginUser.getNickname() != null ) {

				// セッションスコープにログイン情報を格納（userid, nickname, roleid）
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);

				// フォワードパスを指定
				forwardPath = "/index.jsp";

			// ユーザ未登録／入力内容に不足がある場合
			} else {
				String message = "ログイン認証に失敗しました";
				request.setAttribute("message", message);
				forwardPath = "/login.jsp";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// フォワードする
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);

	}

}


