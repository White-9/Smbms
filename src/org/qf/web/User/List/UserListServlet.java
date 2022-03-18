package org.qf.web.User.List;

import org.qf.pojo.SmbmsRole;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsRoleServiceImpl;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsRoleService;
import org.qf.service.SmbmsUserService;
import org.qf.utils.Constants;
import org.qf.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/userList")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和用户角色
        String username = req.getParameter("userName");
        String userRoleId =req.getParameter("userRole");
        int userRole=0;
        //调用业务层代码
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();
        //判断
        if(userRoleId!=null&&!userRoleId.equals("")){
            userRole=Integer.parseInt(userRoleId);
        }
        if(username==null) {username="";}
        List<SmbmsUser> list = null;
        //分页
        //获取当前页面
        String pageIndex = req.getParameter("pageIndex");
        int currentPageNo = 1;
        if(pageIndex!=null) currentPageNo = Integer.parseInt(pageIndex);
        //获取页面容量
        int pageSize = Constants.pageSize;

        int smbmsUserCount = 0;
        try {
            //获取总记录数
            smbmsUserCount = smbmsUserService.getSmbmsUserCount(username,userRole);
            //pageSurpport赋值
            PageSupport pageSupport = new PageSupport();
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setPageSize(pageSize);
            pageSupport.setTotalCount(smbmsUserCount);
            //计算总页数
            int totalPageCount = pageSupport.getTotalPageCount();
            //控制首页和尾页
            if(currentPageNo<1) currentPageNo = 1;
            else if (currentPageNo>totalPageCount) currentPageNo = totalPageCount;

            list=smbmsUserService.queryByuserName(username,userRole,currentPageNo,pageSize);
            if(list.size()>0){
                req.setAttribute("list",list);
                req.setAttribute("userName",username);
                req.setAttribute("pages",pageSupport);
            }
            //查询用户角色
            List<SmbmsRole> list1 = smbmsRoleService.queryRole();
            if(list1.size()>0){
                req.setAttribute("roleList",list1);
                req.setAttribute("userRole",userRole);
            }
            req.getRequestDispatcher("userList.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
