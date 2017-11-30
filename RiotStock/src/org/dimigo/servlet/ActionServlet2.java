package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.action.IAction;
import org.dimigo.util.ActionMapper;
import org.dimigo.util.Message;
import org.dimigo.vo.ActionVO;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("*.do2")
public class ActionServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) {
		ActionVO vo = null;
		try {
			request.setCharacterEncoding("utf-8");
			
			// /WebClass/login.do인 경우 login.do만 추출
			String uri = request.getRequestURI();
			String action = uri.substring(uri.lastIndexOf("/") + 1);
			action = action.substring(0, action.indexOf("."));
			System.out.println("action : " + action);
			
			// action_mapper.xml에 정의된 Action 정보 가져오기
			ActionMapper am = ActionMapper.getInstance();
			vo = am.getAction(action);
			System.out.println("actionVO : " + vo);
			
			if(vo.getClassName() != null) {
				// Reflection api로 객체 동적 생성
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		       
		        Class<?> classInstance = classLoader.loadClass(vo.getClassName());
		        IAction instance = (IAction) classInstance.newInstance();
		        
		        // Action의 execute() 실행
		        instance.execute(request, response);
			}
		        
	        // 정상 페이지 forwarding
			if(vo.getSuccess() != null) {
		        RequestDispatcher rd = request.getRequestDispatcher(vo.getSuccess());
			    rd.forward(request, response);
			}
		    
		} catch(Exception e) {
			try {
				e.printStackTrace();
				request.setAttribute("error_code", e.getMessage());
				request.setAttribute("error_msg", Message.getMessage(e.getMessage()));
				
				// 오류 페이지 forwarding
				RequestDispatcher rd = null;
				if(vo != null && vo.getError() != null) {
					rd = request.getRequestDispatcher(vo.getError());
					rd.forward(request, response);
				} else {
					printErr(request, response, e);
				}				
		    } catch (Exception e1) {
				e1.printStackTrace();
				printErr(request, response, e1);
			}
		}
    }
	
	protected void printErr(HttpServletRequest request, HttpServletResponse response, Exception e) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<html><head><title>Error</title></head><body><h3>");
	        //e.printStackTrace(out);
			out.println(e.getMessage());
	        out.println("</h3></body></html>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}        
	}
	
}
