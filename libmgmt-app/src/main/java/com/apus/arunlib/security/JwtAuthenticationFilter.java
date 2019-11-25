package com.apus.arunlib.security;

import com.apus.arunlib.service.UserDetailsServiceImpl;
import com.apus.arunlib.utils.LibMgmtConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Arun Kumar Raju
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final long serialVersionUID = 986478L;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = getJwtFromRequest(request);
            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                String userName = jwtTokenProvider.getUserNameFromJWT(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Error while setting user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(LibMgmtConstants.LIB_TOKEN_BEARER_PREFIX)) {
            return token.replace(LibMgmtConstants.LIB_TOKEN_BEARER_PREFIX+" ","");
        }
        return null;
    }


}
