package org.qf.web.Bill.Add;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.qf.pojo.SmbmsBill;
import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.SmbmsBillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/addBillCode")
public class AddBillCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //业务层接口
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        //获取数据
        String billCode = req.getParameter("billCode");
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(billCode)){
            map.put("billCode","exit");
        }else {
            try {
                SmbmsBill smbmsBill = smbmsBillService.queryBillBybillCode(billCode);
                if(null!=smbmsBill.getBillCode()){
                    map.put("billCode","exit");
                }else {
                    map.put("billCode","noexit");
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
