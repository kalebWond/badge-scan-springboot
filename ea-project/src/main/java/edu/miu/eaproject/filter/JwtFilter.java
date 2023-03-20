package edu.miu.eaproject.filter;
import edu.miu.eaproject.Util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(("Bearer "))) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.split(" ")[1].trim();

        if (!jwtUtil.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = null;

        try {
            email = jwtUtil.getUsernameFromToken(token);
        } catch (ExpiredJwtException e) { // TODO come back here!
            String isRefreshToken = request.getHeader("isRefreshToken");
        }

        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        var userDetails = userDetailsService.loadUserByUsername(email);//This line retrieves a user's details (e.g., username, password, and authorities) from the database using the provided email as the username.
        // The userDetailsService is an instance of the UserDetailsService interface, which is responsible for loading user-specific data for authentication.
        boolean isTokenValid = jwtUtil.validateToken(token);//This line validates the authenticity and integrity of a JSON Web Token (JWT) by verifying its signature and expiration time.
        // The jwtUtil is an instance of the JwtUtil class, which provides helper methods for JWT-related operations.
        if (isTokenValid) {//This line checks if the JWT is valid. If it is, the code inside the curly braces will be executed.
            // Otherwise, the request will be rejected and the client will receive an unauthorized response.
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//This line sets additional details about the authentication request, such as the remote IP address and session ID.
            // The WebAuthenticationDetailsSource is a helper class that extracts the relevant details from the incoming HTTP request.
            SecurityContextHolder.getContext().setAuthentication(authentication);//This line sets the authenticated Authentication object to the current security context, which is stored in a thread-local variable.
            // This allows subsequent security checks and method invocations to access the authenticated user's information.
        }


        filterChain.doFilter(request, response);//This line passes the HTTP request and response objects to the next filter in the chain, allowing it to process the request.
        // The filterChain is an instance of the FilterChain interface, which provides a way to chain multiple filters and interceptors to handle incoming requests.
    }
}
