package org.qf.web.Bill.List;

import org.qf.pojo.SmbmsBill;
import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsBillServiceImpl;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsBillService;
import org.qf.service.SmbmsProviderService;
import org.qf.utils.Constants;
import org.qf.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/billList")
public class BillListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品名，供应商id，是否付款
        String productName = req.getParameter("productName");
        String id = req.getParameter("tigong");
        Long providerId = 0L;
        String ispayment = req.getParameter("fukuan");
        int isPayment = 0;
        //调用业务层
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        SmbmsBillService smbmsBillService = new SmbmsBillServiceImpl();
        //判断
        if(id!=null&&!id.equals("")){
            providerId = Long.parseLong(id);
        }
        if(ispayment!=null&&!ispayment.equals("")){
            isPayment = Integer.parseInt(ispayment);
        }
        if(productName==null) productName="";
        List<SmbmsBill> list = null;
        //分页
        //获取当前页面
        String pageIndex = req.getParameter("billPageIndex");
        int currentPageNo = 1;
        if(pageIndex!=null) currentPageNo = Integer.parseInt(pageIndex);
        //获取页面容量
        int pageSize = Constants.pageSize;

        int smbmsBillCount = 0;

        try {
            //获取总记录数
            smbmsBillCount = smbmsBillService.getSmbmsBillCount(productName, providerId, isPayment);
            //pageSurpport赋值
            PageSupport pageSupport = new PageSupport();
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setPageSize(pageSize);
            pageSupport.setTotalCount(smbmsBillCount);
            //计算总页数
            int totalPageCount = pageSupport.getTotalPageCount();
            //控制首页和尾页
            if(currentPageNo<1) currentPageNo = 1;
            else if(currentPageNo>totalPageCount){
                if(totalPageCount==0){
                    currentPageNo=1;
                }else {
                    currentPageNo=totalPageCount;
                }
            }

            list = smbmsBillService.queryBillByproductName(productName,providerId,isPayment,currentPageNo,pageSize);
            if(list.size()>0){
                req.setAttribute("billList",list);
                req.setAttribute("productName",productName);
                req.setAttribute("billPages",pageSupport);
            }
            //查询供应商
            List<SmbmsProvider> list1 = smbmsProviderService.queryName();
            if(list1.size()>0){
                req.setAttribute("tigong",providerId);
                req.setAttribute("providerList",list1);
            }
            req.getRequestDispatcher("billList.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
