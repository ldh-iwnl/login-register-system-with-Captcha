package hk.hku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * add cookie to client
 */

@WebServlet("/addCookie")
public class AddCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create cookie
        Cookie cookie = new Cookie("hku","KyleLi");
        //server send cookie to client
        resp.addCookie(cookie);
    }
}
