package org.qf.web.User.View;

import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userView")
public class UserViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用业务层接口
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        //用页面获取用户ID
        String id = req.getParameter("id");
        Long userId = 0L;
        if(id!=null) userId = Long.parseLong(id);

        try {
            SmbmsUser smbmsUser = smbmsUserService.queryUserById(userId);
            if(null!=smbmsUser){
                req.setAttribute("user",smbmsUser);
                req.getRequestDispatcher("userView.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
