package com.roadtoepam.darthvider.exception;

public class CommandException extends Exception {
	
	public CommandException() {
		
		super();
		
	}
	
	public CommandException(Throwable e) {
		
		super(e);
		
	}
	
	public CommandException(String log,Throwable e) {
		
		super(log,e);
		
	}
	
	public CommandException(String log) {
		
		super(log);
		
	}

}
