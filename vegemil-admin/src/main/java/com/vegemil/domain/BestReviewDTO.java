package com.vegemil.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BestReviewDTO extends CommonDTO {

	private Long 	sIdx;	/** 번호 (PK) */
	private String 	sUrl;	/** 주소 */
	private String 	sWritedate;	/** 작성일 */
	private String 	sWritetime;	/** 작성시간 */
	private String 	sUid;	/** 사용자ID */
	private String 	sTitle;	/** 제목 */
	private String 	sEdayId;/** 이데이몰ID */
	private String 	sDesctime;	/** 업데이트시간 */
	private String 	sSns;	/** SNS */
	private String 	sImage;/** 파일명 */
	private String 	sRank;/** 개월 수 */

}
