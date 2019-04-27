


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("user");
		UserManager um = new UserManager(username);
		User currUser = um.getUser();
		
		HttpSession session = request.getSession();
		session.setAttribute("user", currUser); // set user bean as session attribute
		
		/*switch(currUser.getPermissions()) {
			case "admin":
				//response.sendRedirect("admin-dash.jsp");
				request.getRequestDispatcher("admin-dash.jsp").forward(request, response);
				break;
			case "instructor":
				response.sendRedirect("instructor-dash.jsp");
				break;
			case "student":
				response.sendRedirect("student-dash.jsp");
				break;
			default:
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<p id='error'>ERROR: Unknown permissions.</p>");
				out.close();
				break;
		}*/
		
		request.getRequestDispatcher("./WEB-INF/dashboard.jsp").forward(request, response);
	}

}
