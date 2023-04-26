package hk.hku.filter;

import hk.hku.entity.HkuUserEntity;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class UserSessionFilter implements Filter {
    // make an array that need to be excluded
    private String[] excludeUrl = new String[] {"/login", "/register", "/VerifycodeServlet", "/exUserNameServlet", "/registerAjaxServlet", "/ajaxRegister.jsp"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // get user conversation from session and check if it is login before
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String contextPath = httpServletRequest.getContextPath();
        for (int i=0; i<excludeUrl.length; i++){
            String url = excludeUrl[i];
            if((contextPath+url).equals(httpServletRequest.getRequestURI())){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        if(user == null){
            httpServletResponse.sendRedirect("/hku_cookie_war_exploded/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
