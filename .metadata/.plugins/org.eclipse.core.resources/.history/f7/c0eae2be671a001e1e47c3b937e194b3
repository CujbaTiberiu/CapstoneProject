package com.ComuniCate.proj.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ComuniCate.proj.Entity.Role;
import com.ComuniCate.proj.Entity.User;
import com.ComuniCate.proj.Enum.ERole;
import com.ComuniCate.proj.Exception.MyAPIException;
import com.ComuniCate.proj.Model.EmailValidator;
import com.ComuniCate.proj.Model.TaxCodeValidator;
import com.ComuniCate.proj.Payload.LoginDto;
import com.ComuniCate.proj.Payload.RegisterDto;
import com.ComuniCate.proj.Repository.RoleRepository;
import com.ComuniCate.proj.Repository.UserRepository;
import com.ComuniCate.proj.Security.JwtTokenProvider;


@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getUsername(), loginDto.getPassword()
        		)
        ); 
    	System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // checks if username already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // checks if email already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        
        // checks if taxcode already exists in database
        if(userRepository.existsByTaxCode(registerDto.getTaxCode())){
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "TaxCode already exists!");
        }
        
        // check if iserted email has a vaild mail pattern
        if(!EmailValidator.validateEmail(registerDto.getEmail())){
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Invalid email format!");
        }
        
        // check if iserted taxcode has a vaild taxcode pattern
        if(!TaxCodeValidator.validateTaxCode(registerDto.getTaxCode())){
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Invalid Tax Code format!");
        }
        
        

        User user = new User();
        user.setName(registerDto.getName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setTaxCode(registerDto.getTaxCode());

        Set<Role> roles = new HashSet<>();
        
        if(registerDto.getRoles() != null) {
	        registerDto.getRoles().forEach(role -> {
	        	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	        	roles.add(userRole);
	        });
        } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
        }
        
        user.setRoles(roles);
        System.out.println(user);
        userRepository.save(user);

        return "User registered successfully!.";
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ADMIN")) return ERole.ROLE_ADMIN;
    	else if(role.equals("MODERATOR")) return ERole.ROLE_MODERATOR;
    	else return ERole.ROLE_USER;
    }
    
}
