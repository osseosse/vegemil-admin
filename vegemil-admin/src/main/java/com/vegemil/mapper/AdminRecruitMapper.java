package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.RecruitDTO;

@Mapper
public interface AdminRecruitMapper {
	public List<RecruitDTO> selectRecruitList();
	
	public RecruitDTO selectRecruitData(String sTh);

	public RecruitDTO selectVolunteerData(String sTh);
	
	public List<RecruitDTO> selectDateApplyData(String sTh);
	
	public List<RecruitDTO> selectFieldData(String sTh);
	
	public int insertNotice(RecruitDTO recruitDTO);
	
	public int updateNotice(RecruitDTO recruitDTO);
	
	public int copyNotice(RecruitDTO recruitDTO);
	
	public List<RecruitDTO> selectVolunteerList(RecruitDTO params);
}
