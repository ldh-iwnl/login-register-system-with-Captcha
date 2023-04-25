package hk.hku.servlet;

import hk.hku.entity.HkuUserEntity;
import hk.hku.service.HkuUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private HkuUserService hkuUserService = new HkuUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //forward to register.jsp
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //insert the user info into database
        String userName = req.getParameter("userName");
        if (StringUtils.isEmpty(userName)) {
            req.setAttribute("errMsg", "User name cannot be empty");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return; //stop the execution
        }
        String userPwd = req.getParameter("userPwd");
        if (StringUtils.isEmpty(userPwd)) {
            req.setAttribute("errMsg", "User password cannot be empty");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return; //stop the execution
        }
        //before register, check userName exists or not
        HkuUserEntity byUsername = hkuUserService.findByUsername(userName);
        if(byUsername != null){
            req.setAttribute("errMsg", "User name already exists");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return; //stop the execution
        }
        //register the user
        int register = hkuUserService.register(userName, userPwd);
        if (register <= 0) {
            //register failed
            req.setAttribute("errMsg", "failed to register");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
        //register success
        resp.sendRedirect("login");

    }
}
