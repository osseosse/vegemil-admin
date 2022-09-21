package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.BestReviewDTO;

@Mapper
public interface BestReviewMapper {
	
	public int insertBestReview(BestReviewDTO params);

	public BestReviewDTO selectBestReviewDetail(Long idx);

	public int updateBestReview(BestReviewDTO params);

	public int deleteBestReview(Long idx);

	public List<BestReviewDTO> selectBestReviewList(BestReviewDTO params);

	public int selectBestReviewTotalCount(BestReviewDTO params);
	
}
