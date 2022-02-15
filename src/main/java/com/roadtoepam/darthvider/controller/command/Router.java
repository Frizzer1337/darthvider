package com.roadtoepam.darthvider.controller.command;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT,ERROR
    }
    
    private final RouterType routerType;
    private final String pagePath;
    
    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }
    
    public RouterType getRouterType() {
        return routerType;
    }
}