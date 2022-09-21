package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.PublicDTO;

@Mapper
public interface AdminPublicMapper {
	
	public List<PublicDTO> selectTvCfList(PublicDTO params);
	
	public int selectTvCfTotalCount(PublicDTO params);
	
	public int updateOnair(PublicDTO params);
	
	public int insertTvCf(PublicDTO params);
	
	public List<PublicDTO> selectNewsList(PublicDTO params);
	
	public int selectNewsListTotalCount(PublicDTO params);
	
	public int updateNewsDisplay(PublicDTO params);
	
	public int insertNewsList(PublicDTO params);
	
	public PublicDTO selectNewsListDetail(Long mIdx);
	/*
	public int insertBabyInfo(BabyDTO params);
	
	public BabyDTO selectBabyInfoDetail(Long mbsIdx);
	
	public int updateBabyInfo(BabyDTO params);
	
	/*
	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long idx);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	public List<BoardDTO> selectBoardList(BoardDTO params);

	public int selectBoardTotalCount(BoardDTO params);
	*/
}
