package com.example.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LoggerUtil {
	

	
	private static Logger logger = Logger.getLogger(LoggerUtil.class);

	
	static{
		startLogger();
	}
	
	
	/**
	 * Quick load of the lo4j Logger
	 */
	public static void startLogger() {
		BasicConfigurator.configure();
		logger.info("Logger Started");
	}

	public static void logInfo(String message){
		logger.info(message);
	}
	
	public static void logDebug(String message){
		logger.debug(message);
	}
	
	public static void logError(String message){
		logger.error(message);
	}
	
	



}
