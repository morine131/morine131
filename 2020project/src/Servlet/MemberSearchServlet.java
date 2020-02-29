/*作成者：荒井、安西*/



package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserInfoDAO;
import DTO.UserInfoBeans;

/**
 *
 * 検索機能。管理者一覧を取得し、一覧結果(adminlist.jsp)へフォワードする
 *
 */
@WebServlet("/Servlet/MemberSearch")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// DAOの取得
		try(UserInfoDAO dao = new UserInfoDAO()) {
			// 管理者のリストを一覧で取得し、リクエスト属性へ格納する
			List<UserInfoBeans> list = (List<UserInfoBeans>) dao.memberList();

			request.setAttribute("memberList",list);
		}catch (Exception e) {
			throw new ServletException(e);
		}

		// 検索一覧を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/memberlist.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
