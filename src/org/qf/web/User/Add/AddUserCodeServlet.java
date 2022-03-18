package org.qf.web.User.Add;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/addUserCode")
public class AddUserCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String userCode = req.getParameter("userCode");
        SmbmsUserService smbmsUserService = new SmbmsUserServiceImpl();
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(userCode)){
            map.put("userCode","exit");
        }else {
            try {
                SmbmsUser smbmsUser = smbmsUserService.queryByuserCode(userCode);
                if(null!=smbmsUser.getUserCode()){
                    map.put("userCode","exit");
                }else {
                    map.put("userCode","noexit");
                }
                PrintWriter writer = resp.getWriter();
                writer.write(JSONArray.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
