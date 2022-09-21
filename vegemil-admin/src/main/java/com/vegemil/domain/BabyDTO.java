package com.vegemil.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BabyDTO extends CommonDTO {

	/** 번호 (PK) */
	private Long mbsIdx;

	/** 제목 */
	private String mbsTitle;

	/** 내용 */
	private String mbsContent;

	/** 요약 */
	private String mbsSummary;
	
	/** 날짜 */
	private String mbsGetdate;
	
	/** 이미지 */
	private String mbsImage;

	/** 게시여부 */
	private int mbsActive;
	
	/** 영유아식메인 게시여부 */
	private int mbsCheck;

	/** 카테고리 */
	private String mbsCategory;
	
	private String cateSk;
	
	private String cateBo;
	
	private String cateDi;
	
	private String cateGh;

}
