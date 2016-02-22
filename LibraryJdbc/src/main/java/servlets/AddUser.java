package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;
import entities.User;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(urlPatterns={"/adduser"}, name="AddUserServlet")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user;
		user = (User)session.getAttribute("loggedUser");
		if(user.getIsAdmin()){
			response.sendRedirect("newuser.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(	request.getParameter("name"),
				request.getParameter("ssn"),
				request.getParameter("address"),
				User.nextId,
				Boolean.valueOf((request.getParameter("isAdmin").equals("true"))) );
		HttpSession session = request.getSession();
		((AppSettings) session.getAttribute("appSettings"))
		.getUsers()
		.add(user);
		response.sendRedirect("newuser.jsp");
	}

}
