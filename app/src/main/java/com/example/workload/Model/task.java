package com.example.workload.Model;

public class task {
    String id;
    String task_title, description;
    String dueDate;
    String emp_id;
    String status;
    String rating;
    String image;

    public task(String id, String task_title, String description, String dueDate, String emp_id, String status, String rating, String image) {
        this.id=id;
        this.task_title = task_title;
        this.description = description;
        this.dueDate = dueDate;
        this.emp_id = emp_id;
        this.status = status;
        this.rating = rating;
        this.image = image;
    }
    public task(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
