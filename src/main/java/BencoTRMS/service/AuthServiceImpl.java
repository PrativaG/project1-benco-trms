package BencoTRMS.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import BencoTRMS.pojos.Employee;

public class AuthServiceImpl implements AuthService{
	
	EmployeeServiceImpl empServ = new EmployeeServiceImpl();
	
	private Map<String, String> tokenRepo = new HashMap<>();

	
	@Override
	public boolean authenticateUser(String email, String password) {
		//first check whether there is username in database
		//if true, check if password matches
		//if password matches, redirect to login page
		
		Employee registeredEmp = empServ.displayEmployeeByEmail(email);
		
		if(registeredEmp == null) {
			
			return false;
			
		}else{
			
			try {
				if(PasswordHashing.validatePassword(password, registeredEmp.getPassword())){
					
					return true;
					
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		return false;
	}

	@Override
	public String createToken(String username) {
		try {
			
			String token = PasswordHashing.hashPassword(username);
			tokenRepo.put(token, username);
			return token;
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		} catch (InvalidKeySpecException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean validateToken(String token) {
		 
		if(tokenRepo.get(token) != null) {
			return true;
		}
		
		return false;
	}

}
