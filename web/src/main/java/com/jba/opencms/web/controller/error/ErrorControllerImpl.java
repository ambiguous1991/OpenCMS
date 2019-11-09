package com.jba.opencms.web.controller.error;

import com.jba.opencms.web.controller.AbstractController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ErrorControllerImpl extends AbstractController implements ErrorController {

    private final static Map<Integer, String> statusCodesToPageMapping = Stream.of(
            new AbstractMap.SimpleEntry<>(401, "error/forbidden"),
            new AbstractMap.SimpleEntry<>(403, "error/forbidden"),
            new AbstractMap.SimpleEntry<>(404, "error/not-found"),
            new AbstractMap.SimpleEntry<>(500, "error/error")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @RequestMapping("/error")
    public RedirectView handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());
        switch (statusCode){
            case 401: return new RedirectView("/error/forbidden");
            case 403: return new RedirectView("/error/forbidden");
            case 404: return new RedirectView("/error/not-found");
            case 500: return new RedirectView("/error/internal-server-error");
            default: return new RedirectView("/error/internal-server-error");
        }
    }

    @RequestMapping("/error/not-found")
    public String notFound(){
        return statusCodesToPageMapping.get(404);
    }

    @RequestMapping("/error/forbidden")
    public String forbidden(){
        return statusCodesToPageMapping.get(401);
    }

    @RequestMapping("/error/internal-server-error")
    public String error(){
        return statusCodesToPageMapping.get(500);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
