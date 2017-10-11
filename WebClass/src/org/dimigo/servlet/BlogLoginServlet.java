package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimigo.vo.UserVO;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/bloglogin")

public class BlogLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public BlogLoginServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      RequestDispatcher rd = request.getRequestDispatcher("myblog/login.jsp");
      rd.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();

      request.setCharacterEncoding("utf-8");
      String email = request.getParameter("email");
      String pwd = request.getParameter("pwd");
      boolean result = email.equals("test@naver.com");
      
      Gson gson = new Gson();
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("email", email);
      map.put("pwd", pwd);
      map.put("result", result);
      System.out.printf("id : %s, pwd : %s\n", email, pwd);
      System.out.println(gson.toJson(map));
      out.write(gson.toJson(map));

      if (result) {
         // 세션에 사용자 생성
         HttpSession session = request.getSession();
         UserVO user = new UserVO();
         user.setId(email);
         user.setName("홍길동");
         user.setNickname("의적");
         
         session.setAttribute("user", user);
      }
     
   }

   protected void doPost2(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.setContentType("application/json;charset=utf-8");
      PrintWriter out = response.getWriter();

      String email = request.getParameter("email");
      String pwd = request.getParameter("pwd");

      JsonObject jo = new JsonObject();

      jo.addProperty("email", email);
      jo.addProperty("pwd", pwd);

      out.write(jo.toString());
      System.out.println(jo.toString());

      out.close();
   }

}