package cn.edu.swu.user;

<<<<<<< HEAD
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
=======
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
>>>>>>> ff52b43 (期末修改2)

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

<<<<<<< HEAD
=======
    @Override
>>>>>>> ff52b43 (期末修改2)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect("./index.html");
    }

}
<<<<<<< HEAD

=======
>>>>>>> ff52b43 (期末修改2)
