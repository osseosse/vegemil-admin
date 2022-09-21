package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.BabyDTO;
import com.vegemil.mapper.AdminBabyMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminBabyServiceImpl implements AdminBabyService {

	@Autowired
	private AdminBabyMapper adminBabyMapper;
	
	@Override
	public List<BabyDTO> getBabyInfoList(BabyDTO params) {
		List<BabyDTO> babyInfoList = Collections.emptyList();

		int babyInfoTotalCount = adminBabyMapper.selectBabyInfoTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(babyInfoTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (babyInfoTotalCount > 0) {
			babyInfoList = adminBabyMapper.selectBabyInfoList(params);
		}

		return babyInfoList;
	}
	
	@Override
	public boolean registerBabyInfo(BabyDTO params) {
		int queryResult = 0;

		if (params.getMbsIdx() == null) {
			queryResult = adminBabyMapper.insertBabyInfo(params);
		} else {
			queryResult = adminBabyMapper.updateBabyInfo(params);
		}

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public BabyDTO getBabyInfoDetail(Long mbsIdx) {
		return adminBabyMapper.selectBabyInfoDetail(mbsIdx);
	}
	
/*
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;

		BoardDTO board = boardMapper.selectBoardDetail(idx);

		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		List<BoardDTO> boardList = Collections.emptyList();

		int boardTotalCount = boardMapper.selectBoardTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(boardTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList(params);
		}

		return boardList;
	}
*/
	@Override
	public List<BabyDTO> getBabyQnaList(BabyDTO params) {
		List<BabyDTO> babyQnaList = Collections.emptyList();

		int babyQnaTotalCount = adminBabyMapper.selectBabyQnaTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(babyQnaTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (babyQnaTotalCount > 0) {
			babyQnaList = adminBabyMapper.selectBabyQnaList(params);
		}

		return babyQnaList;
	}
	
	@Override
	public boolean registerBabyQna(BabyDTO params) {
		int queryResult = 0;

		if (params.getMbsIdx() == null) {
			queryResult = adminBabyMapper.insertBabyQna(params);
		} else {
			queryResult = adminBabyMapper.updateBabyQna(params);
		}

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public BabyDTO getBabyQnaDetail(Long mbsIdx) {
		return adminBabyMapper.selectBabyQnaDetail(mbsIdx);
	}
}
