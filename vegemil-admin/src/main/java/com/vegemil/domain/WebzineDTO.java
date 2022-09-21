package com.vegemil.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebzineDTO extends CommonDTO {

	private Long indexNo;

	private String custName;

	private String applyDate;

	private String hp;
	
	private String remark;
	
	private String custEmail;
	
	private String answer;

}
