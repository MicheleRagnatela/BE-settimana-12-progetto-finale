package it.epicode.be.epicenergyservice.model.security;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String userName;
	private String password;

}
