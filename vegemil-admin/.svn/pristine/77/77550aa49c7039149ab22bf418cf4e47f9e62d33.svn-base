package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.BabyDTO;

@Mapper
public interface AdminBabyMapper {
	
	public List<BabyDTO> selectBabyInfoList(BabyDTO params);
	
	public int selectBabyInfoTotalCount(BabyDTO params);
	
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
	
	public List<BabyDTO> selectBabyQnaList(BabyDTO params);
	
	public int selectBabyQnaTotalCount(BabyDTO params);
	
	public int insertBabyQna(BabyDTO params);
	
	public BabyDTO selectBabyQnaDetail(Long mbsIdx);
	
	public int updateBabyQna(BabyDTO params);
}
