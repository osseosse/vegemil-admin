package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.CalendarModelDTO;
import com.vegemil.mapper.AdminEventMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	private AdminEventMapper adminEventMapper;

	/* 아기달력모델 2021-08-04 */
	@Override
	public boolean registerCalendarModel(CalendarModelDTO params) {
		int queryResult = 0;

		if (params.getCIdx() == null) {
			queryResult = adminEventMapper.insertCalendarModel(params);
		} else {
			queryResult = adminEventMapper.updateCalendarModel(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public CalendarModelDTO getCalendarModelDetail(Long idx) {
		return adminEventMapper.selectCalendarModelDetail(idx);
	}

	@Override
	public boolean deleteCalendarModel(Long idx) {
		int queryResult = 0;

		CalendarModelDTO calendarModel = adminEventMapper.selectCalendarModelDetail(idx);

		if (calendarModel != null && "N".equals(calendarModel.getDeleteYn())) {
			queryResult = adminEventMapper.deleteCalendarModel(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CalendarModelDTO> getCalendarModelList(CalendarModelDTO params) {
		List<CalendarModelDTO> calendarModelList = Collections.emptyList();

		int calendarModelTotalCount = adminEventMapper.selectCalendarModelTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(calendarModelTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (calendarModelTotalCount > 0) {
			calendarModelList = adminEventMapper.selectCalendarModelList(params);
		}

		return calendarModelList;
	}
	/* 아기달력모델 */

}
