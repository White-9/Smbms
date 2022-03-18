package org.qf.web.User.Add;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();

        SmbmsUser smbmsUser = new SmbmsUser();

        String userCode = req.getParameter("userId");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userlei");

        String sex = req.getParameter("gender");
        int gender = Integer.parseInt(sex);

        String time = req.getParameter("data");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(time);

            String phone = req.getParameter("userphone");
            String address = req.getParameter("userAddress");
            String userLei = req.getParameter("userlei");
            int userRole = Integer.parseInt(userLei);

            smbmsUser.setUserCode(userCode);
            smbmsUser.setUserName(userName);
            smbmsUser.setUserPassword(userPassword);
            smbmsUser.setGender(gender);
            smbmsUser.setBirthday(birthday);
            smbmsUser.setPhone(phone);
            smbmsUser.setAddress(address);
            smbmsUser.setUserRole(userRole);
            //session
            HttpSession session = req.getSession();
            SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");
            smbmsUser.setCreatedBy(userSession.getId());
            smbmsUser.setCreationDate(new Date());
            int i = smbmsUserService.addUser(smbmsUser);
            if(i>0){
                req.getRequestDispatcher("/userList").forward(req,resp);
            }else {
                req.getRequestDispatcher("/addUser").forward(req,resp);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
