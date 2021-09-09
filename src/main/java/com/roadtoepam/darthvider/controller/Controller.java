package com.roadtoepam.darthvider.controller;

import java.io.IOException;
import java.util.Optional;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.CommandProvider;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(COMMAND);
        System.out.println(commandName);
        Optional<Command> command = COMMAND_PROVIDER.getCommand(commandName);
        Router router;
		try {
			router = command.get().execute(request);
		} catch (CommandException e) {
			router = new Router(ERROR_404_PAGE,Router.RouterType.REDIRECT);
		}
        switch (router.getRouterType()) {
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect(ERROR_404_PAGE);
        }
    }


}

