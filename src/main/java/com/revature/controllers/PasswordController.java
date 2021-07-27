package com.revature.controllers;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordController {
	private static int iterations = 1000;
	
    public String passwordHash(String password)
    {       
        try {
            char[] chars = password.toCharArray();
        	byte[] salt = getSalt();
         
        	PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        	SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        	byte[] hash = skf.generateSecret(spec).getEncoded();
        	return toHex(salt) + ":" + toHex(hash);
        } catch(NoSuchAlgorithmException e) {
			System.err.println("Hex Converting Fail: " + e.getMessage());
    	} catch(InvalidKeySpecException d) {
			System.err.println("Key Factory Get Instance Fail: " + d.getMessage());
        }
        
        return null;
    }
     
    private byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    private String toHex(byte[] array) throws NoSuchAlgorithmException
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
    
    public boolean validatePassword(String originalPassword, String storedPassword)
    {
    	byte[] salt = null;
    	byte[] hash = null;
    	byte[] testHash = null;

    	try {
        	String[] parts = storedPassword.split(":");
        	salt = fromHex(parts[0]);
        	hash = fromHex(parts[1]);
         
        	PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        	SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        	testHash = skf.generateSecret(spec).getEncoded();
    	} catch(NoSuchAlgorithmException e) {
			System.err.println("Hex Converting Fail: " + e.getMessage());
			return false;    	
    	} catch(InvalidKeySpecException d) {
			System.err.println("Key Factory Get Instance Fail: " + d.getMessage());
			return false;
    	}
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    
    private byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
