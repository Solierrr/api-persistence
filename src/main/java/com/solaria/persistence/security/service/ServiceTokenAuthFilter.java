package com.solaria.persistence.security.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import org.springframework.web.filter.OncePerRequestFilter;

import com.solaria.persistence.exception.ServiceAuthException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
/**
 * Classe responsavel por validar e extrair o token de serviço
 * Sem @component para não ser instanciada com filtro global(serve apenas rotas /internal/**)
 */
public class ServiceTokenAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    // única classe que sabe emitir/validar o token de serviço.
    private final ServiceTokenProvider serviceTokenProvider;

    public ServiceTokenAuthFilter(ServiceTokenProvider serviceTokenProvider) {
        this.serviceTokenProvider = serviceTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        // verifica se existe um header authorization e se é bearer
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            // remove "Bearer ", sobrando apenas o jwt
            String token = header.substring(BEARER_PREFIX.length());
            try {
                // Validação de assinatura, issuer e expiração por ServiceTokenProvider;
                // TOKEN_TYPE_ACCESS garante que apenas access tokens são válidados
                serviceTokenProvider.parseAndValidate(token, ServiceTokenProvider.TOKEN_TYPE_ACCESS);

                // Token validado -> constroi uma authentication já verificada com a auhtority ROLE_SERVICE
                // Authority única para M2M
                Authentication authentication = new PreAuthenticatedAuthenticationToken(
                        "internal-client", null, 
                        List.of(new SimpleGrantedAuthority("ROLE_SERVICE")));

                // Seta a authtentication para a request atual
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ServiceAuthException ex) {
                // em caso de erro, remove qualquer authentication da trhead
                SecurityContextHolder.clearContext();
            }
        }

        // dispacha request para o próximo filtro
        filterChain.doFilter(request, response);
    }
}
