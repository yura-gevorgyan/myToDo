package am.itspace.mytodo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {
        "/todos/*"
})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/main");
        }
    }
}
