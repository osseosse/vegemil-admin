package com.vegemil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.vegemil.domain.FaqDTO;

@Mapper
public interface FaqMapper {

	public int insertFaq(FaqDTO params);

	public FaqDTO selectFaqDetail(Long idx);

	public int updateFaq(FaqDTO params);

	public int deleteFaq(Long idx);

	public List<FaqDTO> selectFaqList(FaqDTO params);

	public int selectFaqTotalCount(FaqDTO params);

}
