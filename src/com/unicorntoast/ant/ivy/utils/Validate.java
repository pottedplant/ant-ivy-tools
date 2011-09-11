package com.unicorntoast.ant.ivy.utils;


public abstract class Validate {
	
	public static void notEmpty(String s,String message) {
		if( s==null || s.trim().length()==0 )
			throw new ValidationException(message);
	}

}
