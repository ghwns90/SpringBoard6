package com.board.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.users.domain.UserDTO;
import com.board.users.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private  UserMapper  userMapper;
	
	// /Users/List
	@RequestMapping("/List")
	public  String  list( Model   model ) {
		
		List<UserDTO> userList =  userMapper.getUserList();
		System.out.println( "1:userList:" + userList );
				
		model.addAttribute("userList", userList);
		
		return "users/list";
	}

	// /Users/WriteForm
	@RequestMapping("/WriteForm")
	public  String  writerForm() {		
		return "users/write";
	}
	
	// /Users/Write
	@RequestMapping("/Write")
	public  String  write( UserDTO userDTO ) {
		
		System.out.println("2:" +  userDTO );
		
		userMapper.insertUser( userDTO );
		
		return "redirect:/Users/List";
	}
	
	// http://localhost:9090/Users/Delete?userid=sky
	@RequestMapping("/Delete")
	public  String   delete( UserDTO  userDTO  ) {
		
		System.out.println( userDTO );
		
		userMapper.deleteUser( userDTO );
		
		return "redirect:/Users/List";
	}
	
	// http://localhost:9090/Users/UpdateForm?userid=skysky3
	@RequestMapping("/UpdateForm")
	public  String   updateForm( UserDTO  userDTO , Model model ) {
		// 넘어온 값 출력
		System.out.println("3:" + userDTO );
		
		// 수정할 정보를 조회한다
		UserDTO  user  =   userMapper.getUser( userDTO  );
		System.out.println("4:user:" + user);
		
		// 조회한 정보를 모델에 담는다
	    model.addAttribute("user", user);		
		
		return "users/update";
	}
	
	// http://localhost:9090/Users/Update
	@RequestMapping("/Update")
	public  String   update( UserDTO userDTO ) {
		// 수정하기 위해 넘어온값 출력
		System.out.println( userDTO  );
		
		// 수정하기
		userMapper.updateUser( userDTO );
		
		return "redirect:/Users/List";
	}
	
	// 로그인
	// /Users/LoginForm
	// GetMapping 으로 처리 : 로그인 페이지로 이동
	// Interceptor 가 중간에 가로채기 때문에?? postMapping 사용 안됨
	// -- response.sendRedirect() 는 Get 방식 호출
	@GetMapping("/LoginForm")
	public String loginForm(String uri, String menu_id, String nowpage, Model model) {
		
		model.addAttribute("uri", uri);
		model.addAttribute("menu_id", menu_id);
		model.addAttribute("nowpage", nowpage);
		
		return "users/login";
		
	}
	// /Users/Login <- <form>으로 날라옴
	@PostMapping("/Login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		// 넘어온 로그인정보 처리
		String userid  = request.getParameter("userid");
		String passwd  = request.getParameter("passwd");
		String uri     = request.getParameter("uri");
		String menu_id = request.getParameter("menu_id");
		String nowpage = request.getParameter("nowpage");
		
		//db 조회
		UserDTO user = userMapper.login(userid, passwd);
		System.out.println(user);
		
		// 다른 페이지에서 볼수 있도록 session에 저장
		HttpSession session = request.getSession();
		session.setAttribute("login", user);
		
		return "redirect:/" + uri + "/List"
				+ "?menu_id =" + menu_id
				+ "&nowpage =" + nowpage;
	}
	
	// /Users/Logout
	// == @GetMapping 과 같다
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		session.invalidate(); // session 을 초기화
		
		//Object url = session.getAttribute("URL");
		//return "redirect:" + (String url);
		return "redirect:/";
	}
	
	
}



















