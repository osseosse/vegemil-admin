package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.BabyEventDTO;
import com.vegemil.mapper.BabyEventMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class BabyEventServiceImpl implements BabyEventService {

	@Autowired
	private BabyEventMapper babyEventMapper;

	@Override
	public boolean registerBabyEvent(BabyEventDTO params) {
		int queryResult = 0;

		if (params.getSIdx() == null) {
			queryResult = babyEventMapper.insertBabyEvent(params);
		} else {
			queryResult = babyEventMapper.updateBabyEvent(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BabyEventDTO getBabyEventDetail(Long idx) {
		return babyEventMapper.selectBabyEventDetail(idx);
	}

	@Override
	public boolean deleteBabyEvent(Long idx) {
		int queryResult = 0;

		BabyEventDTO BabyEvent = babyEventMapper.selectBabyEventDetail(idx);

		if (BabyEvent != null && "N".equals(BabyEvent.getDeleteYn())) {
			queryResult = babyEventMapper.deleteBabyEvent(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BabyEventDTO> getBabyEventList(BabyEventDTO params) {
		List<BabyEventDTO> babyEventList = Collections.emptyList();

		int babyEventTotalCount = babyEventMapper.selectBabyEventTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(babyEventTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (babyEventTotalCount > 0) {
			babyEventList = babyEventMapper.selectBabyEventList(params);
		}

		return babyEventList;
	}

}
