package org.qf.web.Provider.Add;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsProviderService;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/addProviderCode")
public class AddProviderCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        String proCode = req.getParameter("proCode");
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(proCode)){
            map.put("proCode","exit");
        }else{
            try {
                SmbmsProvider smbmsProvider = smbmsProviderService.queryProviderByproCode(proCode);
                if(null!=smbmsProvider.getProCode()){
                    map.put("proCode","exit");
                }else {
                    map.put("proCode","noexit");
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
