package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;
import entities.User;

/**
 * Servlet implementation class UserList
 */
@WebServlet(urlPatterns={"/userlist"}, name="UserListServlet")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
		
		if(user == null || user.getIsAdmin() == false)
			response.sendRedirect("index.jsp");
		else
		{
			List<User> users = new ArrayList<User>();
			users = ((AppSettings) session.getAttribute("appSettings")).getUsers();
			if(request.getParameter("delete") != null){
				users.remove(((AppSettings) session.getAttribute("appSettings")).getUserById(Integer.parseInt(request.getParameter("delete"))));
			}
			request.setAttribute("users", users);
			request.getRequestDispatcher("allusers.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
