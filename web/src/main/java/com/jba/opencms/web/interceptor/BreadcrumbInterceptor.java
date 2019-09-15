package com.jba.opencms.web.interceptor;

import com.jba.opencms.web.type.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BreadcrumbInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(BreadcrumbInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestURI = request.getRequestURI();
        if(shouldIntercept(request, response)){
            logger.info("Building breadcrumbs for "+requestURI);

            List<Breadcrumb> breadcrumbs = requestToBreadcrumb(requestURI);
            String breadcrumbTitle = breadcrumbs.stream().map(Breadcrumb::getLabel).collect(Collectors.joining(" - ", " - ", ""));
            modelAndView.addObject("breadcrumb", requestToBreadcrumb(requestURI));
            modelAndView.addObject("breadcrumbTitle", breadcrumbTitle);
        }
    }

    private boolean shouldIntercept(HttpServletRequest request, HttpServletResponse response){
        String uri = request.getRequestURI();
        return uri.contains("/dashboard") &&
                response.getStatus()== HttpServletResponse.SC_OK &&
                !uri.contains("/static") &&
                !uri.contains("/js") &&
                !uri.contains("/css") &&
                request.getMethod().equals("GET");
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
        map.put("edit", "Edytuj");
        map.put("images", "Obrazy");
        map.put("add", "Dodaj");
        map.put("system-variables", "Serwis");
        map.put("users", "Użytkownicy");
        map.put("change-password", "Zmiana hasła");
        map.put("presentation", "Wygląd");
        map.put("edit-css", "Edycja arkusza stylów");
        map.put("stylesheets", "Arkusze stylów");
        map.put("scripts", "Skrypty");
        map.put("file", "Plik");
        return map;
    }
}
