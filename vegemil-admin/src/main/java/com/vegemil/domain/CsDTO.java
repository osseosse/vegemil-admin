package com.vegemil.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsDTO extends CommonDTO {

	/*cp뉴스, cp교육자료*/
	/** 번호 (PK) */
	private Long pIdx;
	
	private String pCate;

	/** 제목 */
	private String pSubject;

	/** 파일명 */
	private String pFname;

	/** 작성일자 */
	private String pWritedate;
	
	/** 조회수 */
	private String pHit;
	/*cp뉴스*/
	
	
	/*cp Ebook 카운터, 불공정거래 신고*/
	private Long cIdx;
	
	private String cId;
	
	private String cName;
	
	private String cHp;
	
	private String cEmail;
	
	private String cSubject;
	
	private String cContent;
	
	private String cWritedate;
	
	private String cWritetime;
	
	private String cCheck;
	
	private String cAnswer;
	
	private String cLastdate;
	
	private Long cCount;
	/*cp Ebook 카운터*/
	
	/*가정배달 주문*/
	private Long oIdx;
	
	private String oName;
	
	private String oHp;
	
	private String oAddr;
	
	private String oProduct;
	
	private String oSize;
	
	private String oEa;
	
	private String oDay;
	
	private String oAction;
	
	private String oOrderdate;
	
	private String pTitle;
	
	private String pSize;
	
	private String pUri;
	
	private String pImage;
	/*가정배달 주문*/
	
	
	/*웹진 신청자*/
	private Long sIdx;
	
	private String sName;
	
	private String sHp;
	
	private String sMemo;
	
	private String sActive;
	
	private String sWritedate;
	
	private String sEmail;
}
