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
import entities.Book;
import entities.Transaction;
import entities.User;

/**
 * Servlet implementation class AddTrans
 */
@WebServlet(urlPatterns={"/addtrans"}, name="AddTransServlet")
public class AddTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTrans() {
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
			List <Transaction> trans = ((AppSettings)session.getAttribute("appSettings")).getTrans();
			List <Book> allbooks = ((AppSettings)session.getAttribute("appSettings")).getBooks();
			List <Book> delBooks = new ArrayList<Book>();
			List <Book> avBooks = new ArrayList<Book>();
			for(int i=0; i<trans.size();i++){
				if(trans.get(i).getDateOfReturn() == null){
					delBooks.add(trans.get(i).getBook());
				}
			}
			for(int i = 0;i<allbooks.size();i++){
				if(delBooks.contains((Book)allbooks.get(i)) == false){
					avBooks.add(allbooks.get(i));
				}
			}
			
			request.setAttribute("books", avBooks);
			request.getRequestDispatcher("newtrans.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AppSettings appSettings = (AppSettings) session.getAttribute("appSettings");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		
		Transaction trans = new Transaction(	appSettings.getUserById(Integer.parseInt(request.getParameter("user"))),
												appSettings.getBookById(Integer.parseInt(request.getParameter("book"))),
												LocalDate.parse(request.getParameter("dateOfBorrow"), format),
												LocalDate.parse(request.getParameter("expectedDateOfReturn"), format),
												Transaction.nextId);
		appSettings.getTrans().add(trans);
		response.sendRedirect("addtrans");
				
	}

}
