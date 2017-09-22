/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import business.projectBean;
import business.user;
import database.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class projectDAO {
  static Connection con;
    static PreparedStatement ps;

    /**
     *
     * @return
     */
//    public static List<projectBean> viewProjects() {
//        List<projectBean> projectList = new ArrayList<>();
//        // List<user> users = new ArrayList<>();
//
//        String query = "SELECT * from projects,employees,allocation where projects.staffId=employees.staffId order by projectId ";
//        try {
//            con = connect.getConnection();
//            ps = con.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                projectBean bean = new projectBean();
//                
//                bean.setProjectName(rs.getString("projectName"));
//                bean.setstaffId(rs.getString("staffId"));
//                bean.setFname(rs.getString("fname"));
//                bean.setProjectId(rs.getInt("projectId"));
//                bean.setEndDate(rs.getDate("endDate"));
//                bean.setIsFinished(rs.getInt("isFinished"));
//              
//                projectList.add(bean);
//            }
//       } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//
//        return projectList;
//    }
//    
    
    
    public static List<projectBean> currentprojects() {
        List<projectBean> projects = new ArrayList<>();
        String query = "select * from projects";
        try {
            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectBean bean = new projectBean();
                bean.setProjectName(rs.getString("projectName"));
                bean.setProjectId(rs.getInt("projectid"));
                bean.setClient(rs.getString("client"));
                bean.setArrivalDate(rs.getTimestamp("arrivalDate"));
                projects.add(bean);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return projects;
    }
    
    
    //FROM ALLOCATE TABLE
    
     public static List<projectBean> fromalloc() {
        List<projectBean> projects = new ArrayList<>();
        String query = "select * from allocation";
        try {
            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectBean bean = new projectBean();
                bean.setProjectName(rs.getString("projectName"));
                bean.setIsFinished(rs.getInt("isFinished"));
                bean.setProjectId(rs.getInt("projectid"));
               bean.setStartDate(rs.getDate("Act_endDate"));
                bean.setArrivalDate(rs.getTimestamp("arrivalDate"));
                projects.add(bean);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return projects;
    }
    
    
    public static List<projectBean> projectState(){
        List<projectBean> projec = new ArrayList<>();
        String query = "select * from projects,allocation,employees where projects.projectid=allocation.projectid&&allocation.staffId1=employees.staffId";
        
        try{
            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectBean bean = new projectBean();
                bean.setProjectName(rs.getString("projectName"));
               bean.setProjectId(rs.getInt("projectid"));
              bean.setClient(rs.getString("client"));
            bean.setArrivalDate(rs.getTimestamp("arrivalDate"));
              bean.setIsFinished(rs.getInt("isFinished"));
               bean.setAct_endDate(rs.getTimestamp("act_endDate"));
              bean.setEndDate(rs.getDate("Exp_endDate"));
              bean.setSurname(rs.getString("surname"));
              bean.setFname(rs.getString("fname"));   
               
            projec.add(bean);
        }}
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return projec;
    }
    
  public static List<projectBean> report(Date x,Date y){
      List<projectBean> project = new ArrayList<>();
      String query="select * from projects,allocation,employees where projects.projectid=allocation.projectid&&allocation.staffId1=employees.staffId && allocation.Exp_startDate BETWEEN ? AND ?";
      try{
               con = connect.getConnection();
                ps = con.prepareStatement(query);
               ps.setDate(1, x);
               ps.setDate(2, y);
           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                  projectBean bean = new projectBean();
                bean.setProjectName(rs.getString("projectName"));
               bean.setProjectId(rs.getInt("projectid"));
              bean.setClient(rs.getString("client"));
            bean.setArrivalDate(rs.getTimestamp("arrivalDate"));
              bean.setIsFinished(rs.getInt("isFinished"));
               bean.setAct_endDate(rs.getTimestamp("act_endDate"));
              bean.setEndDate(rs.getDate("Exp_endDate"));
              bean.setStartDate(rs.getDate("Exp_startDate"));
              bean.setSurname(rs.getString("surname"));
              bean.setFname(rs.getString("fname"));   
              
               
            project.add(bean);
        }}
      catch(SQLException e){
          e.getMessage();
            }
          return project;
      }
  
    
    
    
    
    
 

    public static int insertProject(projectBean bean) { // in use currently

        try {
            String query = "INSERT INTO projects (`projectName`,`projectid`,`client`,`arrivalDate`,`type`)"
                    + "VALUES(?,?,?,?,?)";

            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(2, bean.getProjectId());
            ps.setString(1, bean.getProjectName());
            ps.setString(3, bean.getClient());
            ps.setTimestamp(4, bean.getArrivalDate());
            ps.setString(5, bean.getType());
            
            int in = ps.executeUpdate();

            return in;

        } catch (SQLException ex) {
            Logger.getLogger(projectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
    
    
      public static int allocateProject(projectBean bean) { // in use currently to allocate projects to staff

        try {
            String query = "INSERT INTO allocation (`projectid`,`staffId1`,`staffId2`,`staffId3`,`Exp_startDate`,`Exp_endDate`)"
                    + "VALUES(?,?,?,?,?,?)";

            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, bean.getProjectId());
            ps.setString(2, bean.getStaffId());
            ps.setString(3, bean.getStaffId2());
            ps.setString(4, bean.getStaffId3());
            ps.setDate(5, bean.getStartDate());
            ps.setDate(6, bean.getEndDate());
            
            int in = ps.executeUpdate();

            return in;

        } catch (SQLException ex) {
            Logger.getLogger(projectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
    
    
    
    public static void isFinish(int projeId){
     
      try {
          projectBean bean = new projectBean();
          String query = "UPDATE allocation set isFinished=?,act_endDate=? where projectid=?";
//          Statement st = con.createStatement();
//          st.executeUpdate(query);
          ps = con.prepareStatement(query);
          ps.setInt(1, 1);
          ps.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
          
          ps.setInt(3, projeId);
          ps.executeUpdate();
         
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
         
      }
        
      }

    public static List<user> checkStaff() {
        String query = "select staffId,fname,surname from employees";
        Statement st;
        String staff;
        try {
            con = connect.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<user> staffids = new ArrayList<>();
            while (rs.next()) {
                user staf = new user();
                staf.setStaffId(rs.getString("staffId"));
                staf.setFname(rs.getString("fname"));
                staf.setSurname(rs.getString("surname"));
                staffids.add(staf);

            }
            return staffids;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static List<projectBean> myprojects(String staffid) {
        List<projectBean> projectsTaken = new ArrayList<>();
        String query = "select * from projects,allocation,employees where projects.projectid=allocation.projectid && employees.staffId=allocation.staffId1 && employees.staffId=?";
        try {
            con = connect.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, staffid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectBean bean = new projectBean();
                bean.setProjectId(rs.getInt("projectId"));
                bean.setProjectName(rs.getString("projectName"));
                bean.setStartDate(rs.getDate("Exp_startDate"));
                bean.setEndDate(rs.getDate("Exp_endDate"));
                bean.setIsFinished(rs.getInt("isFinished"));
                projectsTaken.add(bean);
            }
            return projectsTaken;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }  
}
