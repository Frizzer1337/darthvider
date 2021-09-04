package com.roadtoepam.darthvider.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
	
	Router execute(HttpServletRequest request);

}
