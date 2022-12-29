package cn.edu.swu.bolg.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/removeFromCart")
public class RemoveFromCart extends HttpServlet {

    public final static String CART = "cart";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long blogId = Long.valueOf(request.getParameter("blogId"));
        List<Long> cart = this.getCart(request);
        System.out.println("remove id :  " + blogId);
        for (int i = 0 ; i < cart.size(); i++) {
            if (cart.get(i).equals(blogId)) {
                System.out.println("remove item " + i);
                cart.remove(i);
            }
        }

        response.setContentType("application/json; charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write("{\"code\": 200, \"message\": \"success\"}");
        }
    }

    private List<Long> getCart(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<Long> cart = (List<Long>) session.getAttribute(CART);
        if (cart == null) {
            cart = new ArrayList<Long>();
            session.setAttribute(CART, cart);
        }
        return cart;
    }

}
