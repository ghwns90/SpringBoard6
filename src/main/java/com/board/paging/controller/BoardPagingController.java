package com.board.paging.controller;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;
import com.board.paging.domain.PageResponse;
import com.board.paging.domain.Pagination;
import com.board.paging.domain.SearchDTO;
import com.board.paging.mapper.BoardPagingMapper;

@Controller
public class BoardPagingController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	@RequestMapping("/BoardPaging/List")
	public ModelAndView list( int nowpage, MenuDTO menuDTO ) {
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 게시물 목록 조회
		// menu_id = MENU01
		// nowpage : 2
		// 줄 번호 11~20번 까지 자료를 조회
		// 해당 menu_id 의 총 자료수 구하기
		int count = boardPagingMapper.count(menuDTO); // menu_id
		System.out.println("자료수 : " + count);
		
		// page로 조회한 결과를 담아놓을 객체
		PageResponse<BoardDTO> response = null;
		if( count < 1 ) { // Menu_id 의 자료가 없다면
			
			// 생성자를 이용해서 초기화 하겠다
			response = new PageResponse<>(
					Collections.emptyList(), null
			); //Collections.emptyList() : 자료가 없는 빈 리스트를 만들어서 채운다			
		}
		
		// 페이징을 위한 초기설정
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPage(nowpage); // 현재 페이지 정보
		searchDTO.setRecordSize(2); // 페이지당 10 rows
		searchDTO.setPageSize(10);	// paging.jsp 출력할 페이지번호수
		
		// Pagination 설정
		Pagination pagination = new Pagination(count, searchDTO);
		searchDTO.setPagination(pagination);
		
		// ---------------------------------------------------------
		int offset     = searchDTO.getOffset(); // 30
		int recordSize = searchDTO.getRecordSize(); // 10
		String menu_id = menuDTO.getMenu_id();
		
		List<BoardDTO> list = boardPagingMapper.getBoardPagingList(
				menu_id, offset, recordSize
		);
		// 페이징별로 list 가져오기 확인
		System.out.println("0:" + list);
		
		response = new PageResponse<>(list, pagination);
		
		menuDTO = menuMapper.getMenu(menuDTO);
		// list.jsp 에 넘겨줄 menuDTO 가져오기 확인
		System.out.println(menuDTO);
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("response", response);
		mv.addObject("searchDTO", searchDTO);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("boardpaging/list");
		
		return mv;
		
		
	}
	
	@RequestMapping("/BoardPaging/WriteForm")
	public ModelAndView writeForm(int nowpage, String menu_id) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setMenu_id(menu_id);
		menuDTO = menuMapper.getMenu(menuDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		mv.addObject("menuDTO", menuDTO);
		mv.setViewName("boardpaging/write");
		
		
		return mv;
	}
	
	@RequestMapping("/BoardPaging/Write")
	public ModelAndView write(int nowpage, BoardDTO boardDTO) {
		
		boardPagingMapper.insertBoard(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		
		String fmt = "redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String loc = String.format(fmt, boardDTO.getMenu_id(), nowpage );
		mv.setViewName(loc);
		return mv;
	}
	
	@RequestMapping("/BoardPaging/View")
	public ModelAndView view(int idx, BoardDTO boardDTO, int nowpage) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 해당게시물의 조회수를 증가한다
		boardPagingMapper.incHit(idx);
		
		// 보여줄 게시물의 정보 조회(idx)
		BoardDTO board = boardPagingMapper.getBoardByIdx(idx);
		
		//board Content \n -> <br>
		String content = board.getContent();
		content        = content.replace("\n", "<br>");
		board.setContent(content);
		
		MenuDTO mDTO = new MenuDTO();
		mDTO.setMenu_id(boardDTO.getMenu_id());
		
		MenuDTO menuDTO = menuMapper.getMenu(mDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("board", board);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("boardpaging/view");
		return mv;
	}
	
	@RequestMapping("/BoardPaging/Delete")
	public ModelAndView delete(String idx, String menu_id, int nowpage) {
		
		// 넘어온 idx로 게시글을 삭제
		boardPagingMapper.deleteBoard(idx);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "/BoardPaging/List?menu_id={0}&nowpage={1}";
		String loc = MessageFormat.format(fmt, menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	
	@RequestMapping("/BoardPaging/UpdateForm")
	public ModelAndView updateForm(int idx, String menu_id, int nowpage) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 넘겨줄 현재 메뉴의 정보
		MenuDTO mDTO = new MenuDTO();
		mDTO.setMenu_id(menu_id);
		MenuDTO menuDTO = menuMapper.getMenu(mDTO);
		
		// 넘어온 글번호로 수정할 게시글을 조회
		BoardDTO board = boardPagingMapper.getBoardByIdx(idx);
		
		//board Content \n -> <br>
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("boardpaging/update");
		return mv;
	}
	
	
	
	@RequestMapping("/BoardPaging/Update")
	public ModelAndView update(int nowpage, BoardDTO boardDTO) {
		
		// 넘어온 정보로 idx 번의 Board data를 수정한다
		boardPagingMapper.updateBoard(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "redirect:/BoardPaging/List?menu_id={0}&nowpage={1}";
		String loc = MessageFormat.format(fmt, boardDTO.getMenu_id(), nowpage);
		mv.setViewName(loc);
		
		return mv;
		
	}
	
}
