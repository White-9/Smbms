package org.qf.web.Bill.View;

import org.qf.pojo.SmbmsBill;
import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.SmbmsBillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/billView")
public class BillViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用业务层接口
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        //获取订单id
        String id = req.getParameter("id");
        Long billId = 0L;
        if(id!=null)    billId = Long.parseLong(id);

        try {
            SmbmsBill smbmsBill = smbmsBillService.queryBillById(billId);
            if(smbmsBill!=null){
                req.setAttribute("bill",smbmsBill);
                req.getRequestDispatcher("billView.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
