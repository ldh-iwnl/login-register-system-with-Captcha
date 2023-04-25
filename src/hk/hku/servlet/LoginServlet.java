package hk.hku.servlet;

import hk.hku.entity.HkuUserEntity;
import hk.hku.service.HkuUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    private HkuUserService hkuUsertService = new HkuUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 转发login页面
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 点击登录的时候 获取到用户的参数
        String userName = req.getParameter("userName");
        if (StringUtils.isEmpty(userName)) {
            //转发到错误页面
            req.setAttribute("errorMsg", "用户名称不能够是为空!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        String userPwd = req.getParameter("userPwd");
        // 参数验证
        if (StringUtils.isEmpty(userPwd)) {
            //转发到错误页面
            req.setAttribute("errorMsg", "userPwd不能够是为空!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        // 在调用业务逻辑层
        HkuUserEntity hkuUserEntity = hkuUsertService.login(userName, userPwd);
        if (hkuUserEntity == null) {
            // 用户名称或者密码错误!
            req.setAttribute("errorMsg", "用户名称或者是密码错误!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        String rememberPassword = req.getParameter("rememberPassword");
        if("on".equals(rememberPassword)){
            Cookie userName1 = new Cookie("userName", userName);
            userName1.setMaxAge(60 * 60 * 24 * 7);
            Cookie userPwd1 = new Cookie("userPwd", userPwd);
            userPwd1.setMaxAge(60 * 60 * 24 * 7);
            // put cookie back to client
            resp.addCookie(userName1);
            resp.addCookie(userPwd1);
        }

        // 能够db中查询到对象 登录成功了  将用户数据存放在session中
        HttpSession session = req.getSession();
        session.setAttribute("user", hkuUserEntity);
        // 在转发到首页（重定向到首页）
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
        resp.sendRedirect("index.jsp");
        resp.getWriter().println("login success" + hkuUserEntity.getUserName());
    }}
