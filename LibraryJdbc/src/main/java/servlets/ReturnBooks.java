package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class ReturnBooks
 */
@WebServlet(urlPatterns={"/returnbooks"}, name="ReturnBooksServlet")
public class ReturnBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBooks() {
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
			List<Transaction> trans = ((AppSettings)session.getAttribute("appSettings")).getTrans();
			List<Transaction> validTrans = new ArrayList<Transaction>();
			for(int i = 0; i<trans.size(); i++){
				if (trans.get(i).getDateOfReturn() == null){
					validTrans.add(trans.get(i));
				}
			}
			request.setAttribute("trans", validTrans);
			request.getRequestDispatcher("returnbook.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("dateOfReturn").length() >= 8 && request.getParameterValues("receive")!=null){
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
			LocalDate date = LocalDate.parse(request.getParameter("dateOfReturn"), format);
			HttpSession session = request.getSession();
			AppSettings appSettings = (AppSettings)session.getAttribute("appSettings");
			String[] ids = request.getParameterValues("receive");
			for(int i=0;i<ids.length;i++){
				System.out.println(ids[i]);
				appSettings.getTransById(Integer.parseInt(ids[i])).setDateOfReturn(date);
				
			}
			
		}
		else
			request.setAttribute("msg", "Please select the received books!");
		request.getRequestDispatcher("returnbook.jsp").forward(request, response);
		//response.sendRedirect("returnbooks");

		
			
	}

}
