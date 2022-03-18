package org.qf.web.Provider.Delete;

import org.qf.pojo.SmbmsProvider;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.Impl.SmbmsUserServiceImpl;
import org.qf.service.SmbmsProviderService;
import org.qf.service.SmbmsUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteProvider")
public class DeleteProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        String id = req.getParameter("id");
        Long proId = 0L;
        if(id!=null)  proId = Long.parseLong(id);

        try {
            int i = smbmsProviderService.deleteProviderById(proId);
            if(i>0){
                req.getRequestDispatcher("/providerList").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
