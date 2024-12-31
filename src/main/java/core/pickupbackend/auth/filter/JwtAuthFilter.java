package core.pickupbackend.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.pickupbackend.auth.service.JwtService;
import core.pickupbackend.global.common.ErrorResponse;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private final JwtService jwtService;
    private final UrlWhiteListChecker whiteListChecker;
    private final ObjectMapper objectMapper;

    public JwtAuthFilter(final JwtService jwtService, final UrlWhiteListChecker whiteListChecker, final ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.whiteListChecker = whiteListChecker;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String uri = request.getRequestURI();
        final String method = request.getMethod();

        if (whiteListChecker.isAllowedUri(uri, method)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendErrorResponse(response);
            return;
        }

        final String accessToken = authHeader.substring(7);

        if (!jwtService.isAlreadyLogin(accessToken)) {
            sendErrorResponse(response);
            return;
        }
    }

    private void sendErrorResponse(final HttpServletResponse response) throws IOException {
        response.setStatus(ErrorCode.INVALID_ACCESS_TOKEN.getStatus().value());
        response.setContentType(CONTENT_TYPE);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_ACCESS_TOKEN);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
