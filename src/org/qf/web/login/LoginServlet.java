package org.qf.web.login;

import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsUserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取页面的值
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("password");
        //调用业务层代码
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        try {
            SmbmsUser login = smbmsUserService.login(userCode,userPassword);
            if(null!=login){
                //转发
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userSession",login);
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
            }else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
