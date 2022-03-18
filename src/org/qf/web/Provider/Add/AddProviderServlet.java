package org.qf.web.Provider.Add;

import org.qf.pojo.SmbmsProvider;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsProviderService;
import org.qf.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/addProvider")
public class AddProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        SmbmsProvider smbmsProvider = new SmbmsProvider();

        String proCode = req.getParameter("providerId");
        String proName = req.getParameter("providerName");
        String proContact = req.getParameter("people");
        String proPhone = req.getParameter("phone");
        String proAddress = req.getParameter("address");
        String proFax = req.getParameter("fax");
        String proDesc = req.getParameter("describe");

        HttpSession session = req.getSession();
        SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");

        smbmsProvider.setProCode(proCode);
        smbmsProvider.setProName(proName);
        smbmsProvider.setProContact(proContact);
        smbmsProvider.setProPhone(proPhone);
        smbmsProvider.setProAddress(proAddress);
        smbmsProvider.setProFax(proFax);
        smbmsProvider.setProDesc(proDesc);
        smbmsProvider.setCreatedBy(userSession.getId());
        smbmsProvider.setCreationDate(new Date());

        try {
            int i = smbmsProviderService.addProvider(smbmsProvider);
            if(i>0){
                req.getRequestDispatcher("/providerList").forward(req,resp);
            }else {
                req.getRequestDispatcher("/addProvider").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
