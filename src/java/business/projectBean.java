/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class projectBean implements Serializable{
    private String projectName;
    private Date startDate;
    private Date endDate;
    private Timestamp act_endDate;
    private String staffId;
    private String staffId2;
    private String staffId3;
    private int projectId;
    private String fname;
    private String surname;
    private int isFinished;
    private String client;
    private Timestamp arrivalDate;
    private String type;
    
    //private String costs;
    //private String projectBudget;

    public projectBean(String projectName, int projectId, String client, Timestamp arrivalDate, String type) {
        this.projectName = projectName;
        this.projectId = projectId;
        this.client = client;
        this.arrivalDate = arrivalDate;
        this.type = type;
    }

    public projectBean(Date startDate, Date endDate, String staffId, String staffId2, String staffId3, int projectId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.staffId = staffId;
        this.staffId2 = staffId2;
        this.staffId3 = staffId3;
        this.projectId = projectId;
    }
    
    

    public projectBean() {
    }
    
    
    


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setstaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStaffId2() {
        return staffId2;
    }

    public void setStaffId2(String staffId2) {
        this.staffId2 = staffId2;
    }

    public String getStaffId3() {
        return staffId3;
    }

    public void setStaffId3(String staffId3) {
        this.staffId3 = staffId3;
    }

    public Timestamp getAct_endDate() {
        return act_endDate;
    }

    public void setAct_endDate(Timestamp act_endDate) {
        this.act_endDate = act_endDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
   
}
