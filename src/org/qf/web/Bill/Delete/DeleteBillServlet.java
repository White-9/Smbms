package org.qf.web.Bill.Delete;

import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.SmbmsBillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteBill")
public class DeleteBillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //业务逻辑层
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        String id = req.getParameter("id");
        Long billId = 0L;
        if(id!=null)    billId = Long.parseLong(id);

        try {
            int i = smbmsBillService.deleteBillById(billId);
            if(i>0){
                req.getRequestDispatcher("/billList").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
