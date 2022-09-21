package com.vegemil.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.RecruitDTO;

@Mapper
public interface AdminRecruitMapper {
	public List<RecruitDTO> selectRecruitList();

	public RecruitDTO selectVolunteerData(String sTh);
	
	public List<RecruitDTO> selectDateApplyData(String sTh);
	
	public List<RecruitDTO> selectFieldData(String sTh);
}
