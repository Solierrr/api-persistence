package com.solaria.persistence.security.rbac;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 /**
 * Classe responsável por interceptar request antes do controller e verificar se o usuário é autorizado ou não
 */
@Component
public class EndpointAuthorizationInterceptor implements HandlerInterceptor {

    private final RbacAuthorizationService rbac;

    public EndpointAuthorizationInterceptor(RbacAuthorizationService rbac) {
        this.rbac = rbac;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (pattern == null) {
            return true;
        }
        String endpointIdentifier = request.getMethod() + " " + pattern;
        rbac.requireEndpointAccess(endpointIdentifier);
        return true;
    }
}
