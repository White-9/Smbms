package org.qf.web.Provider.View;

import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(value = "/providerView")
public class ProviderViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //业务逻辑
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        //获取供应商id
        String id = req.getParameter("proId");
        Long proId = 0L;
        if(id!=null) proId = Long.parseLong(id);

        try {
            SmbmsProvider smbmsProvider = smbmsProviderService.queryProviderById(proId);
            if(null!=smbmsProvider){
                req.setAttribute("provider",smbmsProvider);
                req.getRequestDispatcher("providerView.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
