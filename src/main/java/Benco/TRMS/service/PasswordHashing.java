package Benco.TRMS.service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHashing {
	
	//generating password
	public static String hashPassword(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException{
		//generate char[]password, salt, iteration for PBEKeyspec
		
		int iteration = 500;
		char[] charPwd = pwd.toCharArray();
		
		//generating salt
		SecureRandom sr = new SecureRandom().getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		
		//generating hashed password
		PBEKeySpec spec = new PBEKeySpec(charPwd, salt, iteration, 32);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hashedPw = skf.generateSecret(spec).getEncoded();
		
		 return iteration + ":" + toHex(salt) + ":" + toHex(hashedPw);
	}
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
	
	//validating password
	 public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        String[] parts = storedPassword.split(":");
	        int iterations = Integer.parseInt(parts[0]);
	        byte[] salt = fromHex(parts[1]);
	        byte[] hash = fromHex(parts[2]);
	         
	        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] testHash = skf.generateSecret(spec).getEncoded();
	         
	        int diff = hash.length ^ testHash.length;
	        for(int i = 0; i < hash.length && i < testHash.length; i++)
	        {
	            diff |= hash[i] ^ testHash[i];
	        }
	        return diff == 0;
	    }
	 
	    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	    {
	        byte[] bytes = new byte[hex.length() / 2];
	        for(int i = 0; i<bytes.length ;i++)
	        {
	            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	        }
	        return bytes;
	    }
}

