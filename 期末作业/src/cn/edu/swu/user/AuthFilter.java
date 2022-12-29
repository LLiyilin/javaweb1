package cn.edu.swu.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.endsWith("login.html")
                || uri.endsWith("/api/blogs")
                || uri.endsWith("/api/addToCart")
                || uri.endsWith("/api/getBlogsInCart")
                || uri.endsWith("/api/removeFromCart")
                || uri.endsWith("cart.html")
                || uri.endsWith("login")
                || uri.endsWith("verifyCode")
                || uri.endsWith("index.html")
                || uri.endsWith("png")
                || uri.endsWith("jpg")
                || uri.endsWith("css")
                || uri.endsWith("/add-user.html")
                || uri.endsWith("/api/addUser")
                || uri.endsWith("/saveBlog")) {
            // 对于不需要进行登录认证的资源，直接放行，继续往后执行
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("./login.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);

            if (toke != null && toke.equals(Boolean.TRUE)) {
                System.out.println("已经是登录用户");
                chain.doFilter(request, response);
            } else {
                System.out.println("未获得登录标记");
                response.sendRedirect("./login.html");
            }
        }
    }

}
