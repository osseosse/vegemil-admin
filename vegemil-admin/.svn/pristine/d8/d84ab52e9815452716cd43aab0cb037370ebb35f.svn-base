package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.CsDTO;
import com.vegemil.mapper.AdminCsMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class AdminCsServiceImpl implements AdminCsService {

	@Autowired
	private AdminCsMapper adminCsMapper;

	/* cp뉴스리스트 */
	@Override
	public boolean registerCpNewsList(CsDTO params) {
		int queryResult = 0;

		if (params.getPIdx() == null) {
			queryResult = adminCsMapper.insertCpNewsList(params);
		} else {
			queryResult = adminCsMapper.updateCpNewsList(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteCpNewsList(Long idx) {
		int queryResult = 0;

		queryResult = adminCsMapper.deleteCpNewsList(idx);

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CsDTO> getCpNewsList(CsDTO params) {
		List<CsDTO> cpNewsList = Collections.emptyList();

		cpNewsList = adminCsMapper.selectCpNewsList(params);
		
		return cpNewsList;
	}
	/* cp뉴스리스트 */
	
	/* cp Ebook 카운터 */
	@Override
	public List<CsDTO> getCpEbookCounter(CsDTO params) {
		List<CsDTO> cpNewsList = Collections.emptyList();

		cpNewsList = adminCsMapper.selectCpEbookCounter(params);

		return cpNewsList;
	}
	/* cp Ebook 카운터 */
	
	/* cp 교육자료 */
	@Override
	public boolean registerCpPdsList(CsDTO params) {
		int queryResult = 0;

		if (params.getPIdx() == null) {
			queryResult = adminCsMapper.insertCpPdsList(params);
		} else {
			queryResult = adminCsMapper.updateCpPdsList(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteCpPdsList(Long idx) {
		int queryResult = 0;

		queryResult = adminCsMapper.deleteCpPdsList(idx);

		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<CsDTO> getCpPds(CsDTO params) {
		List<CsDTO> cpPds = Collections.emptyList();

		cpPds = adminCsMapper.selectCpPds(params);

		return cpPds;
	}
	/* cp 교육자료 */
	
	/* 불공정거래 신고 */
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
	public List<CsDTO> getUnfairClaim(CsDTO params) {
		List<CsDTO> unfairClaim = Collections.emptyList();

		unfairClaim = adminCsMapper.selectUnfairClaim(params);
		
		return unfairClaim;
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
	
	@Override
	public CsDTO getUnfairClaimDetail(Long Idx) {
		return adminCsMapper.selectUnfairClaimDetail(Idx);
	}
	/* 불공정거래 신고 */
	
	/* 가정배달 주문 */
	@Override
	public List<CsDTO> getOrderList(CsDTO params) {
		List<CsDTO> orderList = Collections.emptyList();

		orderList = adminCsMapper.selectOrderList(params);
		
		return orderList;
	}
	
	@Override
	public List<CsDTO> getProductList(CsDTO params) {
		List<CsDTO> productList = Collections.emptyList();

		productList = adminCsMapper.selectProductList(params);
		
		return productList;
	}
	/* 가정배달 주문 */
	
	@Override
	public List<CsDTO> getSubscribeList(CsDTO params) {
		List<CsDTO> subscribeList = Collections.emptyList();

		subscribeList = adminCsMapper.selectSubscribeList(params);
		
		return subscribeList;
	}
}
