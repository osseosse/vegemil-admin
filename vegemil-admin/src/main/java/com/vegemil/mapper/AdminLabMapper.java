package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.LabDTO;

@Mapper
public interface AdminLabMapper {
	
	/* 불공정거래 신고 */
	public List<LabDTO> selectFaqList(LabDTO params);
	
	public LabDTO selectFaqDetail(Long idx);
	/*
	public int insertUnfairClaim(CsDTO params);

	public int updateUnfairClaim(CsDTO params);
	
	public int updateCheckUnfairClaim(CsDTO params);
	
	public List<CsDTO> selectUnfairClaim(CsDTO params);
	
	public int deleteUnfairClaim(Long idx);
	/* 불공정거래 신고 */
	
}
