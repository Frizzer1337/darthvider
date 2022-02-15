package com.roadtoepam.darthvider.exception;

public class ServiceException extends Exception{
	
	public ServiceException() {
		
		super();
		
	}
	
	public ServiceException(Throwable e) {
		
		super(e);
		
	}
	
	public ServiceException(String log,Throwable e) {
		
		super(log,e);
		
	}
	
	public ServiceException(String log) {
		
		super(log);
		
	}

}
