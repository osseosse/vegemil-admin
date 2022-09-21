package com.vegemil.service;

import java.util.List;

import com.vegemil.domain.BabyEventDTO;

public interface BabyEventService {
	
	public boolean registerBabyEvent(BabyEventDTO params);

	public BabyEventDTO getBabyEventDetail(Long idx);

	public boolean deleteBabyEvent(Long idx);

	public List<BabyEventDTO> getBabyEventList(BabyEventDTO params);
	
}
