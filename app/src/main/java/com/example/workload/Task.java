package com.example.workload;

public class Task {
    String name, description;
    String dueDate;
    String employee;
    String status;
    String rating;
    String image;

    public Task(String name, String description, String dueDate, String employee, String status, String rating, String image) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.employee = employee;
        this.status = status;
        this.rating = rating;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDueTime() {
        return employee;
    }

    public void setDueTime(String employee) {
        this.employee = employee;
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
