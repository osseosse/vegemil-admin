package com.vegemil.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vegemil.adapter.GsonLocalDateTimeAdapter;
import com.vegemil.constant.Method;
import com.vegemil.domain.CalendarModelDTO;
import com.vegemil.domain.RecruitDTO;
import com.vegemil.service.AdminRecruitService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminRecruitController extends UiUtils {
	@Autowired
	private AdminRecruitService adminRecruitService;
	
	@RequestMapping(value = "/admin/recruit/{viewName}")
    public String adminRecruit(@PathVariable(value = "viewName", required = false) String viewName, HttpServletRequest req, Model model)throws Exception{
		return "admin/recruit/"+viewName;
    }
	
	@RequestMapping(value = "/admin/recruit/recruitIndex")
    public String adminRecruitIndex(@RequestParam(value = "sTh", required = false) String sTh, HttpServletRequest req, Model model)throws Exception{
		List<RecruitDTO> recruitList = adminRecruitService.getRecruitList();
		
		if(sTh == null) sTh = recruitList.get(0).getSTh();
		
		model.addAttribute("sTh", sTh);
		model.addAttribute("recruitList", recruitList);
		model.addAttribute("volunteerdata", adminRecruitService.getVolunteerData(sTh));
		model.addAttribute("dateapplydata", adminRecruitService.getDateApplyData(sTh));
		model.addAttribute("fielddata", adminRecruitService.getFieldData(sTh));
		
		return "admin/recruit/recruitIndex";
    }
	/*
	@GetMapping(value = "/admin/recruit/recruitIndex/recruitData")
	public @ResponseBody JsonObject getRecruitData(@ModelAttribute("params") RecruitDTO params, Model model) {
	
		JsonObject jsonObj = new JsonObject();

		List<RecruitDTO> recruitData = adminRecruitService.getRecruitData(params);
		if (CollectionUtils.isEmpty(recruitData) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(recruitData).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}
		
		return jsonObj;
	}
	*/
}
