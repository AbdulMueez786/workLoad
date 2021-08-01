package com.example.workload.Model;

public class employee {
    private String id;
    private String email;
    private String F_Name;
    private String L_Name;
    private  String username, designation, profile, joining_date;
    private String total_task_assign;
    private String task_complited;
    private String p_id;

    public employee(String id, String email, String f_Name, String l_Name, String username, String designation, String profile, String joining_date, String total_task_assign, String task_complited, String p_id) {
        this.id = id;
        this.email = email;
        F_Name = f_Name;
        L_Name = l_Name;
        this.username = username;
        this.designation = designation;
        this.profile = profile;
        this.joining_date = joining_date;
        this.total_task_assign = total_task_assign;
        this.task_complited = task_complited;
        this.p_id=p_id;
    }
    public employee(){

    }
    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String f_Name) {
        F_Name = f_Name;
    }

    public String getL_Name() {
        return L_Name;
    }

    public void setL_Name(String l_Name) {
        L_Name = l_Name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getTotal_task_assign() {
        return total_task_assign;
    }

    public void setTotal_task_assign(String total_task_assign) {
        this.total_task_assign = total_task_assign;
    }

    public String getTask_complited() {
        return task_complited;
    }

    public void setTask_complited(String task_complited) {
        this.task_complited = task_complited;
    }
}
