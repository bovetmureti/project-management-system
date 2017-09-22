/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import business.loginBean;
import business.projectBean;
import business.user;
import data.projectDAO;
import data.userDAO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
//import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        //admin processing
        RequestDispatcher d;

        String action = request.getParameter("action");
        switch (action) {
            case "log":
                String username = request.getParameter("username");
                String pawd = request.getParameter("pawd");
                
                loginBean login = new loginBean(username, pawd);
                
                String message = userDAO.login(login);
                String type = userDAO.utype(login);
                
                if (message.equals("SUCCESS") && type.equalsIgnoreCase("admin")) {
                    
                    List<user> staff = projectDAO.checkStaff();
                    List<projectBean> projos = projectDAO.currentprojects();
                    List<projectBean> state = projectDAO.projectState();
                    Date current = Date.valueOf(LocalDate.now());
                    List<projectBean> proj = projectDAO.fromalloc();
                    request.setAttribute("current", current);
                    session.setAttribute("cprojects", projos);
                    request.setAttribute("alloc", proj);
                    session.setAttribute("staffDetails", staff);
                    session.setAttribute("status", state);
                    session.setAttribute("adminid", pawd);
                    session.setAttribute("username", username);
                
                    d = getServletContext().getRequestDispatcher("/addProject");
                    d.forward(request, response);
                } 
                else if (type.equalsIgnoreCase("user") && message.equalsIgnoreCase("SUCCESS")) {
                    session.setAttribute("username", username);
                    session.setAttribute("staffid", pawd);
                    
                    request.getRequestDispatcher("/myAccount").forward(request, response);
                    
                } else {
                    request.setAttribute("errMessage", message);
                    request.getAttribute("errMessage");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }   break;
            case "admin":
               // String admin = request.getParameter("admin");
                //session.setAttribute("staffid", admin);
                d = getServletContext().getRequestDispatcher("/myAccount");
                d.forward(request, response);
                break;
                
            case "report":
                List<projectBean> rep = projectDAO.projectState();
                request.setAttribute("reports", rep);
                d = getServletContext().getRequestDispatcher("/report.jsp");
                d.forward(request, response);
                break;
                
            case "out":
                session.invalidate();
                // session.removeAttribute("username");
            d = getServletContext().getRequestDispatcher("/login.jsp");
                d.forward(request, response);
                break;
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