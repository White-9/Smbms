package org.qf.web.User.Update;

import org.qf.pojo.SmbmsRole;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsRoleServiceImpl;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsRoleService;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/userUpdateView")
public class UserUpdateViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用业务层接口
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();
        //用页面获取用户ID
        String id = req.getParameter("id");
        Long userId = 0L;
        if(id!=null) userId = Long.parseLong(id);

        try {
            SmbmsUser smbmsUser = smbmsUserService.queryUserById(userId);
            HttpSession httpSession = req.getSession();

            if(null!=smbmsUser){
                httpSession.setAttribute("userUpdate",smbmsUser);
            }
            List<SmbmsRole> roleList = smbmsRoleService.queryRole();
            if(roleList.size()>0){
                req.setAttribute("roleList",roleList);
            }
            req.getRequestDispatcher("userUpdate.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
