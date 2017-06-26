package com.tfactory.common;

public class ExceptionUtil {

	public static String getMessage(Exception e){
		
		StringBuilder builder = new StringBuilder();
		
		StackTraceElement[] arr = e.getStackTrace();
		
		
		for(StackTraceElement ele: arr){
			builder.append(ele.toString());
		}
		
		
		
		return builder.toString();
	}
}
