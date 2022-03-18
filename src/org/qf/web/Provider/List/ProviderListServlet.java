package org.qf.web.Provider.List;

import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
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

@WebServlet(value = "/providerList")
public class ProviderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //业务逻辑层
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        //接收供应商名字
        String proName = req.getParameter("proName");
        if(proName==null) proName="";
        //分页
        //接收当前页面
        String proPageIndex = req.getParameter("proPageIndex");
        int currentPageNo = 1;
        if(proPageIndex!=null) currentPageNo = Integer.parseInt(proPageIndex);
        //获取页面容量
        int pageSize = Constants.pageSize;
        List<SmbmsProvider> proList =null;
        int smbmsProCount = 0;

        try {
            smbmsProCount = smbmsProviderService.getSmbmsProviderCount(proName);
            //pageSurpport赋值
            PageSupport pageSupport = new PageSupport();
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setPageSize(pageSize);
            pageSupport.setTotalCount(smbmsProCount);
            int totalPageCount = pageSupport.getTotalPageCount();
            //控制首页和尾页
            if(currentPageNo<1) currentPageNo = 1;
            else if(currentPageNo>totalPageCount) currentPageNo = totalPageCount;
            //查询
            proList = smbmsProviderService.queryProviderByproName(proName,currentPageNo,pageSize);
            if(proList.size()>0){
                req.setAttribute("proList",proList);
                req.setAttribute("proName",proName);
                req.setAttribute("proPages",pageSupport);
            }
            req.getRequestDispatcher("providerList.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
