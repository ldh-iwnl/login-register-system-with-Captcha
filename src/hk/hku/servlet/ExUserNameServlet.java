package hk.hku.servlet;

import hk.hku.service.HkuUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/exUserNameServlet")
public class ExUserNameServlet  extends HttpServlet {
    private HkuUserService hkuUserService = new HkuUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. Get the username from the request
        String userName = req.getParameter("userName");
        // 2. Check if the username exists
        PrintWriter writer = resp.getWriter();
        if (hkuUserService.findByUsername(userName) != null){
            writer.print("this username" + userName + "already exists");
        }
        writer.close();
    }
}
