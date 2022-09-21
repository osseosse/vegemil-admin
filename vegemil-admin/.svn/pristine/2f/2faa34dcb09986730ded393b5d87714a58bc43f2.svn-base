package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.BabyDTO;
import com.vegemil.domain.CsDTO;

@Mapper
public interface AdminCsMapper {
	
	/* cp뉴스브리핑 */
	public int insertCpNewsList(CsDTO params);

	public int updateCpNewsList(CsDTO params);

	public int deleteCpNewsList(Long idx);

	public List<CsDTO> selectCpNewsList(CsDTO params);
	/* cp뉴스브리핑 */
	
	/* cp Ebook 카운터 */
	public List<CsDTO> selectCpEbookCounter(CsDTO params);
	/* cp Ebook 카운터 */
	
	/* cp 교육자료 */
	public int insertCpPdsList(CsDTO params);

	public int updateCpPdsList(CsDTO params);

	public int deleteCpPdsList(Long idx);
	
	public List<CsDTO> selectCpPds(CsDTO params);
	/* cp 교육자료 */
	
	/* 불공정거래 신고 */
	public int insertUnfairClaim(CsDTO params);

	public int updateUnfairClaim(CsDTO params);
	
	public int updateCheckUnfairClaim(CsDTO params);
	
	public List<CsDTO> selectUnfairClaim(CsDTO params);
	
	public int deleteUnfairClaim(Long idx);
	
	public CsDTO selectUnfairClaimDetail(Long Idx);
	/* 불공정거래 신고 */
	
	/* 가정배달 주문 */
	public List<CsDTO> selectOrderList(CsDTO params);
	
	public List<CsDTO> selectProductList(CsDTO params);
	/* 가정배달 주문 */
	
	public List<CsDTO> selectSubscribeList(CsDTO params);
}
