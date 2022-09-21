package com.vegemil.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.RecruitDTO;
import com.vegemil.mapper.AdminRecruitMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminRecruitServiceImpl implements AdminRecruitService {

	@Autowired
	private AdminRecruitMapper adminRecruitMapper;

	@Override
	public List<RecruitDTO> getRecruitList() {
		List<RecruitDTO> recruitList = adminRecruitMapper.selectRecruitList();

		return recruitList;
	}
	
	@Override
	public RecruitDTO getVolunteerData(String sTh) {
		return adminRecruitMapper.selectVolunteerData(sTh);
	}
	
	@Override
	public List<RecruitDTO> getDateApplyData(String sTh) {

		return adminRecruitMapper.selectDateApplyData(sTh);
	}
	
	@Override
	public List<RecruitDTO> getFieldData(String sTh) {

		return adminRecruitMapper.selectFieldData(sTh);
	}
}
