//package com.lms.admin.security;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//import java.util.List;
//public class JwtFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//    public JwtFilter(JwtUtil jwtUtil){ this.jwtUtil = jwtUtil; }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String h = request.getHeader("Authorization");
//        if(h != null && h.startsWith("Bearer ")){
//            String token = h.substring(7);
//            try{
//                var claims = jwtUtil.validate(token).getBody();
//                String user = claims.getSubject();
//                var auth = new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }catch(Exception e){ SecurityContextHolder.clearContext(); }
//        }
//        chain.doFilter(request, response);
//    }
//}
