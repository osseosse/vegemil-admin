package com.vegemil.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vegemil.domain.AdminDTO;
import com.vegemil.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
    public boolean joinAdmin(AdminDTO adminDto) {
    	
        // 비밀번호 암호화
        adminDto.setAPwd(passwordEncoder.encode(adminDto.getAPwd()));
        
        int queryResult = 0;

		queryResult = adminMapper.insertAdmin(adminDto);
		if(queryResult == 1) {
			return true;
		} else {
			return false;
		}
               
    }
    
    @Override
    public Map<String, Object> validationLogin(AdminDTO adminDto) {
    	AdminDTO loginUser = adminMapper.findAdminById(adminDto.getAId());
    	Map<String, Object> params = new LinkedHashMap<>();

       if(loginUser==null) {
    	   params.put("result", 1);
    	   return params;
       }

       if(!passwordEncoder.matches(adminDto.getAPwd(), loginUser.getAPwd())) {
    	   params.put("result", 2);
    	   return params;
       }
       
       params.put("result", 0);
       params.put("admin", loginUser);
       return params;
    }
    
    @Override
    public AdminDTO getAdmin(String aId) {
    	AdminDTO loginUser = adminMapper.findAdminById(aId);
    	
    	return loginUser;
    	
    }

}
