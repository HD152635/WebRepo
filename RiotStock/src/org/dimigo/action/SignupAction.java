/**
 * 
 */
package org.dimigo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.UserService;
import org.dimigo.util.CommonUtil;
import org.dimigo.vo.UserVO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * <pre>
 * org.dimigo.action
 *  |_ LoginAction
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class SignupAction implements IAction {

	// 입력값 검증
	private void validate(UserVO vo) throws Exception {
		if(CommonUtil.isEmpty(vo.getId())) throw new Exception("아이디를 입력하세요");
		if(CommonUtil.isEmpty(vo.getPwd())) throw new Exception("비밀번호를 입력하세요");
		if(CommonUtil.isEmpty(vo.getName())) throw new Exception("이름을 입력하세요");
		if(CommonUtil.isEmpty(vo.getNickname())) throw new Exception("닉네임을 입력하세요");
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		
		try {
			// 입력값 추출
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname");
			
			// VO 객체에 데이터 바인딩
			UserVO user = new UserVO();
			user.setId(id);
			user.setPwd(pwd);
			user.setName(name);
			user.setNickname(nickname);
			
			// 입력값 검증
			validate(user);
			
			// 비지니스 로직 처리를 위한 Service 호출
			UserService service = new UserService();
			service.signup(user);
			
		    obj.addProperty("msg", "success");
		    
		} catch(Exception e) {
			obj.addProperty("msg", "error");
			obj.addProperty("error", e.getMessage());
		} finally {
			out.write(gson.toJson(obj));
			out.close();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
