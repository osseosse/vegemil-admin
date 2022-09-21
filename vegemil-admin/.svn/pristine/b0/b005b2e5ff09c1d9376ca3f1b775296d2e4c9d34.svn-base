package com.vegemil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegemil.domain.SampleBabyDTO;
import com.vegemil.mapper.SampleBabyMapper;
import com.vegemil.paging.PaginationInfo;

@Service
public class SampleBabyServiceImpl implements SampleBabyService {

	@Autowired
	private SampleBabyMapper sampleBabyMapper;

	@Override
	public boolean registerSampleBaby(SampleBabyDTO params) {
		int queryResult = 0;

		if (params.getSIdx() == null) {
			queryResult = sampleBabyMapper.insertSampleBaby(params);
		} else {
			queryResult = sampleBabyMapper.updateSampleBaby(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public SampleBabyDTO getSampleBabyDetail(Long idx) {
		return sampleBabyMapper.selectSampleBabyDetail(idx);
	}

	@Override
	public boolean deleteSampleBaby(Long idx) {
		int queryResult = 0;

		SampleBabyDTO sampleBaby = sampleBabyMapper.selectSampleBabyDetail(idx);

		if (sampleBaby != null && "N".equals(sampleBaby.getDeleteYn())) {
			queryResult = sampleBabyMapper.deleteSampleBaby(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<SampleBabyDTO> getSampleBabyList(SampleBabyDTO params) {
		List<SampleBabyDTO> sampleBabyList = Collections.emptyList();

		int SampleBabyTotalCount = sampleBabyMapper.selectSampleBabyTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(SampleBabyTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (SampleBabyTotalCount > 0) {
			sampleBabyList = sampleBabyMapper.selectSampleBabyList(params);
		}

		return sampleBabyList;
	}

}
