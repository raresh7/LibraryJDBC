package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns={"/login"}, name="LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("it worked ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		AppSettings app = (AppSettings) session.getAttribute("appSettings");
		
		if(app == null){
			app = new AppSettings();
			session.setAttribute("appSettings", app);
		}
		System.out.println(request.getParameter("user"));
		if(app.getUserByName(request.getParameter("user")) != null){
			session.setAttribute("loggedUser", app.getUserByName(request.getParameter("user")));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			System.out.println("no user");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
