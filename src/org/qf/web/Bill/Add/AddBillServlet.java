package org.qf.web.Bill.Add;

import org.qf.pojo.SmbmsBill;
import org.qf.pojo.SmbmsProvider;
import org.qf.pojo.SmbmsUser;
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
import java.util.Date;
import java.util.List;

@WebServlet(value = "/addBill")
public class AddBillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //业务层接口
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        //获取数据
        String billCode = req.getParameter("billId");
        String productName = req.getParameter("billName");
        String productUnit = req.getParameter("billCom");
        String productcount = req.getParameter("billNum");
        double productCount = 0;
        if(productcount!=null)  productCount = Double.parseDouble(productcount);
        String totalprice = req.getParameter("money");
        double totalPrice = 0L;
        if(totalprice!=null)    totalPrice = Double.parseDouble(totalprice);
        String productDesc = req.getParameter("desc");
        String providerid = req.getParameter("supplier");
        Long providerId = 0L;
        if(providerid!=null)    providerId = Long.parseLong(providerid);
        String ispayment = req.getParameter("zhifu");
        int isPayment = 0;
        if(ispayment!=null) isPayment = Integer.parseInt(ispayment);
        //添加数据
        SmbmsBill smbmsBill = new SmbmsBill();
        smbmsBill.setBillCode(billCode);
        smbmsBill.setProductName(productName);
        smbmsBill.setProductUnit(productUnit);
        smbmsBill.setProductCount(productCount);
        smbmsBill.setTotalPrice(totalPrice);
        smbmsBill.setProductDesc(productDesc);
        smbmsBill.setProviderId(providerId);
        smbmsBill.setIsPayment(isPayment);
        HttpSession session = req.getSession();
        SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");
        smbmsBill.setCreateBy(userSession.getId());
        smbmsBill.setCreationDate(new Date());

        try {
            int i = smbmsBillService.addBill(smbmsBill);
            if(i>0){
                req.getRequestDispatcher("/billList").forward(req,resp);
            }else {
                req.getRequestDispatcher("/addBill").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
