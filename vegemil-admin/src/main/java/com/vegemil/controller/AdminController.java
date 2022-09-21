package com.vegemil.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vegemil.constant.Method;
import com.vegemil.domain.AdminDTO;
import com.vegemil.service.AdminService;
import com.vegemil.util.UiUtils;

@Controller
public class AdminController extends UiUtils {
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/admin/index")
	public String adminIndex(HttpServletRequest req, Model model) {
		
		// View attribute
	    return "admin/index1";
	}
	
	@RequestMapping(value = "/admin/auth/{viewName}")
    public String adminAuth(@PathVariable(value = "viewName", required = false) String viewName, HttpServletRequest req, Model model)throws Exception{
		
		
		return "admin/auth/"+viewName;
    }
	
	@RequestMapping(value = "/admin/account/{viewName}")
    public String adminAccount(@PathVariable(value = "viewName", required = false) String viewName, HttpServletRequest req, Model model)throws Exception{
		
		HttpSession session = req.getSession();
		if(viewName.equals("settings")) {
			AdminDTO admin = (AdminDTO) session.getAttribute("admin");
			admin = adminService.getAdmin(admin.getAId());
			if(admin != null) {
				model.addAttribute("admin", admin);
			}
		}
		
		return "admin/account/"+viewName;
    }
	
	@PostMapping("/admin/login" )
	public String adminLogin(AdminDTO adminDto, Model model , HttpServletRequest req, RedirectAttributes rttr) {
		int result = 0;
		HttpSession session = req.getSession();
		
		try {
			Map<String, Object> params = adminService.validationLogin(adminDto);
			
			if(params.get("result").equals(0)) {
				session.setAttribute("admin", params.get("admin"));
				return showMessageWithRedirect("관리자로 로그인 합니다.", "/admin/index", Method.GET, null, model);
			} else if(result == 1) {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("해당 유저가 존재하지 않습니다.", "/admin/auth/login", Method.GET, null, model);
			} else if(result == 2) {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("비밀번호가 일치하지 않습니다.", "/admin/auth/login", Method.GET, null, model);
			} else {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("올바르지 않은 접근입니다.", "/admin/auth/login", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/auth/login", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/auth/login", Method.GET, null, model);
		}
    }
	
    // 회원가입 처리
    @PostMapping("/admin/signUp")
    public String adminSignup(AdminDTO adminDto, Model model) {
    	
    	try {
	    	
	    	boolean isRegistered = adminService.joinAdmin(adminDto);
			if (isRegistered == false) {
				return showMessageWithRedirect("회원 가입 실패하였습니다.", "/admin/auth/signUp", Method.GET, null, model);
			}
    	} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/auth/signUp", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/auth/signUp", Method.GET, null, model);
		}
    	
    	return showMessageWithRedirect("가입이 완료되었습니다.\n 로그인 페이지로 이동 합니다.", "/admin/auth/login", Method.GET, null, model);

    }

    // 로그아웃 결과 페이지
    @GetMapping("/admin/logout")
    public String adminLogout(HttpServletRequest req, Model model) {
    	
    	HttpSession session = req.getSession();
    	session.setAttribute("admin", null);
    	return showMessageWithRedirect("로그아웃 되었습니다.\n로그인 페이지로 이동 합니다.", "/admin/auth/login", Method.GET, null, model);
    	
    }
    
    @GetMapping("/admin/auth/login")
    public String moveAdminLogin(HttpServletRequest req, Model model) {
    	
    	if(req.getParameter("msg") != null) {
	    	if(req.getParameter("msg").equals("session")) {
	    		return showMessageWithRedirect("세션이만료되어 \n로그인 페이지로 이동 합니다.", "/admin/auth/login", Method.GET, null, model);
	    	}
    	}
        return "/admin/auth/login";
    }

    @PostMapping("/admin/passChange" )
	public String adminPassChange(AdminDTO adminDto, Model model , HttpServletRequest req, RedirectAttributes rttr) {
		int result = 0;
		HttpSession session = req.getSession();
		
		try {
			Map<String, Object> params = adminService.validationLogin(adminDto);
			
			if(params.get("result").equals(0)) {
				session.setAttribute("admin", params.get("admin"));
				return showMessageWithRedirect("관리자로 로그인 합니다.", "/admin/index", Method.GET, null, model);
			} else if(result == 1) {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("해당 유저가 존재하지 않습니다.", "/admin/auth/login", Method.GET, null, model);
			} else if(result == 2) {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("비밀번호가 일치하지 않습니다.", "/admin/auth/login", Method.GET, null, model);
			} else {
				session.setAttribute("admin", null);
				return showMessageWithRedirect("올바르지 않은 접근입니다.", "/admin/auth/login", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/auth/login", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/auth/login", Method.GET, null, model);
		}
    }

}
