package com.zensar.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.springBoot.dto.UserDto;
import com.zensar.springBoot.security.JwtUtils;

@RestController
@RequestMapping("/login")
public class UserController {
	@Autowired
	AuthenticationManager authenticatonManager;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<UserDto> authenticatre(@RequestBody UserDto userDto){
		try {
			authenticatonManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword()));
			String generateToken = jwtUtils.generateToken(userDto.getUserName());
			userDto.setToken(generateToken);
			return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

		}
		catch(AuthenticationException e) {
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);

		}
	}
	@GetMapping(value="/user/token/validate")
	public ResponseEntity<Boolean> checkRole(){
		try {
			//authenticatonManger.authenticate
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);

		}
		catch(AuthenticationException e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);

		}
	}

}
