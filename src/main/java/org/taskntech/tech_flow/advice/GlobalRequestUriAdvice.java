package org.taskntech.tech_flow.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


//injects  the request URI into models for all controllers
@ControllerAdvice
public class GlobalRequestUriAdvice {

    @ModelAttribute("requestUri")
    public String requestUri(HttpServletRequest request) {

        return request.getRequestURI();
    }

}
