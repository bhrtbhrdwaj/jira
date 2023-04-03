package com.jira.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends OncePerRequestFilter {
    public static List<String> staticTokens = Arrays.asList("abc", "def");
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            logger.info(request.getRequestURL());
            String token = request.getHeader(SwaggerConfig.AUTHORIZATION_HEADER);
            if (!staticTokens.contains(token)) {
                throw new RuntimeException("Not a valid token!");
            }
        } catch (Exception e) {
            logger.error("Could not authenticate user", e);
            setResponse(response, e.getMessage(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED);
        }


        filterChain.doFilter(request, response);
    }
    private void setResponse(HttpServletResponse response, String message, int httpStatusValue, HttpStatus httpStatus)
            throws IOException {
        response.setStatus(httpStatusValue);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonRespString = ow.writeValueAsString(ResponseEntity.status(httpStatus).body(message));
        response.setContentType("application/json");
        response.getWriter().write(jsonRespString);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println(request.getRequestURI());
        AntPathMatcher pathMatcher = new AntPathMatcher(AntPathMatcher.DEFAULT_PATH_SEPARATOR);
        return excludeFilter.stream().anyMatch(path -> pathMatcher.match(path, request.getRequestURI()));
    }
    public static List<String> excludeFilter = Arrays.asList(SecurityConfig.AUTH_WHITELIST);
}
