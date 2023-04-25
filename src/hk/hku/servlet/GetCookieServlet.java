package hk.hku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getCookie")
public class GetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doGet acquire cookie from client
        Cookie[] cookies = req.getCookies();
        req.getSession(false);
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }
}
