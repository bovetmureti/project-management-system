/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import business.projectBean;
import business.user;
import data.projectDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class addProject extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("username")) {
            response.sendRedirect("/login.jsp");
        }

        if ("addproject".equals(action)) {
            String pname = request.getParameter("pname");
            String client = request.getParameter("client");
            String type = request.getParameter("type");
            Timestamp arrivaLDate = null;
            int projectid = 0;

            projectBean bean = new projectBean(pname, projectid, client, arrivaLDate, type);

            projectDAO.insertProject(bean);

            RequestDispatcher d = getServletContext().getRequestDispatcher("/home.jsp");
            d.forward(request, response);

            System.out.println(pname + "\t" + client + "\t" + type);
        }
        
        
       

        else{

        String projectId = request.getParameter("projectId");
        String startString = request.getParameter("startdate");
        String endString = request.getParameter("endDate");
        String staffId = request.getParameter("staffId");
        String[] others = request.getParameterValues("others");
        if (null != others) {
            String staffId2 = others[0];
            String staffId3 = others[1];
            if (startString != null || endString != null) {
                final Date startDate = Date.valueOf(startString);
                final Date endDate = Date.valueOf(endString);
                int projectid = Integer.parseInt(projectId);
                projectBean project = new projectBean(startDate, endDate, staffId, staffId2, staffId3, projectid);
                projectDAO.allocateProject(project);
               
            }
        } 
            Date current = Date.valueOf(LocalDate.now());
            request.setAttribute("current", current);
            List<projectBean> projects = projectDAO.currentprojects();
            List<projectBean> proj = projectDAO.fromalloc();
              List<user> staff = projectDAO.checkStaff();
                   
                    List<projectBean> state = projectDAO.projectState();
                    request.setAttribute("alloc", proj);
                    request.setAttribute("status", state);
            request.setAttribute("cprojects", projects);
            request.getAttribute("status");
            request.getAttribute("projects");

            RequestDispatcher d = getServletContext().getRequestDispatcher("/home.jsp");
            d.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
