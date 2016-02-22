package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;
import entities.Transaction;
import entities.User;


/**
 * Servlet implementation class EditTrans
 */
@WebServlet(urlPatterns={"/edittrans"}, name="EditTransServlet")
public class EditTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTrans() {
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
			Transaction trans = ((AppSettings) session.getAttribute("appSettings")).getTransById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("trans", trans);
			request.getRequestDispatcher("edittrans.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AppSettings appSettings = (AppSettings) session.getAttribute("appSettings");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		Transaction trans = appSettings.getTransById(Integer.parseInt(request.getParameter("id")));
		trans.setBook(appSettings.getBookById(Integer.parseInt(request.getParameter("book"))));
		trans.setUser(appSettings.getUserById(Integer.parseInt(request.getParameter("user"))));
		trans.setDateOfBorrow(LocalDate.parse(request.getParameter("dateOfBorrow"), format));
		trans.setExpectedDateOfReturn(LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
		response.sendRedirect("lentbooks");
		
	}

}
