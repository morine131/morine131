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
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/Servlet/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
	 * userinput.jspで入力された内容を、最終確認画面へフォワードする
	 * 最終確認画面から送付されたパラメータを取得し、後続の登録処理を実行する
	 *
	 * @param 	param リクエストパラメータ
	 * 			        null : userconfirm.jspを呼び出す
	 *                         渡される値：userid、token、roleid=3（利用者の新規作成時のみ）
	 * 			        exec : tokenで指定された処理を実行
	 *                         渡される値：userid、token
	 * 			token 処理種別（新規登録、更新のいずれか）
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字コード設定
		request.setCharacterEncoding("UTF-8");

		// DTOの生成
		UserInfoBeans dto = new UserInfoBeans();

		// セッション取得
		HttpSession session = request.getSession();

		// リクエストパラメータ取得
		String param = request.getParameter("param"); //doPost内での分岐（confirm表示、登録/更新の実行）
		String token = request.getParameter("token"); //新規登録/更新の分岐


	//---------------------------------------------------------------------------------

		// userinput.jspで入力された内容を、最終確認画面へフォワードする
		if( param == null ) {

			// userinput.jspの入力内容をリクエストパラメータから受け取り、DTOに格納する
			try {
				String userid = request.getParameter("userid");
				int roleid = Integer.parseInt(request.getParameter("roleid"));
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				String mail = request.getParameter("mail");
				int typeid = Integer.parseInt(request.getParameter("typeid"));
				String area = request.getParameter("area");

				dto.setUserid(userid);
				dto.setPassword(password);
				dto.setNickname(nickname);
				dto.setRoleid(roleid);
				dto.setMail(mail);
				dto.setTypeid(typeid);
				dto.setArea(area);

			} catch (Exception e) {
				throw new ServletException(e);

			}

			//★★ユーザ重複チェック(新規登録時のみ、useridの重複チェックを実施）
			if( token.equals("新規登録") ) {

				UserInfoBeans checkUser = null;
				String message = "";

				//useridの重複チェックを行う
				try( UserInfoDAO dao = new UserInfoDAO(); ) {
					// DB検索結果を取得する
					checkUser = dao.userCheck(dto);
					// ログイン情報が格納されている場合はID重複しているのでエラーメッセージを格納する
					if( checkUser.getUserid() != null ) {
						message = checkUser.getUserid() + "は、すでに存在しています。";
						dto.setUserid(null);

						request.setAttribute("message", message);
						request.setAttribute("dto", dto);
						request.setAttribute("token", token);

						//Servletにフォワードする
						RequestDispatcher rd = request.getRequestDispatcher("/userinput.jsp");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			// リクエスト/セッションスコープに処理種別と入力内容を格納
			session.setAttribute("token", token);	//処理種別
			session.setAttribute("dto", dto);		//入力データ

			// 最終確認画面にフォワードする
			RequestDispatcher rd = request.getRequestDispatcher("/userconfirm.jsp");
			rd.forward(request, response);

		}


	//---------------------------------------------------------------------------------

		// 最終確認画面からリクエストパラメータで実行が指示された場合
		if( param.equals("exec")) {

			// セッションスコープから更新情報を取得
			dto = (UserInfoBeans)session.getAttribute("dto");
			token = (String)session.getAttribute("token");

			String message = "";

			try(UserInfoDAO dao = new UserInfoDAO()){

				// 更新または登録処理を行う
				if(token.equals("新規登録")) {
					dao.userRegisterInsert(dto);
					message = "新規登録処理が完了しました。";

					// ゲストから新規ユーザ登録を行った場合（セッションにloginUserが未格納の場合）は、
					// ログインする
					if ( session.getAttribute("loginUser") == null ) {
						// セッションスコープのユーザ情報（loginUser）に、dtoを格納する
						session.setAttribute("loginUser", dto);

					}

				} else {
					dao.userRegisterUpdate(dto);
					message = dto.getUserid() + " の更新処理が完了しました。";


					// ログインユーザのニックネームが更新された場合、セッションスコープのloginUserを書き換える
					// ログインユーザ名の取得
					UserInfoBeans loginUser = (UserInfoBeans)session.getAttribute("loginUser");

					// ログインユーザ名と今回更新したログインユーザ名が等しく、
					// かつ、現在のニックネームと更新したニックネームが異なる場合
					if( loginUser.getUserid().equals(dto.getUserid() ) &&
							!(loginUser.getNickname().equals(dto.getNickname())) ) {
						// セッションスコープのユーザ情報をdtoで上書きする
						session.setAttribute("loginUser", dto);
					}

				}
					setMessage(request, message);
				} catch (Exception e) {
					throw new ServletException(e);
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
