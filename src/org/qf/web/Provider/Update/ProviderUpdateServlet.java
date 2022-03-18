package org.qf.web.Provider.Update;

import org.qf.pojo.SmbmsProvider;
import org.qf.pojo.SmbmsUser;
import org.qf.service.Impl.SmbmsProviderServiceImpl;
import org.qf.service.SmbmsProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/providerUpdate")
public class ProviderUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SmbmsProviderService smbmsProviderService = new SmbmsProviderServiceImpl();
        SmbmsProvider smbmsProvider = new SmbmsProvider();
        String ids = req.getParameter("id");
        Long proId = Long.parseLong(ids);

        String proCode = req.getParameter("providerId");
        String proName = req.getParameter("providerName");
        String proContact = req.getParameter("people");
        String proAddress = req.getParameter("address");
        String proFax = req.getParameter("fax");
        String proDesc = req.getParameter("describe");
        String proPhone = req.getParameter("phone");
        smbmsProvider.setProPhone(proPhone);
        smbmsProvider.setId(proId);
        smbmsProvider.setProCode(proCode);
        smbmsProvider.setProName(proName);
        smbmsProvider.setProContact(proContact);
        smbmsProvider.setProAddress(proAddress);
        smbmsProvider.setProFax(proFax);
        smbmsProvider.setProDesc(proDesc);
        
        HttpSession session = req.getSession();
        SmbmsUser userSession = (SmbmsUser)session.getAttribute("userSession");
        smbmsProvider.setModifyBy(userSession.getId());
        smbmsProvider.setModifyDate(new Date());

        try {
            int i = smbmsProviderService.updateProvider(smbmsProvider);
            if(i>0){
                req.getRequestDispatcher("/providerList").forward(req,resp);
            }else {
                resp.sendRedirect("providerUpdate.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
