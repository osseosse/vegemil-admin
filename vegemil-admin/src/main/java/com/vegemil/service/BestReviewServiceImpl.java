package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.BestReviewDTO;
import com.vegemil.mapper.BestReviewMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class BestReviewServiceImpl implements BestReviewService {

	@Autowired
	private BestReviewMapper bestReviewMapper;

	/* 아기달력모델 2021-08-04 */
	@Override
	public boolean registerBestReview(BestReviewDTO params) {
		int queryResult = 0;

		if (params.getSIdx() == null) {
			queryResult = bestReviewMapper.insertBestReview(params);
		} else {
			queryResult = bestReviewMapper.updateBestReview(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BestReviewDTO getBestReviewDetail(Long idx) {
		return bestReviewMapper.selectBestReviewDetail(idx);
	}

	@Override
	public boolean deleteBestReview(Long idx) {
		int queryResult = 0;

		BestReviewDTO BestReview = bestReviewMapper.selectBestReviewDetail(idx);

		if (BestReview != null && "N".equals(BestReview.getDeleteYn())) {
			queryResult = bestReviewMapper.deleteBestReview(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BestReviewDTO> getBestReviewList(BestReviewDTO params) {
		List<BestReviewDTO> BestReviewList = Collections.emptyList();

		int BestReviewTotalCount = bestReviewMapper.selectBestReviewTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(BestReviewTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (BestReviewTotalCount > 0) {
			BestReviewList = bestReviewMapper.selectBestReviewList(params);
		}

		return BestReviewList;
	}
	/* 아기달력모델 */

}
