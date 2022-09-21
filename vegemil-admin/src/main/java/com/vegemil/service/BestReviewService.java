package com.vegemil.service;

import java.util.List;

import com.vegemil.domain.BestReviewDTO;

public interface BestReviewService {
	
	public boolean registerBestReview(BestReviewDTO params);

	public BestReviewDTO getBestReviewDetail(Long idx);

	public boolean deleteBestReview(Long idx);

	public List<BestReviewDTO> getBestReviewList(BestReviewDTO params);
	
}
