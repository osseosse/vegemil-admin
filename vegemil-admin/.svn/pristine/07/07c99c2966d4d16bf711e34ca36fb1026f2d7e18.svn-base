package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.PublicDTO;
import com.vegemil.mapper.AdminPublicMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminPublicServiceImpl implements AdminPublicService {

	@Autowired
	private AdminPublicMapper adminPublicMapper;
	
	@Override
	public List<PublicDTO> getTvCfList(PublicDTO params) {
		List<PublicDTO> tvCfList = Collections.emptyList();

		int TvCfCount = adminPublicMapper.selectTvCfTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(TvCfCount);

		params.setPaginationInfo(paginationInfo);

		if (TvCfCount > 0) {
			tvCfList = adminPublicMapper.selectTvCfList(params);
		}

		return tvCfList;
	}
	
	@Override
	public boolean changeOnair(Long tIdx,int tOnair) {
		int queryResult = 0;
		PublicDTO params = new PublicDTO();
		
		params.setTIdx(tIdx);
		params.setTOnair(tOnair);

		queryResult = adminPublicMapper.updateOnair(params);

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public boolean registerTvCf(PublicDTO params) {
		int queryResult = 0;

		queryResult = adminPublicMapper.insertTvCf(params);

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<PublicDTO> getNewsList(PublicDTO params) {
		List<PublicDTO> newsList = Collections.emptyList();

		int newsListCount = adminPublicMapper.selectNewsListTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(newsListCount);

		params.setPaginationInfo(paginationInfo);

		if (newsListCount > 0) {
			newsList = adminPublicMapper.selectNewsList(params);
		}

		return newsList;
	}
	
	@Override
	public boolean changeNewsDisplay(Long mIdx,String display) {
		int queryResult = 0;
		PublicDTO params = new PublicDTO();
		
		params.setMIdx(mIdx);
		params.setMDisplay(display);

		queryResult = adminPublicMapper.updateNewsDisplay(params);

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public boolean registerNewsList(PublicDTO params) {
		int queryResult = 0;

		queryResult = adminPublicMapper.insertNewsList(params);

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public PublicDTO getNewsListDetail(Long mIdx) {
		return adminPublicMapper.selectNewsListDetail(mIdx);
	}
	/*
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
}
