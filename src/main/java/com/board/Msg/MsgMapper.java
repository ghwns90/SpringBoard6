package com.board.Msg;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MsgMapper {

	public void insertMsg(MsgDTO msgDTO);

	public List<MsgDTO> getMsgList(MsgDTO msgDTO);

	

}
