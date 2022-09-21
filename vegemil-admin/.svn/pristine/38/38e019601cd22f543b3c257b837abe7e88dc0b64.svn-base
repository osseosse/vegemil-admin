package com.vegemil.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vegemil.adapter.GsonLocalDateTimeAdapter;
import com.vegemil.constant.Method;
import com.vegemil.domain.FaqDTO;
import com.vegemil.service.FaqService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminCustomerController extends UiUtils {
	
	@Autowired
	private FaqService faqService;

	@RequestMapping(value = "/admin/customer/{viewName}")
    public String adminMoveCustomer(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		return "admin/customer/"+viewName;
    }
	
	@GetMapping(value = "/admin/customer/faq/table")
	 public @ResponseBody JsonObject getFaqList(@ModelAttribute("params") FaqDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<FaqDTO> FaqList = faqService.getFaqList(params);
		if (CollectionUtils.isEmpty(FaqList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(FaqList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/customer/faq/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateFaqList(@ModelAttribute("params") final FaqDTO params, Model model) {
		try {
			boolean isUpdate = faqService.registerFaq(params);
			if (isUpdate== false) {
				return showMessageWithRedirect("순위 등록에 실패하였습니다.", "/admin/event/Faq", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/event/Faq", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/event/Faq", Method.GET, null, model);
		}

		//return showMessageWithRedirect("순위 등록이 완료되었습니다.", "admin/event/Faq", Method.GET, null, model);
		return "/admin/event/Faq";
	}
	
}
