package database;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultsServlet
 */
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[][] results;
	private ResultManager manager;
	
    /**
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() throws ClassNotFoundException, IOException {
        super();
        manager = new ResultManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userID");
		//results = manager.getResults(Integer.parseInt(user_id));
		
		PrintWriter writer = response.getWriter();
		writer.append("<html><head><title>Results for User " + user_id +"</title></head><body");
		
		writer.append("</body></html>");
	}

}
