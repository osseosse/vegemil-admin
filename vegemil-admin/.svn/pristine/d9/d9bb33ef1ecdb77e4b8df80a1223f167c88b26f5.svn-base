package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.LabDTO;
import com.vegemil.mapper.AdminLabMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminLabServiceImpl implements AdminLabService {

	@Autowired
	private AdminLabMapper adminLabMapper;
	
	@Override
	public List<LabDTO> getFaqList(LabDTO params) {
		List<LabDTO> faqList = Collections.emptyList();

		faqList = adminLabMapper.selectFaqList(params);
		
		return faqList;
	}
	
	@Override
	public LabDTO getFaqDetail(Long idx) {
		return adminLabMapper.selectFaqDetail(idx);
	}
	/* 불공정거래 신고 */
	/*
	@Override
	public boolean registerUnfairClaim(CsDTO params) {
		int queryResult = 0;

		if (params.getCIdx() == null) {
			queryResult = adminCsMapper.insertUnfairClaim(params);
		} else {
			queryResult = adminCsMapper.updateUnfairClaim(params);
		}

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public boolean checkUnfairClaim(CsDTO params) {
		int queryResult = 0;

		queryResult = adminCsMapper.updateCheckUnfairClaim(params);
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public boolean deleteUnfairClaim(Long idx) {
		int queryResult = 0;

		queryResult = adminCsMapper.deleteUnfairClaim(idx);

		return (queryResult == 1) ? true : false;
	}
	
	*/
	/* 불공정거래 신고 */
}
