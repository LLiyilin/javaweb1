package cn.edu.swu.user;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        System.out.println("auth filter");

        String uri = request.getRequestURI();
        System.out.println(uri);
        if ( uri.endsWith("login.html") || uri.endsWith("index.html") ||
                uri.endsWith("png") || uri.endsWith("jpg") || uri.endsWith("css") || uri.endsWith("login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null) {
            System.out.println("auth failed");
            response.sendRedirect("./login.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if (toke == Boolean.TRUE) {
                System.out.println("登录验证成功");
                chain.doFilter(request, response);
            } else {
                System.out.println("auth failed");
                response.sendRedirect("./login.html");
            }
        }
    }

}
