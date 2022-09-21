package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.CalendarModelDTO;
import com.vegemil.domain.WebzineDTO;

@Mapper
public interface AdminEventMapper {
	
	/* 아기달력모델 2021-08-04 */
	public int insertCalendarModel(CalendarModelDTO params);

	public CalendarModelDTO selectCalendarModelDetail(Long idx);

	public int updateCalendarModel(CalendarModelDTO params);

	public int deleteCalendarModel(Long idx);

	public List<CalendarModelDTO> selectCalendarModelList(CalendarModelDTO params);

	public int selectCalendarModelTotalCount(CalendarModelDTO params);
	/* 아기달력모델 */
	
	public List<WebzineDTO> selectWebzineEventList(WebzineDTO params);

	public int selectWebzineEventTotalCount(WebzineDTO params);
	
	public int selectTemperature();
	
	public boolean updateTemperature(int temperature);
}
