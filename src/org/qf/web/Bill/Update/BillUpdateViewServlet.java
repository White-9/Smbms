package org.qf.web.Bill.Update;

import org.qf.pojo.SmbmsBill;
import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsBillService;
import org.qf.service.SmbmsProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/billUpdateView")
public class BillUpdateViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用业务层接口
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        //获取订单id
        String id = req.getParameter("id");
        Long billId = 0L;
        if(id!=null)    billId = Long.parseLong(id);
        try {
            HttpSession session = req.getSession();
            SmbmsBill smbmsBill = smbmsBillService.queryBillById(billId);
            List<SmbmsProvider> list = smbmsProviderService.queryName();
            if(list.size()>0){
                req.setAttribute("providerList",list);
            }
            if(null!=smbmsBill){
                session.setAttribute("billUpdate",smbmsBill);
                req.getRequestDispatcher("billUpdate.jsp").forward(req,resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
