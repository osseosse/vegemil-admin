package com.vegemil.controller;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vegemil.adapter.GsonLocalDateTimeAdapter;
import com.vegemil.constant.Method;
import com.vegemil.domain.CsDTO;
import com.vegemil.service.AdminCsService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminCSController extends UiUtils {

	@Autowired
	private AdminCsService adminCsService;
	
	@RequestMapping(value = "/admin/cs/{viewName}")
    public String adminMain(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		// View attribute
		return "admin/cs/"+viewName;
    }
	
	/*cp뉴스*/
	@GetMapping(value = "/admin/cs/cpNews/table")
	 public @ResponseBody JsonObject getCpNewsList(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> cpNewsList = adminCsService.getCpNewsList(params);
		if (CollectionUtils.isEmpty(cpNewsList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(cpNewsList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@PostMapping(value = "/admin/cs/cpNewsRegist")
	public String registerCpNews(@ModelAttribute("params") final CsDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/*
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String formatedNow = now.format(formatter);

			String[] extension = fileName.getOriginalFilename().split("\\.");
			String fName = formatedNow + " " + params.getPSubject() + "." + extension[1];
			*/
			String originalName = fileName.getOriginalFilename();
			String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
			Path savePath = Paths.get(request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/cpNews/" + file);	
			fileName.transferTo(savePath);
			
			params.setPFname(file);
			
			boolean isRegistered = adminCsService.registerCpNewsList(params);
			if (isRegistered == false) {
				out.println("<script>alert('CP뉴스 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/cpNews", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/cpNews", Method.GET, null, model);
		}
		
		out.println("<script>alert('CP뉴스 등록이 완료되었습니다.'); window.location='/admin/cs/cpNews';</script>");
		out.flush();

		return showMessageWithRedirect("CP뉴스 등록이 완료되었습니다.", "/admin/cs/cpNews", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/cpNewsDelete")
	public String deleteCpNews(@RequestParam(value = "pIdx", required = false) Long pIdx, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isDelete = adminCsService.deleteCpNewsList(pIdx);
			if (isDelete == false) {
				out.println("<script>alert('CP뉴스 삭제에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/cpNews", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/cpNews", Method.GET, null, model);
		}
		
		out.println("<script>alert('CP뉴스 삭제가 완료되었습니다.'); window.location='/admin/cs/cpNews';</script>");
		out.flush();

		return showMessageWithRedirect("CP뉴스 삭제가 완료되었습니다.", "/admin/cs/cpNews", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/cpNewsDownload")
	public ResponseEntity<Object> downloadCpNews(@RequestParam(value = "fileName", required = false) String fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			//String reFile = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

			String path = request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/cpNews/" + fileName;
			Path filePath = Paths.get(path);	
			
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}

	}
	/*cp뉴스*/
	
	/*cp Ebook 카운터*/
	@RequestMapping(value = "/admin/cs/cpEbookCounter/chart", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getCalendarList(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> ebookCounter = adminCsService.getCpEbookCounter(params);
		if (CollectionUtils.isEmpty(ebookCounter) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(ebookCounter).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	/*cp 교육자료*/
	@GetMapping(value = "/admin/cs/cpPds/table")
	 public @ResponseBody JsonObject getCpPds(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> cpPds = adminCsService.getCpPds(params);
		if (CollectionUtils.isEmpty(cpPds) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(cpPds).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@PostMapping(value = "/admin/cs/cpPdsRegist")
	public String registerCpPds(@ModelAttribute("params") final CsDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/*
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String formatedNow = now.format(formatter);

			String[] extension = fileName.getOriginalFilename().split("\\.");
			String fName = formatedNow + " " + params.getPSubject() + "." + extension[1];
			*/
			String originalName = fileName.getOriginalFilename();
			String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
			Path savePath = Paths.get(request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/cpPds/" + file);	
			fileName.transferTo(savePath);
			
			params.setPFname(file);
			
			boolean isRegistered = adminCsService.registerCpPdsList(params);
			if (isRegistered == false) {
				out.println("<script>alert('CP뉴스 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/cpPds", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/cpPds", Method.GET, null, model);
		}
		
		out.println("<script>alert('CP뉴스 등록이 완료되었습니다.'); window.location='/admin/cs/cpPds';</script>");
		out.flush();

		return showMessageWithRedirect("CP뉴스 등록이 완료되었습니다.", "/admin/cs/cpPds", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/cpPdsDelete")
	public String deleteCpPds(@RequestParam(value = "pIdx", required = false) Long pIdx, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isDelete = adminCsService.deleteCpPdsList(pIdx);
			if (isDelete == false) {
				out.println("<script>alert('CP뉴스 삭제에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
				return "";
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/cpPds", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/cpPds", Method.GET, null, model);
		}
		
		out.println("<script>alert('CP뉴스 삭제가 완료되었습니다.'); window.location='/admin/cs/cpPds';</script>");
		out.flush();

		return showMessageWithRedirect("CP뉴스 삭제가 완료되었습니다.", "/admin/cs/cpPds", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/cpPdsDownload")
	public ResponseEntity<Object> downloadCpPds(@RequestParam(value = "fileName", required = false) String fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			//String reFile = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

			String path = request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/cpPds/" + fileName;
			Path filePath = Paths.get(path);	
			
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}

	}
	/*cp 교육자료*/
	
	/*불공정거래 신고*/
	@RequestMapping(value = "/admin/cs/unfairClaim/table", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getUnfairClaim(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> ebookCounter = adminCsService.getUnfairClaim(params);
		if (CollectionUtils.isEmpty(ebookCounter) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(ebookCounter).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@GetMapping(value = "/admin/cs/unfairClaimDelete")
	public String deleteUnfairClaim(@RequestParam(value = "cIdx", required = false) Long cIdx, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isDelete = adminCsService.deleteUnfairClaim(cIdx);
			if (isDelete == false) {
				out.println("<script>alert('해당 문의 삭제에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
				return "";
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
		}
		
		out.println("<script>alert('해당 문의 삭제가 완료되었습니다.'); window.location='/admin/cs/unfairClaim';</script>");
		out.flush();

		return showMessageWithRedirect("해당 문의 삭제가 완료되었습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/unfairClaimDetail")
	public String openUnfairClaimDetail(@ModelAttribute("params") CsDTO params, @RequestParam(value = "cIdx", required = false) Long cIdx, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (cIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "admin/cs/unfairClaim", Method.GET, null, model);
		}
		CsDTO claimDetail = adminCsService.getUnfairClaimDetail(cIdx);
		if (claimDetail == null) {
			out.println("<script>alert('없는 게시글이거나 이미 삭제된 게시글입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "admin/cs/unfairClaim", Method.GET, null, model);
		}
		claimDetail.setCIdx(cIdx);
		model.addAttribute("claimDetail", claimDetail);

		return "admin/cs/unfairClaimDetail";
	}
	
	@PostMapping(value = "/admin/cs/unfairClaimUpdate")
	public String updateBabyInfo(@ModelAttribute("params") final CsDTO params, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			params.setCCheck("1");
			boolean isRegistered = adminCsService.registerUnfairClaim(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 수정에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
		}
		
		out.println("<script>alert('답변 등록이 완료되었습니다.'); window.location='/admin/cs/unfairClaim';</script>");
		out.flush();

		return showMessageWithRedirect("답변 등록이 완료되었습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/cs/unfairClaimUpdateCheck")
	public String updateCheckBabyInfo(@ModelAttribute("params") final CsDTO params, @RequestParam(value = "cIdx", required = false) Long cIdx, @RequestParam(value = "cCheck", required = false) String cCheck, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			params.setCIdx(cIdx);
			params.setCCheck(cCheck);
			
			boolean isRegistered = adminCsService.checkUnfairClaim(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 수정에 실패하였습니다.'); window.location='/admin/cs/unfairClaimDetail?cIdx=" + cIdx + "';</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); window.location='/admin/cs/unfairClaimDetail?cIdx=" + cIdx + "';</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); window.location='/admin/cs/unfairClaimDetail?cIdx=" + cIdx + "';</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
		}
		
		out.println("<script>alert('답변 상태 변경이 완료되었습니다.'); window.location='/admin/cs/unfairClaimDetail?cIdx=" + cIdx + "';</script>");
		out.flush();

		return showMessageWithRedirect("답변 상태 변경이 완료되었습니다.", "/admin/cs/unfairClaim", Method.GET, null, model);
	}
	/*불공정거래 신고*/
	
	/*가정배달 주문*/
	@RequestMapping(value = "/admin/cs/orderList/table", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getOrderList(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> orderList = adminCsService.getOrderList(params);
		if (CollectionUtils.isEmpty(orderList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(orderList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@RequestMapping(value = "/admin/cs/orderList/product", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getProductList(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> productList = adminCsService.getProductList(params);
		if (CollectionUtils.isEmpty(productList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(productList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	/* 가정배달 주문 */
	
	@RequestMapping(value = "/admin/cs/subscribeList/table", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody JsonObject getSubscribeList(@ModelAttribute("params") CsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<CsDTO> subscribeList = adminCsService.getSubscribeList(params);
		if (CollectionUtils.isEmpty(subscribeList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(subscribeList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}
		
		return jsonObj;
	}
}
