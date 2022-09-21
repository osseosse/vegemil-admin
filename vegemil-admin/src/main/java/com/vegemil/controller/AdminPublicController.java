package com.vegemil.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vegemil.constant.Method;
import com.vegemil.domain.PublicDTO;
import com.vegemil.service.AdminPublicService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminPublicController extends UiUtils {

	@Autowired
	private AdminPublicService adminPublicService;
	
	@GetMapping(value = "/admin/public/tvCf")
	public String openBabyInfoList(@ModelAttribute("params") PublicDTO params, Model model) {
		List<PublicDTO> tvCfList = adminPublicService.getTvCfList(params);
		model.addAttribute("tvCfList", tvCfList);

		return "admin/Public/tvCf";
	}
	
	@GetMapping(value = "/admin/public/tvCfOnair")
	public void changeOnair(@RequestParam(value = "tIdx", required = false) Long tIdx, @RequestParam(value = "tOnair", required = false) int tOnair, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (tIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		boolean changeOnair = adminPublicService.changeOnair(tIdx,tOnair);
		if (changeOnair == false) {
			out.println("<script>alert('육아정보 진열 변경에 실패하였습니다.'); window.location='/admin/public/tvCf';</script>");
			out.flush();
		}
	}
	
	@PostMapping(value = "/admin/public/tvCfRegist")
	public String registerTvCf(@ModelAttribute("params") final PublicDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String originalName = fileName.getOriginalFilename();
			String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
			String uuid = UUID.randomUUID().toString();
			String savefileName = uuid + "_" + file;
			Path savePath = Paths.get(request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/tvCf/" + savefileName);
			
			fileName.transferTo(savePath);
			params.setTYoutubeImg(savefileName);
			boolean isRegistered = adminPublicService.registerTvCf(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/public/tvCf", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/public/tvCf", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 등록이 완료되었습니다.'); window.location='/admin/public/tvCf';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/admin/public/tvCf", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/public/newsList")
	public String openNewsList(@ModelAttribute("params") PublicDTO params, Model model) {
		List<PublicDTO> newsList = adminPublicService.getNewsList(params);
		model.addAttribute("newsList", newsList);

		return "admin/Public/newsList";
	}
	
	@GetMapping(value = "/admin/public/newsDisplay")
	public void changeNewsDisplay(@RequestParam(value = "mIdx", required = false) Long mIdx, @RequestParam(value = "display", required = false) String display, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		boolean changeOnair = adminPublicService.changeNewsDisplay(mIdx,display);
		if (changeOnair == false) {
			out.println("<script>alert('육아정보 진열 변경에 실패하였습니다.'); window.location='/admin/public/tvCf';</script>");
			out.flush();
		}
	}
	
	@PostMapping(value = "/admin/public/newsListRegist")
	public String registerNewsList(@ModelAttribute("params") final PublicDTO params, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isRegistered = adminPublicService.registerNewsList(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/public/newsList", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/public/newsList", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 등록이 완료되었습니다.'); window.location='/admin/public/newsList';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/admin/public/newsList", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/public/newsListDetail")
	public String openNewsListDetail(@ModelAttribute("params") PublicDTO params, @RequestParam(value = "mIdx", required = false) Long mIdx, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "admin/public/newsList", Method.GET, null, model);
		}
		PublicDTO news = adminPublicService.getNewsListDetail(mIdx);
		if (news == null) {
			out.println("<script>alert('없는 게시글이거나 이미 삭제된 게시글입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "admin/public/newsList", Method.GET, null, model);
		}
		news.setMIdx(mIdx);
		model.addAttribute("news", news);

		return "admin/Public/newsListDetail";
	}
	/*
	@PostMapping(value = "/admin/baby/babyInfoRegist")
	public String registerBabyInfo(@ModelAttribute("params") final BabyDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String originalName = fileName.getOriginalFilename();
			String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
			String uuid = UUID.randomUUID().toString();
			String savefileName = uuid + "_" + file;
			Path savePath = Paths.get(request.getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/babyInfo/" + savefileName);
			
			fileName.transferTo(savePath);
			params.setMbsImage(savefileName);
			boolean isRegistered = adminBabyService.registerBabyInfo(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 등록이 완료되었습니다.'); window.location='/admin/baby/babyInfoList';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/baby/babyInfoDetail")
	public String openbabyInfoDetail(@ModelAttribute("params") BabyDTO params, @RequestParam(value = "mbsIdx", required = false) Long mbsIdx, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "admin/baby/babyInfoList", Method.GET, null, model);
		}
		BabyDTO babyInfo = adminBabyService.getBabyInfoDetail(mbsIdx);
		if (babyInfo == null) {
			out.println("<script>alert('없는 게시글이거나 이미 삭제된 게시글입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "admin/baby/babyInfoList", Method.GET, null, model);
		}
		babyInfo.setMbsIdx(mbsIdx);
		model.addAttribute("babyInfo", babyInfo);

		return "admin/baby/babyInfoDetail";
	}
	
	@PostMapping(value = "/admin/baby/babyInfoUpdate")
	public String updateBabyInfo(@ModelAttribute("params") final BabyDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
				if(!fileName.getOriginalFilename().equals("")) {
					String originalName = fileName.getOriginalFilename();
					String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
					String uuid = UUID.randomUUID().toString();
					String savefileName = uuid + "_" + file;
					Path savePath = Paths.get(request.getSession().getServletContext().getRealPath("/..") + "/WEB-INF/classes/static/upload/admin/babyInfo/" + savefileName);
					
					fileName.transferTo(savePath);
					params.setMbsImage(savefileName);
				}
			boolean isRegistered = adminBabyService.registerBabyInfo(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 수정에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 수정이 완료되었습니다.'); window.location='/admin/baby/babyInfoList';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 수정이 완료되었습니다.", "/admin/baby/babyInfoList", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/baby/babyInfoActive")
	public void changeActive(@RequestParam(value = "mbsIdx", required = false) Long mbsIdx, @RequestParam(value = "display", required = false) int display, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		BabyDTO babyInfo = adminBabyService.getBabyInfoDetail(mbsIdx);
		babyInfo.setMbsIdx(mbsIdx);
		babyInfo.setMbsActive(display);
		boolean isRegistered = adminBabyService.registerBabyInfo(babyInfo);
		if (isRegistered == false) {
			out.println("<script>alert('육아정보 진열 변경에 실패하였습니다.'); window.location='/admin/baby/babyInfoList';</script>");
			out.flush();
		}
	}
	
	@GetMapping(value = "/admin/baby/babyInfoCheck")
	public void changeCheck(@RequestParam(value = "mbsIdx", required = false) Long mbsIdx, @RequestParam(value = "display", required = false) int display, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		BabyDTO babyInfo = adminBabyService.getBabyInfoDetail(mbsIdx);
		babyInfo.setMbsIdx(mbsIdx);
		babyInfo.setMbsCheck(display);
		boolean isRegistered = adminBabyService.registerBabyInfo(babyInfo);
		if (isRegistered == false) {
			out.println("<script>alert('메인화면 진열 변경에 실패하였습니다.'); window.location='/admin/baby/babyInfoList';</script>");
			out.flush();
		}
	}*/
	/*
	@RequestMapping(value = "/admin/baby/{viewName}")
    public String adminMain(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		// View attribute
		return "admin/baby/"+viewName;
    }

*/
}
