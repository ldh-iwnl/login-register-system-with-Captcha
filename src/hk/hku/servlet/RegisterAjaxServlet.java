package hk.hku.servlet;


import com.alibaba.fastjson.JSONObject;
import hk.hku.Utils.RandomValidateCode;
import hk.hku.entity.HkuUserEntity;
import hk.hku.service.HkuUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerAjaxServlet")
public class RegisterAjaxServlet extends HttpServlet {
    private HkuUserService hkuUserService = new HkuUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //forward to register.jsp
        //req.getRequestDispatcher("ajaxRegister.jsp").forward(req,resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //insert the user info into database
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        String userName = req.getParameter("userName");
        if (StringUtils.isEmpty(userName)) {

            setResultError("User name cannot be empty", writer);
            return; //stop the execution
        }
        String userPwd = req.getParameter("userPwd");
        if (StringUtils.isEmpty(userPwd)) {
            setResultError("Password cannot be empty", writer);
            return; //stop the execution
        }
        //check captcha
        //compare the captcha from session and the captcha from user input
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String randomValidateCode = (String) session.getAttribute(RandomValidateCode.MAYIKT_RANDOMVALIDATECODE);
        if(StringUtils.isEmpty(randomValidateCode)){
            setResultError("Captcha is expired", writer);
            return; //stop the execution
        }
        if(!randomValidateCode.equalsIgnoreCase(code)){
            setResultError("Captcha is wrong", writer);
            return; //stop the execution
        }
        //before register, check userName exists or not
        HkuUserEntity byUsername = hkuUserService.findByUsername(userName);
        if(byUsername != null){
            setResultError("User name already exists", writer);
            return; //stop the execution
        }
        //register the user
        int register = hkuUserService.register(userName, userPwd);
        if (register <= 0) {
            //register failed
            setResultError("Register failed", writer);
            return; //stop the execution
        }
        //register success
        setResultOk("Register success", writer);

    }

    public void setResultError(String msg, PrintWriter writer){
        setResult(500, msg, writer);
    }

    public void setResult(Integer code, String msg, PrintWriter writer) {
          Map<String, String> result = new HashMap<>();
          result.put("code", code.toString());
          result.put("msg", msg);
          String jsonString = JSONObject.toJSONString(result);
          writer.print(jsonString);
          writer.close();
    }
    public void setResultOk(String msg, PrintWriter writer) {
        setResult(200, msg, writer);
    }


}
