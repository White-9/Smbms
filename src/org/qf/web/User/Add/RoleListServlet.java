package org.qf.web.User.Add;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.qf.pojo.SmbmsRole;
import org.qf.service.Impl.SmbmsRoleServiceImpl;
import org.qf.service.SmbmsRoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/roleList")
public class RoleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();
        try {
            List<SmbmsRole> roleList = smbmsRoleService.queryRole();
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(roleList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
