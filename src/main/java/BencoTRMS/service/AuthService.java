package BencoTRMS.service;

public interface AuthService {

		public boolean authenticateUser(String username, String password);
		
		public String createToken(String s);
		
		public boolean validateToken(String s);
}
