package org.qf.web.User.Update;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(value = "/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SmbmsUser smbmsUser = new SmbmsUser();
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        String ids = req.getParameter("id");
        Long id = Long.parseLong(ids);
        String userName = req.getParameter("userName");
        String sex = req.getParameter("gender");
        int gender = Integer.parseInt(sex);
        String time = req.getParameter("data");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(time);
            String phone = req.getParameter("userphone");
            String address = req.getParameter("userAddress");

            String userLei = req.getParameter("userRoleup");
            int userRole = Integer.parseInt(userLei);

            smbmsUser.setUserName(userName);
            smbmsUser.setGender(gender);
            smbmsUser.setBirthday(birthday);
            smbmsUser.setPhone(phone);
            smbmsUser.setAddress(address);

            //
            HttpSession session = req.getSession();
            SmbmsUser userUpdate = (SmbmsUser)session.getAttribute("userUpdate");
            if(userRole==0){
                smbmsUser.setUserRole(userUpdate.getUserRole());
            }
            if(userRole>0){
                smbmsUser.setUserRole(userRole);
            }
            SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");
            smbmsUser.setModifyBy(userSession.getId());
            smbmsUser.setModifyDate(new Date());
            smbmsUser.setId(id);
            int i  = smbmsUserService.updateUser(smbmsUser);
            if(i>0){
                req.getRequestDispatcher("/userList").forward(req,resp);
            }else {
                resp.sendRedirect("userUpdate.jsp");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
