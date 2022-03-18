package org.qf.web.Bill.Add;

import com.alibaba.fastjson.JSONArray;
import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/getProviderListForAddBill")
public class GetProviderListForAddBillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //业务逻辑层
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();

        try {
            List<SmbmsProvider> list = smbmsProviderService.queryName();
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(list));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
