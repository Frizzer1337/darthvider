package com.roadtoepam.darthvider.exception;

public class DaoException extends Exception {
	
	public DaoException() {
		
		super();
		
	}
	
	public DaoException(Throwable e) {
		
		super(e);
		
	}
	
	public DaoException(String log,Throwable e) {
		
		super(log,e);
		
	}
	
	public DaoException(String log) {
		
		super(log);
		
	}

}
