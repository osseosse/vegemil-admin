package com.vegemil.service;

import java.util.List;

import com.vegemil.domain.LabDTO;

public interface AdminLabService {
	
	/* faq */
	public List<LabDTO> getFaqList(LabDTO params);
	
	public LabDTO getFaqDetail(Long idx);
	/*
	public boolean registerUnfairClaim(CsDTO params);
	
	public boolean deleteUnfairClaim(Long idx);
	
	public boolean checkUnfairClaim(CsDTO params);
	/* faq */

}
