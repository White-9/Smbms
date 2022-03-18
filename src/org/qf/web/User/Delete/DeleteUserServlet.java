package org.qf.web.User.Delete;

import com.mysql.jdbc.StringUtils;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        String id = req.getParameter("id");
        Long userId = 0L;
        if(id!=null)  userId = Long.parseLong(id);

        try {
            int i = smbmsUserService.deleteUserById(userId);
            if(i>0){
                req.getRequestDispatcher("/userList").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
