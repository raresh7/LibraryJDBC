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
import entities.Transaction;
import entities.User;

/**
 * Servlet implementation class LentBooks
 */
@WebServlet(urlPatterns={"/lentbooks"}, name="LentBooksServlet")
public class LentBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LentBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usr;
		usr = (User)session.getAttribute("loggedUser");
		if(usr == null || usr.getIsAdmin() == false)
			response.sendRedirect("index.jsp");
		else
		{
			int i = 0;
			List <Transaction> trans = ((AppSettings) session.getAttribute("appSettings")).getTrans();
			if(request.getParameter("delete") != null){
				trans.remove(((AppSettings) session.getAttribute("appSettings")).getTransById((Integer.parseInt(request.getParameter("delete")))));
			}
			List <Transaction> validTrans = new ArrayList <Transaction>();
			while(i<trans.size()){
				if(trans.get(i).getDateOfReturn() == null)
					validTrans.add(trans.get(i));
				i++;
			}
			
			
			request.setAttribute("trans", validTrans);
			request.getRequestDispatcher("lentbooks.jsp").forward(request, response);
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
