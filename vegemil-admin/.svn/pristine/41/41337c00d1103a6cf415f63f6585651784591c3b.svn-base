package com.vegemil.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import com.vegemil.domain.LabDTO;
import com.vegemil.service.AdminLabService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminLabController extends UiUtils {

	@Autowired
	private AdminLabService adminLabService;
	
	@RequestMapping(value = "/admin/lab/{viewName}")
    public String adminMain(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		// View attribute
		return "admin/lab/"+viewName;
    }
	
	/*FAQ*/
	@RequestMapping(value = "/admin/lab/faqList/table", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getFaqList(@ModelAttribute("params") LabDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<LabDTO> faqList = adminLabService.getFaqList(params);
		if (CollectionUtils.isEmpty(faqList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(faqList).getAsJsonArray();
			jsonObj.add("data", jsonArr);
		}

		return jsonObj;
	 }
	
	@GetMapping(value = "/admin/lab/faqDetail")
	public String openFaqDetail(@ModelAttribute("params") LabDTO params, @RequestParam(value = "fIdx", required = false) Long fIdx, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (fIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "admin/lab/faqList", Method.GET, null, model);
		}
		LabDTO faqDetail = adminLabService.getFaqDetail(fIdx);
		if (faqDetail == null) {
			
			out.println("<script>alert('없는 게시글이거나 이미 삭제된 게시글입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "admin/lab/faqList", Method.GET, null, model);
		}
		
		model.addAttribute("faqDetail", faqDetail);

		return "admin/lab/faqDetail";
	}
	
	@RequestMapping("/admin/lab/faqDetail/imageUpload") 
	public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response){ 
		try { 
			//파일정보 
			String sFileInfo = ""; 
			//파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name"); 
			//파일 확장자 
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1); 
			//확장자를소문자로 변경 
			filename_ext = filename_ext.toLowerCase(); 
			//이미지 검증 배열변수
			String[] allow_file = {"jpg","png","bmp","gif"}; 
			//돌리면서 확장자가 이미지인지 
			int cnt = 0; 
			for(int i=0; i<allow_file.length; i++) { 
				if(filename_ext.equals(allow_file[i])){ 
					cnt++; 
				} 
			} 
			//이미지가 아님
			if(cnt == 0) { 
				PrintWriter print = response.getWriter(); 
				print.print("NOTALLOW_"+filename); 
				print.flush(); print.close(); 
			} 
			else { 
				//이미지이므로 신규 파일로 디렉토리 설정 및 업로드 
				//파일 기본경로 
				String dftFilePath = request.getSession().getServletContext().getRealPath("/"); 
				//파일 기본경로 _ 상세경로
				String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator +"multiupload" + File.separator; 
				File file = new File(filePath); 
				if(!file.exists()) { 
					file.mkdirs(); 
				} 
				String realFileNm = ""; 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss"); 
				String today= formatter.format(new java.util.Date()); 
				realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf(".")); 
				String rlFileNm = filePath + realFileNm; 
				///////////////// 서버에 파일쓰기 ///////////////// 
				InputStream is = request.getInputStream(); 
				OutputStream os = new FileOutputStream(rlFileNm); 
				int numRead; byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))]; 
				while((numRead = is.read(b,0,b.length)) != -1){ 
					os.write(b,0,numRead); 
				} 
				if(is != null) { 
					is.close(); 
				} 
				os.flush(); 
				os.close(); 
				///////////////// 서버에 파일쓰기 ///////////////// 
				// 정보 출력 
				sFileInfo += "&bNewLine=true"; 
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함 
				sFileInfo += "&sFileName="+ filename; 
				sFileInfo += "&sFileURL="+"/resources/editor/multiupload/"+realFileNm; 
				PrintWriter print = response.getWriter(); 
				print.print(sFileInfo); 
				print.flush(); 
				print.close(); 
			} 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}




	/*
	@GetMapping(value = "/admin/cs/unfairClaimDelete")
	public String deleteUnfairClaim(@RequestParam(value = "cIdx", required = false) Long cIdx, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isDelete = adminLabService.deleteUnfairClaim(cIdx);
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
	
	@PostMapping(value = "/admin/cs/unfairClaimUpdate")
	public String updateBabyInfo(@ModelAttribute("params") final CsDTO params, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			params.setCCheck("1");
			boolean isRegistered = adminLabService.registerUnfairClaim(params);
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
			
			boolean isRegistered = adminLabService.checkUnfairClaim(params);
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
	}*/
	/*FAQ*/
}
