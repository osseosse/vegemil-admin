package com.vegemil.service;

import java.util.List;

import com.vegemil.domain.CsDTO;

public interface AdminCsService {
	
	/* cp뉴스브리핑 */
	public boolean registerCpNewsList(CsDTO params);

	public boolean deleteCpNewsList(Long idx);

	public List<CsDTO> getCpNewsList(CsDTO params);
	/* cp뉴스브리핑 */
	
	/* cp Ebook 카운터 */
	public List<CsDTO> getCpEbookCounter(CsDTO params);
	/* cp Ebook 카운터 */
	
	/* cp 교육자료 */
	public boolean registerCpPdsList(CsDTO params);

	public boolean deleteCpPdsList(Long idx);
	
	public List<CsDTO> getCpPds(CsDTO params);
	/* cp 교육자료 */
	
	/* 불공정거래 신고 */
	public boolean registerUnfairClaim(CsDTO params);
	
	public boolean deleteUnfairClaim(Long idx);
	
	public boolean checkUnfairClaim(CsDTO params);
	
	public List<CsDTO> getUnfairClaim(CsDTO params);
	
	public CsDTO getUnfairClaimDetail(Long Idx);
	/* 불공정거래 신고 */
	
	/* 가정배달 주문 */
	public List<CsDTO> getOrderList(CsDTO params);
	
	public List<CsDTO> getProductList(CsDTO params);
	/* 가정배달 주문 */
	
	public List<CsDTO> getSubscribeList(CsDTO params);
}
