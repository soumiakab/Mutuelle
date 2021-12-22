package com.mutuelle.helpers;

public class ValidateInputs {


	public static boolean verifyMaxLength(String text,int length) {
		if(text.trim().length() >length) {
			return false;
		}
		return true;
	}
	
	
	
	public static  boolean verifyMinLength(String text,int length) {
		if(text.trim().length() < length) {
			return false;
		}
		return true;
	}
	
	
	
	public static  boolean verifyMuches(String text,String regex) {
		return text.matches(regex);
	}
	
	
	
	
	public static  boolean verifyPhone(String text) {
		String phoneRegex="\\d{9}";
		return verifyMuches( text,phoneRegex);
	}
	
	
	
	public static  boolean verifyCin(String text) {
		String cinRegex="[a-zA-Z]{2}\\d{6}";
		return verifyMuches( text,cinRegex );
	}
	
	
	
	public static  boolean verifyPassport(String text) {
		
		String passPortRegex="[a-zA-Z]{2}\\d{7}";
		return verifyMuches( text, passPortRegex);
	}
	
	
	
	
	public static  boolean verifyEmail(String text) {
		
		String emailRegex="^(.+)@(.+)$";
		return verifyMuches( text, emailRegex);
	}

	
}
