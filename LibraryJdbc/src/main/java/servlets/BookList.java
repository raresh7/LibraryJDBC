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
import entities.Book;
import entities.User;

/**
 * Servlet implementation class BookList
 */
@WebServlet(urlPatterns={"/booklist"}, name="BookListServlet")
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookList() {
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
			
			List<Book> books = new ArrayList<Book>();
			books = ((AppSettings) session.getAttribute("appSettings")).getBooks();
			if(request.getParameter("delete") != null){
				books.remove(((AppSettings) session.getAttribute("appSettings")).getBookById(Integer.parseInt(request.getParameter("delete"))));
			}
			request.setAttribute("books", books);
			request.getRequestDispatcher("allbooks.jsp").forward(request, response);
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
