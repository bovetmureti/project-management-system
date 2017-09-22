/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import business.message;
import business.projectBean;
import business.user;
import data.messageDAO;
import data.projectDAO;
import data.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class myAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Timestamp time = null;
        String url;
        //  String action = request.getParameter("check");
        switch (request.getParameter("action")) {
            case "hidden": {
                String pid = request.getParameter("pid");
                int ipid = Integer.valueOf(pid);
                List<message> chat = messageDAO.getChats(ipid);
                request.setAttribute("chat", chat);
                session.setAttribute("pid", ipid);

                String staff = (String) session.getAttribute("staffid");

                List<user> userValues = userDAO.getUserdetails(staff);
                List<projectBean> myprojos = projectDAO.myprojects(staff);
                request.setAttribute("zangu", myprojos);
                request.setAttribute("person", userValues);

                request.getRequestDispatcher("/conversation.jsp").forward(request, response);

            }

            case "finish": {
                String finish = request.getParameter("pid");
                //String projo = (String) session.getAttribute("ipid");
                System.out.println(finish);
                int projectid = Integer.parseInt(finish);
                System.out.println(projectid);

                projectDAO.isFinish(projectid);

                String staff = (String) session.getAttribute("staffid");

                List<user> userValues = userDAO.getUserdetails(staff);
                List<projectBean> myprojos = projectDAO.myprojects(staff);
                request.setAttribute("zangu", myprojos);
                request.setAttribute("person", userValues);

                request.getRequestDispatcher("/conversation.jsp").forward(request, response);
                break;

            }

//            case "chat": {
//
//                String meso = request.getParameter("meso");
//                String ip = request.getParameter("ip");
//                if (null == meso && meso.isEmpty()) {
//                    request.getRequestDispatcher("/conversation.jsp").forward(request, response);
//                } else {
//                    String staff = (String) session.getAttribute("staffid");
//                    int pid = Integer.valueOf(ip);
//                    int messageId = 0;
//                    message m = new message(messageId, pid, meso, staff);
//                    int me = messageDAO.insertmeso(m);
//                    if (me < 1) {
//                        request.getRequestDispatcher("/login.jsp").forward(request, response);
//                        break;
//                    }
//                    List<message> chat = messageDAO.getChats(pid);
//                    request.setAttribute("chat", chat);
//
//                    List<user> userValues = userDAO.getUserdetails(staff);
//                    List<projectBean> myprojos = projectDAO.myprojects(staff);
//                    request.setAttribute("zangu", myprojos);
//                    request.setAttribute("person", userValues);
//                    request.getRequestDispatcher("/conversation.jsp").forward(request, response);
//                }
//                break;
//            }
//            case "adminchat": {
//                String chatid = request.getParameter("chatid");
//                String assigneeid = request.getParameter("projectownerid");
//                int projectIdint = Integer.valueOf(chatid);
//
//                session.setAttribute("chatid", projectIdint);
//
//                String staff = (String) session.getAttribute("staffid");
//
//                List<user> userValues = userDAO.getUserdetails(staff);
//                List<projectBean> myprojos = projectDAO.myprojects(assigneeid);
//                request.setAttribute("zangu", myprojos);
//                request.setAttribute("person", userValues);
//
//                List<message> chat = messageDAO.getChats(projectIdint);
//                if (chat.isEmpty()) {
//                    request.setAttribute("errormessage", "Sorry! No messages available for this project");
//                }
//                request.setAttribute("chat", chat);
//                session.setAttribute("projectId", projectIdint);
//                RequestDispatcher d = request.getRequestDispatcher("/adminchat.jsp");
//                d.forward(request, response);
//                return;
//            }
//            case "adminsend": {
//
//                String adminmeso = request.getParameter("adminmeso");
//                String mesoId = request.getParameter("mesoId");
//                if (adminmeso.isEmpty() && mesoId.isEmpty()) {
//                    response.sendRedirect("/adminchat.jsp");
//                    break;
//                } else {
//                    String staff = (String) session.getAttribute("adminid");
//                    // int projoid = Integer.valueOf(mesoId);
//                    int projoid = (Integer) session.getAttribute("chatid");
//                    int messageId = 0;
//                    message m = new message(messageId, projoid, adminmeso, staff);
//                    int me = messageDAO.insertmeso(m);
//                    if (me < 1) {
//                        request.getRequestDispatcher("/login.jsp").forward(request, response);
//                    }
//                    List<message> chat = messageDAO.getChats(projoid);
//                    request.setAttribute("chat", chat);
//
//                    //List<user> userValues = userDAO.getUserdetails(staff);
//                    List<projectBean> myprojos = projectDAO.myprojects(staff);
//                    request.setAttribute("zangu", myprojos);
//                    //request.setAttribute("person", userValues);
//                    request.getRequestDispatcher("/adminchat.jsp").forward(request, response);
//                    // response.sendRedirect("/adminchat.jsp");
//                }
//                //break;
//
//            }
        }

        String staff = (String) session.getAttribute("staffid");

        List<user> userValues = userDAO.getUserdetails(staff);
        List<projectBean> myprojos = projectDAO.myprojects(staff);
        
        request.setAttribute("zangu", myprojos);
        request.setAttribute("person", userValues);
        request.getRequestDispatcher("/myAccount.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
