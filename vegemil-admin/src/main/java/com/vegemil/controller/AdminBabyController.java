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
import com.vegemil.domain.BabyDTO;
import com.vegemil.service.AdminBabyService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminBabyController extends UiUtils {

	@Autowired
	private AdminBabyService adminBabyService;
	
	@GetMapping(value = "/admin/baby/babyInfoList")
	public String openBabyInfoList(@ModelAttribute("params") BabyDTO params, Model model) {
		List<BabyDTO> babyInfoList = adminBabyService.getBabyInfoList(params);
		model.addAttribute("babyInfoList", babyInfoList);

		return "admin/baby/babyInfoList";
	}
	
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
	}
	/*
	@RequestMapping(value = "/admin/baby/{viewName}")
    public String adminMain(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		// View attribute
		return "admin/baby/"+viewName;
    }

	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/adminBoard.do", Method.GET, null, model);
			}
			model.addAttribute("board", board);
		}

		return "board/write";
	}

	@PostMapping(value = "/board/register.do")
	public String registerBoard(@ModelAttribute("params") final BoardDTO params, Model model) {
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
	}

	@GetMapping(value = "/board.do")
	public String openBoardList(@ModelAttribute("params") BoardDTO params, Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		model.addAttribute("boardList", boardList);

		return "board";
	}
	
	@GetMapping(value = "/adminBoard.do")
	public String adminBoardList(@ModelAttribute("params") BoardDTO params, Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		model.addAttribute("boardList", boardList);

		return "adminBoard";
	}

	@GetMapping(value = "/board/view.do")
	public String openBoardDetail(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "board", Method.GET, null, model);
		}

		BoardDTO board = boardService.getBoardDetail(idx);
		if (board == null || "Y".equals(board.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "board", Method.GET, null, model);
		}
		model.addAttribute("board", board);

		return "board/view";
	}

	@PostMapping(value = "/board/delete.do")
	public String deleteBoard(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/adminBoard.do", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/adminBoard.do", Method.GET, pagingParams, model);
	}
*/
	
	@GetMapping(value = "/admin/baby/babyQnaList")
	public String openBabyQnaList(@ModelAttribute("params") BabyDTO params, Model model) {
		List<BabyDTO> babyQnaList = adminBabyService.getBabyQnaList(params);
		model.addAttribute("babyQnaList", babyQnaList);

		return "admin/baby/babyQnaList";
	}
	
	@PostMapping(value = "/admin/baby/babyQnaRegist")
	public String registerBabyQna(@ModelAttribute("params") final BabyDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String originalName = fileName.getOriginalFilename();
			String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
			String uuid = UUID.randomUUID().toString();
			String savefileName = uuid + "_" + file;
			Path savePath = Paths.get(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/babyQna/" + savefileName);
			
			fileName.transferTo(savePath);
			params.setMbsImage(savefileName);
			boolean isRegistered = adminBabyService.registerBabyQna(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 등록에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 등록이 완료되었습니다.'); window.location='/admin/baby/babyQnaList';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/baby/babyQnaDetail")
	public String openbabyQnaDetail(@ModelAttribute("params") BabyDTO params, @RequestParam(value = "mbsIdx", required = false) Long mbsIdx, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "admin/baby/babyQnaList", Method.GET, null, model);
		}
		BabyDTO babyQna = adminBabyService.getBabyQnaDetail(mbsIdx);
		if (babyQna == null) {
			out.println("<script>alert('없는 게시글이거나 이미 삭제된 게시글입니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "admin/baby/babyQnaList", Method.GET, null, model);
		}
		babyQna.setMbsIdx(mbsIdx);
		model.addAttribute("babyQna", babyQna);

		return "admin/baby/babyQnaDetail";
	}
	
	@PostMapping(value = "/admin/baby/babyQnaUpdate")
	public String updateBabyQna(@ModelAttribute("params") final BabyDTO params, @RequestParam("fileName") MultipartFile fileName, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
				if(!fileName.getOriginalFilename().equals("")) {
					String originalName = fileName.getOriginalFilename();
					String file = originalName.substring(originalName.lastIndexOf("\\") + 1);
					String uuid = UUID.randomUUID().toString();
					String savefileName = uuid + "_" + file;
					Path savePath = Paths.get(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/classes/static/upload/admin/babyQna/" + savefileName);
					
					fileName.transferTo(savePath);
					params.setMbsImage(savefileName);
				}
			boolean isRegistered = adminBabyService.registerBabyQna(params);
			if (isRegistered == false) {
				out.println("<script>alert('게시글 수정에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
			}
		} catch (DataAccessException e) {
			out.println("<script>alert('데이터베이스 처리 과정에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);

		} catch (Exception e) {
			out.println("<script>alert('시스템에 문제가 발생하였습니다.'); history.go(-1);</script>");
			out.flush();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);
		}
		
		out.println("<script>alert('게시글 수정이 완료되었습니다.'); window.location='/admin/baby/babyQnaList';</script>");
		out.flush();

		return showMessageWithRedirect("게시글 수정이 완료되었습니다.", "/admin/baby/babyQnaList", Method.GET, null, model);
	}
	
	@GetMapping(value = "/admin/baby/babyQnaActive")
	public void changeQnaActive(@RequestParam(value = "mbsIdx", required = false) Long mbsIdx, @RequestParam(value = "display", required = false) int display, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		BabyDTO babyQna = adminBabyService.getBabyQnaDetail(mbsIdx);
		babyQna.setMbsIdx(mbsIdx);
		babyQna.setMbsActive(display);
		boolean isRegistered = adminBabyService.registerBabyQna(babyQna);
		if (isRegistered == false) {
			out.println("<script>alert('육아정보 진열 변경에 실패하였습니다.'); window.location='/admin/baby/babyQnaList';</script>");
			out.flush();
		}
	}
	
	@GetMapping(value = "/admin/baby/babyQnaCheck")
	public void changeQnaCheck(@RequestParam(value = "mbsIdx", required = false) Long mbsIdx, @RequestParam(value = "display", required = false) int display, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (mbsIdx == null) {
			out.println("<script>alert('올바르지 않은 접근입니다.'); history.go(-1);</script>");
			out.flush();
		}
		BabyDTO babyQna = adminBabyService.getBabyQnaDetail(mbsIdx);
		babyQna.setMbsIdx(mbsIdx);
		babyQna.setMbsCheck(display);
		boolean isRegistered = adminBabyService.registerBabyQna(babyQna);
		if (isRegistered == false) {
			out.println("<script>alert('메인화면 진열 변경에 실패하였습니다.'); window.location='/admin/baby/babyQnaList';</script>");
			out.flush();
		}
	}
}
