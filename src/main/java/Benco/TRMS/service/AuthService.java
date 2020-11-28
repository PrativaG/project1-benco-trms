package Benco.TRMS.service;

public interface AuthService {

		public boolean authenticateUser(String username, String password);
		
		public String createToken(String s);
		
		public String validateToken(String s);
}
