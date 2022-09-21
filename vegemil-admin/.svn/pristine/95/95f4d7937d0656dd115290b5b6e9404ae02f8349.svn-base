package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.FaqDTO;
import com.vegemil.mapper.FaqMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class FaqServiceImpl implements FaqService {

	@Autowired
	private FaqMapper faqMapper;

	@Override
	public boolean registerFaq(FaqDTO params) {
		int queryResult = 0;

		if (params.getFIdx() == null) {
			queryResult = faqMapper.insertFaq(params);
		} else {
			queryResult = faqMapper.updateFaq(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public FaqDTO getFaqDetail(Long idx) {
		return faqMapper.selectFaqDetail(idx);
	}

	@Override
	public boolean deleteFaq(Long idx) {
		int queryResult = 0;

		FaqDTO faq = faqMapper.selectFaqDetail(idx);

		if (faq != null && "N".equals(faq.getDeleteYn())) {
			queryResult = faqMapper.deleteFaq(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<FaqDTO> getFaqList(FaqDTO params) {
		List<FaqDTO> faqList = Collections.emptyList();

		int faqTotalCount = faqMapper.selectFaqTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(faqTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (faqTotalCount > 0) {
			faqList = faqMapper.selectFaqList(params);
		}

		return faqList;
	}

}
