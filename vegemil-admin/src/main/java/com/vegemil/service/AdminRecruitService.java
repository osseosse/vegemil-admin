package com.vegemil.service;

import java.util.List;
import java.util.Map;

import com.vegemil.domain.RecruitDTO;

public interface AdminRecruitService {
	
	public List<RecruitDTO> getRecruitList();
	
	public RecruitDTO getVolunteerData(String sTh);
	
	public List<RecruitDTO> getDateApplyData(String sTh);
	
	public List<RecruitDTO> getFieldData(String sTh);
}
