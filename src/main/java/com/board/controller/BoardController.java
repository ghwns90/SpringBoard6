package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;

@Controller
public class BoardController {
	
	@Autowired
	private MenuMapper  menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	// http://localhost:9090/Board/List?menu_id=MENU01
	@RequestMapping("/Board/List")
	public ModelAndView list( MenuDTO menuDTO ) {
		// 메뉴 리스트
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		// 게시물 목록처리
		List<BoardDTO> boardList =  boardMapper.getBoardList( menuDTO ); 
		// System.out.println( boardList );
	
		menuDTO                  =  menuMapper.getMenu( menuDTO );
		
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("menuList",  menuList);		
		//mv.addObject("menu_id",   menu_id );		
		mv.addObject("menuDTO",   menuDTO );		
		mv.addObject("boardList", boardList );
		mv.setViewName( "board/list" );
		return  mv;
	}
	
	// http://localhost:9090/Board/WriteForm?menu_id=MENU01
	@RequestMapping("/Board/WriteForm")
	public  ModelAndView  writeForm( MenuDTO  menuDTO  ) {
		
		// 메뉴 목록을 조회
		List<MenuDTO>  menuList = menuMapper.getMenuList();
		
		menuDTO                 = menuMapper.getMenu( menuDTO );
		
		ModelAndView  mv  =  new ModelAndView();
		mv.addObject("menuList", menuList );
		mv.addObject("menuDTO",  menuDTO);
		mv.setViewName( "board/write" );
		return        mv;
		
	}
	
	@RequestMapping("/Board/Write")
	public  ModelAndView  write( BoardDTO boardDTO ) {
		
		boardMapper.insertBoard( boardDTO  );
		
		String menu_id = boardDTO.getMenu_id();
		
		ModelAndView  mv   = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return  mv;
		
	}
	
	// /Board/View
	@RequestMapping("/Board/View")
	public ModelAndView view(BoardDTO boardDTO) {
		
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		//idx에 해당하는 글번호 조회수 1 증가
		boardMapper.incHit(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		
		// view.jsp에 출력할 내용 검색
		BoardDTO dto = boardMapper.getBoard(boardDTO);
		
		// menu_id로 메뉴 조회
		String menu_id = dto.getMenu_id();
		MenuDTO menuDTO = new MenuDTO(menu_id, null, 0);
		menuDTO         = menuMapper.getMenu(menuDTO);
		
		// menuList 도 넣어줘야 include한 메뉴바가 뜬다
		mv.addObject(     "menuList", menuList );
		mv.addObject(     "menuDTO", menuDTO   );
		mv.addObject(     "boardDTO", dto      );
		mv.setViewName(   "board/view"         );
		
		return mv;
	}
	
	// DELETE
	@RequestMapping("/Board/Delete")
	public ModelAndView delete(BoardDTO boardDTO) {
		
		// BoardDTO (idx) 로 삭제
		boardMapper.deleteBoard( boardDTO );
		
		String menu_id = boardDTO.getMenu_id();
		// 삭제 후 이동
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id );
		return mv;
	
	}
	
	// UPDATEFORM
	@RequestMapping("/Board/UpdateForm")
	public ModelAndView updateForm(BoardDTO boardDTO) {
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 메뉴이름 조회
		String menu_id = boardDTO.getMenu_id();
		MenuDTO menuDTO = new MenuDTO(menu_id, null, 0);
		menuDTO         = menuMapper.getMenu(menuDTO);
		
		// 수정할 게시글 정보를 idx로 조회
		BoardDTO board = boardMapper.getBoard(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("board", board);
		mv.setViewName("board/update");
		return mv;
	}
	
	// UPDATE
	@RequestMapping("/Board/Update")
	public ModelAndView update(BoardDTO boardDTO) {
		
		// 받은 정보 수정
		boardMapper.updateBoard(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		
		String menu_id = boardDTO.getMenu_id();
		MenuDTO menuDTO = new MenuDTO(menu_id, null, 0);
		menuDTO         = menuMapper.getMenu(menuDTO);
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		return mv;
		
	}
}




