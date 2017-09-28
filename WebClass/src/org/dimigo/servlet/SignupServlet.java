package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/signup.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
	    String id = request.getParameter("id");
	    String pwd = request.getParameter("pwd");
	    String name = request.getParameter("name");
	    String nickname = request.getParameter("nickname");
	    System.out.printf("id : %s, pwd : %s", id, pwd);
	    System.out.printf("name : %s, nickname : %s", name, nickname);
	    boolean result = false; //(!id.equals("") && !pwd.equals("") && !name.equals("") && !nickname.equals(""));
	    if(result) {
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
	        rd.forward(request, response);
	    }
	    else {
	    	request.setAttribute("msg", "error");
	    	request.setAttribute("id", id);
	    	request.setAttribute("pwd", pwd);
	    	request.setAttribute("name", name);
	    	request.setAttribute("nickname", nickname);
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp/signup.jsp");
	        rd.forward(request, response);
	    }
	}

}
