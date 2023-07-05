package com.ComuniCate.proj.Service;

import com.ComuniCate.proj.Payload.LoginDto;
import com.ComuniCate.proj.Payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    String userRole(String username);
    
}
