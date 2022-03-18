package org.qf.web.password;

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

@WebServlet(value = "/password")
public class PasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        HttpSession session = req.getSession();
        SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");

        String userPassword = req.getParameter("reNewPassword");

        try {
            int i = smbmsUserService.updateUserPassword(userSession.getId(), userPassword);
            if(i>0){
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }else {
                req.getRequestDispatcher("password.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
