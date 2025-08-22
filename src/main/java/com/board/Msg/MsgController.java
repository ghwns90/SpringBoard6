package com.board.Msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.users.domain.UserDTO;
import com.board.users.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Msg")
public class MsgController {
	
	@Autowired
	private MsgMapper  msgMapper;

	@Autowired
	private MsgDTO msgDTO;
	
	@RequestMapping("/MsgForm")
	public String msgForm(){
		
		return "msg/msgForm";
	}
	
	// 쪽지쓰기 페이지, 보낸아이디, 받는 아이디, 내용을 받아와서 MSG DB테이블에 INSERT
	// 후에 받은쪽지함으로 이동
	@RequestMapping("/WriteMsg")
	public ModelAndView writeMsg( MsgDTO msgDTO ) {
		
		msgMapper.insertMsg(msgDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msgDTO", msgDTO);
//		mv.setViewName("redirect:/MSG/RList?receiver_id="+msgDTO.getRECEIVER_ID());
		mv.setViewName("redirect:/Msg/MsgList");
		return mv;
	}
	
	// 받은 쪽지함 - receiver_id 에 맞는 쪽지들을 리스트로 받아온다
	@RequestMapping("/Msg/MsgList")
	public ModelAndView getMsgList(MsgDTO msgDTO) {
		
		// receiver_id 로 검색해서 일치하는글들 list로 담아온다?
		List<MsgDTO> msgList = msgMapper.getMsgList(msgDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msgList",msgList);
		mv.setViewName("msg/msgList");
		
		return mv;
	}
	
}



















