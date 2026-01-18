package order_service.order__service.Config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InternalSecurityFilter extends OncePerRequestFilter {
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        String gatewayHeader = request.getHeader("X-Gateway-Header");
        System.out.println("Filter checking for Header..."); 
        
        
        System.out.println("Header Value: " + gatewayHeader);
       
        if ("WajidKey@123".equals(gatewayHeader)) {
            filterChain.doFilter(request, response);
        } else {
           
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Direct access not allowed! Route via API Gateway.\"}");
            return;
        }
    }
}
