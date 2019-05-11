package com.jba.opencms.web.interceptor;

import com.jba.opencms.web.type.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BreadcrumbInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(BreadcrumbInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestURI = request.getRequestURI();
        if(requestURI.startsWith("/dashboard")&&!requestURI.contains("/static")){
            logger.info("Building breadcrumbs for "+request.getRequestURI());
            if(requestURI.contains("?"))
                modelAndView.addObject("breadcrumb", requestToBreadcrumb(requestURI.substring(0, requestURI.lastIndexOf("?"))));
            else
                modelAndView.addObject("breadcrumb", requestToBreadcrumb(requestURI));
        }
    }

    private List<Breadcrumb> requestToBreadcrumb(final String requestURI){
        StringBuilder hrefBuilder = new StringBuilder();
        List<Breadcrumb> collect = Arrays.stream(requestURI.split("/"))
                .filter(element -> !element.equals(""))
                .map(element-> {
                    Breadcrumb breadcrumb = null;
                    if(element.matches("\\d+"))
                        breadcrumb = new Breadcrumb(mapping().get("digit"), extractHref(element, hrefBuilder));
                    else {
                        breadcrumb = new Breadcrumb(mapping().get(element), extractHref(element, hrefBuilder));
                    }
                    return breadcrumb;
                })
                .collect(Collectors.toList());
        return collect;
    }

    private String extractHref(String element, StringBuilder requestURI){
        requestURI.append("/"+element);
        return requestURI.toString();
    }

    private final static Map<String, String> mapping(){
        HashMap<String, String> map = new HashMap<>();
        map.put("dashboard", "Tablica");
        map.put("menu", "Edycja menu");
        map.put("digit", "Element");
        map.put("delete", "Usunięcie");
        map.put("page", "Strony");
        return map;
    }
}
