package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.BabyEventDTO;

@Mapper
public interface BabyEventMapper {
	
	public int insertBabyEvent(BabyEventDTO params);

	public BabyEventDTO selectBabyEventDetail(Long idx);

	public int updateBabyEvent(BabyEventDTO params);

	public int deleteBabyEvent(Long idx);

	public List<BabyEventDTO> selectBabyEventList(BabyEventDTO params);

	public int selectBabyEventTotalCount(BabyEventDTO params);
	
}
