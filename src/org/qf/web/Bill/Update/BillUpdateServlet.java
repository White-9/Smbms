package org.qf.web.Bill.Update;

import org.qf.pojo.SmbmsBill;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.SmbmsBillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/billUpdate")
public class BillUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //业务层接口
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        SmbmsBill smbmsBill = new SmbmsBill();
        //获取数据
        String billid = req.getParameter("id");
        Long id = 0L;
        if(billid!=null)    id = Long.parseLong(billid);
        String billCode = req.getParameter("providerId");
        String productName = req.getParameter("providerName");
        String productUnit = req.getParameter("people");
        String productcount = req.getParameter("phone");
        Double productCount = 0.0;
        if(productcount!=null)  productCount = Double.parseDouble(productcount);
        String totalprice = req.getParameter("address");
        Double totalPrice = 0.0;
        if(totalprice!=null)    totalPrice = Double.parseDouble(totalprice);
        String providerid = req.getParameter("fax");
        Long providerId = 0L;
        if(providerid!=null)    providerId = Long.parseLong(providerid);
        String ispayment = req.getParameter("zhifu");
        int isPayment = 0;
        if(ispayment!=null) isPayment = Integer.parseInt(ispayment);

        //添加数据
        smbmsBill.setId(id);
        smbmsBill.setBillCode(billCode);
        smbmsBill.setProductName(productName);
        smbmsBill.setProductUnit(productUnit);
        smbmsBill.setProductCount(productCount);
        smbmsBill.setTotalPrice(totalPrice);
        smbmsBill.setProviderId(providerId);
        smbmsBill.setIsPayment(isPayment);
        HttpSession session = req.getSession();
        SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");
        smbmsBill.setModifyBy(userSession.getId());
        smbmsBill.setModifyDate(new Date());

        try {
            int i = smbmsBillService.updateBill(smbmsBill);
            if(i>0){
                req.getRequestDispatcher("/billList").forward(req,resp);
            }else {
                resp.sendRedirect("billUpdate.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
