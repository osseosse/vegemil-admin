package com.vegemil.controller;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vegemil.adapter.GsonLocalDateTimeAdapter;
import com.vegemil.constant.Method;
import com.vegemil.domain.BabyEventDTO;
import com.vegemil.domain.BestReviewDTO;
import com.vegemil.domain.CalendarModelDTO;

import com.vegemil.domain.PublicDTO;
import com.vegemil.domain.WebzineDTO;

import com.vegemil.domain.SampleBabyDTO;

import com.vegemil.service.AdminEventService;
import com.vegemil.service.BabyEventService;
import com.vegemil.service.BestReviewService;
import com.vegemil.service.SampleBabyService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminEventController extends UiUtils {

	@Autowired
	private AdminEventService adminEventService;
	
	@Autowired
	private BestReviewService bestReviewService;
	
	@Autowired
	private SampleBabyService sampleBabyService;
	
	@Autowired
	private BabyEventService babyEventService;
	
	@RequestMapping(value = "/admin/event/{viewName}")
    public String adminMoveEvent(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		return "admin/event/"+viewName;
    }
	
	@GetMapping(value = "/admin/event/calendarmodel/table")
	 public @ResponseBody JsonObject getCalenModelList(@ModelAttribute("params") CalendarModelDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CalendarModelDTO> calendarModelList = adminEventService.getCalendarModelList(params);
		if (CollectionUtils.isEmpty(calendarModelList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(calendarModelList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/event/calendarmodel/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateCalenModelList(@ModelAttribute("params") final CalendarModelDTO params, Model model) {
		try {
			boolean isUpdate = adminEventService.registerCalendarModel(params);
			if (isUpdate== false) {
				return showMessageWithRedirect("순위 등록에 실패하였습니다.", "/admin/event/calendarModel", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/event/calendarModel", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/event/calendarModel", Method.GET, null, model);
		}

		//return showMessageWithRedirect("순위 등록이 완료되었습니다.", "admin/event/calendarModel", Method.GET, null, model);
		return "/admin/event/calendarModel";
	}
	
	@GetMapping(value = "/admin/event/bestReview/table")
	 public @ResponseBody JsonObject getBestReviewList(@ModelAttribute("params") BestReviewDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<BestReviewDTO> bestReviewList = bestReviewService.getBestReviewList(params);
		if (CollectionUtils.isEmpty(bestReviewList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(bestReviewList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/event/bestReview/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateBestReviewList(@ModelAttribute("params") final BestReviewDTO params, Model model) {
		try {
			boolean isUpdate = bestReviewService.registerBestReview(params);
			if (isUpdate== false) {
				return showMessageWithRedirect("순위 등록에 실패하였습니다.", "/admin/event/bestReview", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/event/bestReview", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/event/bestReview", Method.GET, null, model);
		}

		return "/admin/event/calendarModel";
	}
	
	@GetMapping(value = "/admin/event/babyEvent/table")
	 public @ResponseBody JsonObject getBabyEventList(@ModelAttribute("params") BabyEventDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<BabyEventDTO> babyEventList = babyEventService.getBabyEventList(params);
		if (CollectionUtils.isEmpty(babyEventList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(babyEventList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/event/babyEvent/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateBabyEventList(@ModelAttribute("params") final BabyEventDTO params, Model model) {
		try {
			boolean isUpdate = babyEventService.registerBabyEvent(params);
			if (isUpdate== false) {
				return showMessageWithRedirect("순위 등록에 실패하였습니다.", "/admin/event/babyEvent", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/event/babyEvent", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/event/babyEvent", Method.GET, null, model);
		}

		return "/admin/event/babyEvent";
	}
	
	@GetMapping(value = "/admin/event/sampleBaby/table")
	 public @ResponseBody JsonObject getSampleBabyList(@ModelAttribute("params") SampleBabyDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<SampleBabyDTO> sampleBabyList = sampleBabyService.getSampleBabyList(params);
		if (CollectionUtils.isEmpty(sampleBabyList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(sampleBabyList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/event/sampleBaby/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateSampleBabyList(@ModelAttribute("params") final SampleBabyDTO params, Model model) {
		try {
			boolean isUpdate = sampleBabyService.registerSampleBaby(params);
			if (isUpdate== false) {
				return showMessageWithRedirect("순위 등록에 실패하였습니다.", "/admin/event/sampleBaby", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/event/sampleBaby", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/event/sampleBaby", Method.GET, null, model);
		}

		return "/admin/event/sampleBaby";
	}
	
	
	
	@GetMapping(value = "/admin/event/webzineEvent/table")
	 public @ResponseBody JsonObject getWebzineEventList(@ModelAttribute("params") WebzineDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<WebzineDTO> webzineEventList = adminEventService.getWebzineEventList(params);
		if (CollectionUtils.isEmpty(webzineEventList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(webzineEventList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@GetMapping(value = "/admin/event/thermometer")
	public String openBabyInfoList(Model model) {
		int temperature = adminEventService.getTemperature();
		model.addAttribute("temperature", temperature);

		return "/admin/event/thermometer";
	}
	
	@GetMapping(value = "/admin/event/changeTemperature")
	public String changeTemperature(@RequestParam(value = "temperature", required = false) int temperature, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		boolean changeTemperature = adminEventService.changeTemperature(temperature);
		if (changeTemperature == false) {
			out.println("<script>alert('사랑의 온도계 온도 변경에 실패하였습니다.'); window.location='/admin/event/thermometer';</script>");
			out.flush();
		}
		return "/admin/event/thermometer";
	}
}
