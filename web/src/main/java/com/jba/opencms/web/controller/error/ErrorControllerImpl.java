package com.jba.opencms.web.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ErrorControllerImpl implements ErrorController {

    private final static Map<Integer, String> statusCodesToPageMapping = Stream.of(
            new AbstractMap.SimpleEntry<>(401, "error/forbidden"),
            new AbstractMap.SimpleEntry<>(403, "error/forbidden"),
            new AbstractMap.SimpleEntry<>(404, "error/not-found"),
            new AbstractMap.SimpleEntry<>(500, "error/error")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());

        return statusCodesToPageMapping.getOrDefault(statusCode, "error/error");
    }

    @RequestMapping("/forbidden")
    public String fobidden(){
        return "error/forbidden";
    }

    @RequestMapping("/other")
    public String other(){
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
